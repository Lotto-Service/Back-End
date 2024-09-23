package com.icebear2n2.lotto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icebear2n2.lotto.model.entity.RefreshToken;
import com.icebear2n2.lotto.model.entity.User;

public interface RefreshTokenJpaRepository extends JpaRepository<RefreshToken, Long>{
	Optional<RefreshToken> findByToken(String token);
	void deleteByUser(User user);
}
