package com.icebear2n2.lotto.model.dto;

import java.time.ZonedDateTime;

import com.icebear2n2.lotto.model.entity.RefreshToken;
import com.icebear2n2.lotto.model.entity.User;

public class RefreshTokenDto {
	private final UserDto user;
	private final String token;
	private final ZonedDateTime expiredAt;
	public RefreshTokenDto(User user, String token, ZonedDateTime expiredAt) {
		this.user = new UserDto(user.getUsername(), user.getEmail(), user.getBirth(), user.getPhoneNumber(), user.getCreatedAt(), user.getUpdatedAt(), user.getDeletedAt());
		this.token = token;
		this.expiredAt = expiredAt;
	}
	
	public static RefreshTokenDto of(RefreshToken token) { return new RefreshTokenDto(token.getUser(), token.getToken(), token.getExpiredAt()); }
}
