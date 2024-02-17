package com.hh.mirishop.newsfeed.newsfeed.service;

import com.hh.mirishop.newsfeed.like.entity.Like;
import com.hh.mirishop.newsfeed.auth.domain.UserDetailsImpl;
import com.hh.mirishop.newsfeed.newsfeed.dto.ActivityResponse;
import org.springframework.data.domain.Page;

public interface NewsFeedService {

    Page<ActivityResponse> getNewsfeedForMember(int page, int size, Long currentMemberNumber);

    void createActivityForLike(Like like);

    void deleteActivityForUnlike(Like like);
}
