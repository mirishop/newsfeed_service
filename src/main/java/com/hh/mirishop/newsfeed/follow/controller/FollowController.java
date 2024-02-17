package com.hh.mirishop.newsfeed.follow.controller;

import com.hh.mirishop.newsfeed.common.dto.BaseResponse;
import com.hh.mirishop.newsfeed.follow.dto.FollowRequest;
import com.hh.mirishop.newsfeed.follow.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/follows")
public class FollowController {

    private final FollowService followService;

    @PostMapping
    public ResponseEntity<BaseResponse<Void>> follow(@RequestBody final FollowRequest followRequest,
                                                     @RequestParam(name = "member") Long currentMemberNumber) {
        followService.follow(followRequest, currentMemberNumber);

        return new ResponseEntity<>(BaseResponse.of("팔로우 추가", true, null),
                HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<BaseResponse<Void>> unfollow(@RequestBody final FollowRequest followRequest,
                                                       @RequestParam(name = "member") Long currentMemberNumber) {
        followService.unfollow(followRequest, currentMemberNumber);

        return new ResponseEntity<>(BaseResponse.of("언팔로우", true, null), HttpStatus.OK);
    }
}
