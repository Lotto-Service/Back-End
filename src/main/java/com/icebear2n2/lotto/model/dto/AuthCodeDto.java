package com.icebear2n2.lotto.model.dto;

import java.time.ZonedDateTime;

import com.icebear2n2.lotto.model.entity.AuthCode;
import com.icebear2n2.lotto.model.entity.User;

import lombok.Getter;

@Getter
public class AuthCodeDto {
	private final UserDto user;
	private final String phoneNumber;
	private final String code;
	private final ZonedDateTime expirationTime;
	private final ZonedDateTime createdAt;
	private final ZonedDateTime completedAt;
	
	public AuthCodeDto(User user, String phoneNumber, String code, ZonedDateTime expirationTime,
			ZonedDateTime createdAt, ZonedDateTime completedAt) {
		this.user = new UserDto(user.getUsername(), user.getEmail(), user.getBirth(), user.getPhoneNumber(), user.getCreatedAt(), user.getUpdatedAt(), user.getDeletedAt());
		this.phoneNumber = phoneNumber;
		this.code = code;
		this.expirationTime = expirationTime;
		this.createdAt = createdAt;
		this.completedAt = completedAt;
	}
	
	public static AuthCodeDto of(AuthCode authCode) { return new AuthCodeDto(authCode.getUser(), authCode.getPhoneNumber(), authCode.getCode(), authCode.getExpirationTime(), authCode.getCreatedAt(), authCode.getCompletedAt()); }
	
}
