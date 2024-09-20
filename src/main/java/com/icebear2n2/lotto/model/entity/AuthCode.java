package com.icebear2n2.lotto.model.entity;

import java.time.ZonedDateTime;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "AUTH_CODES")
public class AuthCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AUTH_CODE_ID")
    private Long authCodeId;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    private User user;
    
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
    
    @Column(name = "CODE")
    private String code;
    
    @Column(name = "EXPIRATION_TIME")
    private ZonedDateTime expirationTime;
    
    @Column(name = "CREATED_AT")
    private ZonedDateTime createdAt;
    
    @Column(name = "COMPLETED_AT")
    private ZonedDateTime completedAt;
    
    
    
    
    public void setUser(User user) {
        this.user = user;
    }

    public void setCompletedAt(ZonedDateTime completedAt) {
        this.completedAt = completedAt;
    }
    
    @PrePersist
    private void prePersist() {
        this.createdAt = ZonedDateTime.now();
    }
    
	public AuthCode(String phoneNumber, String code, ZonedDateTime expirationTime) {
		super();
		this.phoneNumber = phoneNumber;
		this.code = code;
		this.expirationTime = expirationTime;
	}
    

	public AuthCode(User user, String phoneNumber, String code, ZonedDateTime expirationTime,
			ZonedDateTime createdAt, ZonedDateTime completedAt) {
		this.user = user;
		this.phoneNumber = phoneNumber;
		this.code = code;
		this.expirationTime = expirationTime;
		this.createdAt = createdAt;
		this.completedAt = completedAt;
	}


}
