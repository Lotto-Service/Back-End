package com.icebear2n2.lotto.model.entity;

import java.time.ZonedDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "AUTH_CODES")
public class AuthCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODE_ID")
    private Long codeId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;
    
    @Column(name = "code")
    private String code;
    
    @Column(name = "EXPIRED_AT")
    private ZonedDateTime expiredAt;
    
    @Column(name = "COMPLETED_AT")
    private ZonedDateTime completedAt;
    
	public AuthCode(User user, String code) {
		this.user = user;
		this.code = code;
	}
    
    
	public AuthCode(User user, String code, ZonedDateTime expiredAt, ZonedDateTime completedAt) {
		this.user = user;
		this.code = code;
		this.expiredAt = expiredAt;
		this.completedAt = completedAt;
	}


	public void setCompletedAt(ZonedDateTime completedAt) {
		this.completedAt = completedAt;
	}


	public void setUser(User user) {
		this.user = user;
	}
    
	
    
}
