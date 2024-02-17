package com.hh.mirishop.newsfeed.newsfeed.controller;

import com.hh.mirishop.newsfeed.newsfeed.dto.NewsFeedCreate;
import com.hh.mirishop.newsfeed.newsfeed.service.NewsFeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/internal/newsfeeds")
@RequiredArgsConstructor
public class InternalNewsFeedController {

    private final NewsFeedService newsFeedService;

    @PostMapping
    public ResponseEntity<Void> createNewsfeed(@RequestBody NewsFeedCreate newsFeedCreate) {
        newsFeedService.createNewsfeed(newsFeedCreate);

        return null;
    }
}
