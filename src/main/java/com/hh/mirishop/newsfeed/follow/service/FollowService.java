package com.hh.mirishop.newsfeed.follow.service;

import com.hh.mirishop.newsfeed.follow.dto.FollowRequest;


public interface FollowService {

    void follow(FollowRequest followRequest, Long currentMemberNumber);

    void unfollow(FollowRequest followRequest, Long currentMemberNumber);
}
