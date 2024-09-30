package com.icebear2n2.lotto.repository;

import com.icebear2n2.lotto.exception.ClientErrorException;
import com.icebear2n2.lotto.model.entity.User;
import com.icebear2n2.lotto.model.request.UserSignUpRequest;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final UserJpaRepository userJpaRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public User create(UserSignUpRequest request) {
    	
    	if (userJpaRepository.existsByUsername(request.username())) {
    		throw new ClientErrorException(HttpStatus.OK, "이미 존재하는 사용자 이름입니다: " + request.username());
    	}
    	
    	if (userJpaRepository.existsByEmail(request.email())) {
    		throw new ClientErrorException(HttpStatus.OK, "이미 등록된 이메일입니다.");
    	}
    	
    	if (userJpaRepository.existsByPhoneNumber(request.phoneNumber())) {
    		throw new ClientErrorException(HttpStatus.OK, "이미 등록된 전화번호입니다.");
    	}
    	
    	LocalDate now = LocalDate.now();
    	LocalDate birth = request.birth().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    	
    	Period age = Period.between(birth, now);
    	
    	if (age.getYears() < 18) {
    	    throw new ClientErrorException(HttpStatus.OK, "사용자는 18세 이상이어야 합니다. 현재 나이: " + age.getYears());
    	}

    	if (request.password().length() < 8) {
    	    throw new ClientErrorException(HttpStatus.OK, "비밀번호는 최소 8자 이상이어야 합니다. 현재 길이: " + request.password().length());
    	}
    	
        return userJpaRepository.save(new User(request.username(), passwordEncoder.encode(request.password()), request.email(), request.birth(), request.phoneNumber()));
    }
    
    public User findByUsername(String username) {
        return userJpaRepository.findByUsername(username).orElseThrow(() -> new ClientErrorException(HttpStatus.OK, "해당 사용자 이름을 찾을 수 없습니다: " + username));
    }

    public boolean existsByUsername(String username) {
    	return userJpaRepository.existsByUsername(username);
    }

    public User findById(Long userId) {
        return userJpaRepository.findById(userId).orElseThrow(() -> new ClientErrorException(HttpStatus.OK, "해당 사용자 ID를 찾을 수 없습니다: " + userId));
    }

	@Transactional
	public User update(User user) {
		return userJpaRepository.save(user);
	}
}
