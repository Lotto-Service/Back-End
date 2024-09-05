package com.icebear2n2.lotto.service;

import com.icebear2n2.lotto.exception.user.UserAlreadyExistException;
import com.icebear2n2.lotto.exception.user.UserNotFoundException;
import com.icebear2n2.lotto.model.dto.UserDto;
import com.icebear2n2.lotto.model.entity.User;
import com.icebear2n2.lotto.model.request.UserLoginRequest;
import com.icebear2n2.lotto.model.request.UserSignUpRequest;
import com.icebear2n2.lotto.model.response.UserAuthenticationResponse;
import com.icebear2n2.lotto.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
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
            return new UserAuthenticationResponse(accessToken);
        } else {
            throw new UserNotFoundException();
        }
    }

    private User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
