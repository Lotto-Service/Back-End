package com.icebear2n2.lotto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icebear2n2.lotto.model.entity.AuthCode;
import com.icebear2n2.lotto.model.entity.User;

public interface AuthCodeJpaRepository extends JpaRepository<AuthCode, Long> {
 	Optional<AuthCode> findByUserPhoneNumberAndCode(String phoneNumber, String code);
 	Optional<AuthCode> findByUserAndCode(User user, String code);

}
