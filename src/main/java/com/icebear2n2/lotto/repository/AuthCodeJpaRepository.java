package com.icebear2n2.lotto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icebear2n2.lotto.model.entity.AuthCode;
import com.icebear2n2.lotto.model.entity.User;

public interface AuthCodeJpaRepository extends JpaRepository<AuthCode, Long> {
 	AuthCode findByUserPhoneNumberAndCode(String phoneNumber, String code);
    AuthCode findByUserAndCode(User user, String code);

}
