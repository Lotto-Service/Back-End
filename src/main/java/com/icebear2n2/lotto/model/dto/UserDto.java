package com.icebear2n2.lotto.model.dto;

import com.icebear2n2.lotto.model.entity.User;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.Date;

@Getter
public class UserDto {
    private final String username;
    private final String email;
    private final Date birth;
    private final String phoneNumber;
    private final ZonedDateTime createdAt;
    private final ZonedDateTime updatedAt;
    private final ZonedDateTime deletedAt;

    public UserDto(String username, String email, Date birth, String phoneNumber, ZonedDateTime createdAt, ZonedDateTime updatedAt, ZonedDateTime deletedAt) {
        this.username = username;
        this.email = email;
        this.birth = birth;
        this.phoneNumber = phoneNumber;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public static UserDto of (User user) { return new UserDto(user.getUsername(), user.getEmail(), user.getBirth(), user.getPhoneNumber(), user.getCreatedAt(), user.getUpdatedAt(), user.getDeletedAt()); }
}
