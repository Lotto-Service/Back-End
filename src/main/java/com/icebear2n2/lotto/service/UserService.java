package com.icebear2n2.lotto.service;

import com.icebear2n2.lotto.exception.ClientErrorException;
import com.icebear2n2.lotto.model.dto.UserDto;
import com.icebear2n2.lotto.model.entity.RefreshToken;
import com.icebear2n2.lotto.model.entity.User;
import com.icebear2n2.lotto.model.request.UserLoginRequest;
import com.icebear2n2.lotto.model.request.UserSignUpRequest;
import com.icebear2n2.lotto.model.response.UserAuthenticationResponse;
import com.icebear2n2.lotto.repository.RefreshTokenRepository;
import com.icebear2n2.lotto.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getByUsername(username);
    }

    public UserDto signUp(UserSignUpRequest request) {
        return UserDto.of(userRepository.create(request));
    }

    public UserAuthenticationResponse authenticate(UserLoginRequest request) {
        User user = userRepository.findByUsername(request.username());

        if (passwordEncoder.matches(request.password(), user.getPassword())) {
            var accessToken = jwtService.generateAccessToken(user);
            var refreshToken = jwtService.generateRefreshToken(user);
            
            return new UserAuthenticationResponse(accessToken, refreshToken);
        } else {
            throw new ClientErrorException(HttpStatus.NOT_FOUND, "계정 정보가 일치하지 않거나, 존재하지 않는 계정입니다.");
        }
    }
    
    public String logout(User user) {
        RefreshToken refreshToken = refreshTokenRepository.findByUser(user);
        if (refreshToken == null) {
            throw new ClientErrorException(HttpStatus.NOT_FOUND, "토큰 정보를 찾을 수 없습니다.");
        }
        jwtService.invalidateRefreshToken(refreshToken.getToken());
        SecurityContextHolder.clearContext();
        
        return "성공적으로 로그아웃되었습니다.";
    }

    private User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }
  
}
