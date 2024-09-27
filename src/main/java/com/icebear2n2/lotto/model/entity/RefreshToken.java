package com.icebear2n2.lotto.model.entity;

import java.time.ZonedDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "REFRESH_TOKENS")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TOKEN_ID")
    private Long tokenId;
    
    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;
    
    @Column(name = "TOKEN", nullable = false, unique = true)
    private String token;
    
    @Column(name = "EXPIRED_AT")
    private ZonedDateTime expiredAt;

	public RefreshToken(User user, String token) {
		this.user = user;
		this.token = token;
	}
    
	public RefreshToken(User user, String token, ZonedDateTime expiredAt) {
		this.user = user;
		this.token = token;
		this.expiredAt = expiredAt;
	}

	public void setToken(String token) {
		this.token = token;
	}


    
}
