package com.icebear2n2.lotto.model.dto;

import java.time.ZonedDateTime;
import java.util.List;

import com.icebear2n2.lotto.model.entity.AuthCode;
import com.icebear2n2.lotto.model.entity.User;

import lombok.Getter;

@Getter
public class AuthCodeDto {
	private final UserDto user;
	private final String code;
	private final ZonedDateTime expiredAt;
	private final ZonedDateTime completedAt;
	
	public AuthCodeDto(User user, String code, ZonedDateTime expiredAt, ZonedDateTime completedAt) {
		this.user = new UserDto(user.getUsername(), user.getEmail(), user.getBirth(), user.getPhoneNumber(), user.getCreatedAt(), user.getUpdatedAt(), user.getDeletedAt());
		this.code = code;
		this.expiredAt = expiredAt;
		this.completedAt = completedAt;
	}
	
	public static AuthCodeDto of(AuthCode code) { return new AuthCodeDto(code.getUser(), code.getCode(), code.getExpiredAt(), code.getCompletedAt()); }
}
