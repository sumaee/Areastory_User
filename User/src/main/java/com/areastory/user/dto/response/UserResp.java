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
public class UserResp {

    private Long userId;
    private String nickname;
    private String profile;
    private Long followCount;
    private Long followingCount;
    private Boolean isExist;

    public static UserResp fromEntity(UserInfo user) {
        return UserResp.builder()
                .userId(user.getUserId())
                .nickname(user.getNickname())
                .profile(user.getProfile())
                .followCount(user.getFollowCount())
                .followingCount(user.getFollowingCount())
                .isExist(true)
                .build();
    }

    public static UserResp fromEntity(UserInfo user, Boolean isExist) {
        return UserResp.builder()
                .userId(user.getUserId())
                .nickname(user.getNickname())
                .profile(user.getProfile())
                .followCount(user.getFollowCount())
                .followingCount(user.getFollowingCount())
                .isExist(isExist)
                .build();
    }

    public static UserResp fromEntity(Boolean isExist) {
        return UserResp.builder()
                .isExist(isExist)
                .build();
    }
}
