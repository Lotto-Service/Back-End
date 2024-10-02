package com.icebear2n2.lotto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.icebear2n2.lotto.model.entity.AuthCode;
import com.icebear2n2.lotto.model.entity.User;

public interface AuthCodeJpaRepository extends JpaRepository<AuthCode, Long> {
 	Optional<AuthCode> findByUserPhoneNumberAndCode(String phoneNumber, String code);
 	AuthCode findByUserAndCode(User user, String code);
 	@Query("SELECT a FROM AuthCode a WHERE a.code = :code AND a.completedAt IS NULL")
    Optional<AuthCode> findByCodeAndCompletedAtIsNull(@Param("code") String code);
}
