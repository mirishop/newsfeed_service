package com.hh.mirishop.newsfeed.newsfeed.controller;

import com.hh.mirishop.newsfeed.common.dto.BaseResponse;
import com.hh.mirishop.newsfeed.newsfeed.dto.ActivityResponse;
import com.hh.mirishop.newsfeed.newsfeed.service.NewsFeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/newsfeed")
@RequiredArgsConstructor
public class NewsFeedController {

    private final NewsFeedService newsFeedService;

    @GetMapping("/my")
    public ResponseEntity<BaseResponse<Page<ActivityResponse>>> getMyNewsFeed(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam(name = "member") Long currentMemberNumber) {
        Page<ActivityResponse> newsFeed = newsFeedService.getNewsfeedForMember(page - 1, size, currentMemberNumber);

        return new ResponseEntity<>(BaseResponse.of("뉴스피드 조회 성공", true, newsFeed), HttpStatus.OK);
    }
}
