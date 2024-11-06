package com.areastory.user.dto.response;

import com.areastory.user.db.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSignUpResp {

    private Long userId;
    private String hashKey;

    public static UserSignUpResp fromEntity(UserInfo user) {
        return UserSignUpResp.builder()
                .userId(user.getUserId())
                .hashKey(user.getHashKey())
                .build();
    }
}
