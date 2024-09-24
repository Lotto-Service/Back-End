package com.icebear2n2.lotto.repository;

import com.icebear2n2.lotto.exception.user.InvalidPasswordException;
import com.icebear2n2.lotto.exception.user.UserAlreadyExistException;
import com.icebear2n2.lotto.exception.user.UserNotFoundException;
import com.icebear2n2.lotto.exception.user.UserUnderageException;
import com.icebear2n2.lotto.model.entity.User;
import com.icebear2n2.lotto.model.request.UserSignUpRequest;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final UserJpaRepository userJpaRepository;
    private final JPAQueryFactory jpaQueryFactory;
    private final BCryptPasswordEncoder passwordEncoder;

    public User findByUsername(String username) {
        return userJpaRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    }

    @Transactional
    public User create(UserSignUpRequest request) {
    	
    	if (userJpaRepository.existsByUsername(request.username())) {
    		throw new UserAlreadyExistException(request.username());
    	}
    	
    	if (userJpaRepository.existsByEmail(request.email())) {
    		throw new UserAlreadyExistException();
    	}
    	
    	if (userJpaRepository.existsByPhoneNumber(request.phoneNumber())) {
    		throw new UserAlreadyExistException();
    	}
    	
    	LocalDate now = LocalDate.now();
    	LocalDate birth = request.birth().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    	
    	Period age = Period.between(birth, now);
    	
    	if (age.getYears() < 18) {
    	    throw new UserUnderageException(age.getYears());
    	}

    	if (request.password().length() < 8) {
    	    throw new InvalidPasswordException(request.password().length());
    	}
    	
        return userJpaRepository.save(new User(request.username(), passwordEncoder.encode(request.password()), request.email(), request.birth(), request.phoneNumber()));
    }

    @Transactional
    public User findById(Long userId) {
        return userJpaRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

	@Transactional
	public User update(User user) {
		return userJpaRepository.save(user);
	}
}
