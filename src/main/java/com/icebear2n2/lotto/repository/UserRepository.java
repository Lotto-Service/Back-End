package com.icebear2n2.lotto.repository;

import com.icebear2n2.lotto.exception.user.UserNotFoundException;
import com.icebear2n2.lotto.model.entity.User;
import com.icebear2n2.lotto.model.request.UserSignUpRequest;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
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
        return userJpaRepository.save(new User(request.username(), passwordEncoder.encode(request.password()), request.email(), request.birth(), request.phoneNumber()));
    }

    @Transactional
    public User findById(Long userId) {
        return userJpaRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }
}
