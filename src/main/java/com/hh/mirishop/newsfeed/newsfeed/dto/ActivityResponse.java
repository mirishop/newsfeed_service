package com.hh.mirishop.newsfeed.newsfeed.dto;

import com.hh.mirishop.newsfeed.newsfeed.entity.Activity;
import com.hh.mirishop.newsfeed.newsfeed.domain.ActivityType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ActivityResponse {

    private String id;
    private Long memberNumber;
    private ActivityType activityType;
    private Long activityId;
    private Long targetPostId;
    private LocalDateTime createdAt;
    private Boolean isDeleted;

    public static ActivityResponse fromActivity(Activity activity) {
        return new ActivityResponse(
                activity.getId(),
                activity.getMemberNumber(),
                activity.getActivityType(),
                activity.getActivityId(),
                activity.getTargetPostId(),
                activity.getCreatedAt(),
                activity.getIsDeleted()
        );
    }
}
