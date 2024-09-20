package com.icebear2n2.lotto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.icebear2n2.lotto.model.entity.AuthCode;
import com.icebear2n2.lotto.model.entity.User;

public interface AuthCodeJpaRepository extends JpaRepository<AuthCode, Long> {
    AuthCode findByPhoneNumberAndCode(String phoneNumber, String code);

    AuthCode findByUserAndCode(User user, String code);

    @Query("SELECT a FROM AUTH_CODES a WHERE a.PHONE_NUMBER = ?1 ORDER BY a.COMPLETED_AT DESC limit 1")
    AuthCode findByPhone(String phoneNumber);

}
