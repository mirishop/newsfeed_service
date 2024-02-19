package com.hh.mirishop.newsfeed.newsfeed.service;

import com.hh.mirishop.newsfeed.newsfeed.domain.NewsFeedType;
import com.hh.mirishop.newsfeed.newsfeed.dto.NewsFeedCreate;
import com.hh.mirishop.newsfeed.newsfeed.dto.NewsFeedDelete;
import com.hh.mirishop.newsfeed.newsfeed.dto.NewsFeedUpdate;
import com.hh.mirishop.newsfeed.newsfeed.entity.NewsFeed;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NewsFeedServiceImpl implements NewsFeedService {

    private final MongoTemplate mongoTemplate;

    /*
    뉴스피드 저장
    */
    @Override
    @Transactional
    public void createNewsFeed(NewsFeedCreate newsfeedCreate) {

        NewsFeed newsFeed =
                NewsFeed.builder()
                        .memberNumber(newsfeedCreate.getMemberNumber())
                        .newsfeedType(NewsFeedType.of(newsfeedCreate.getNewsFeedType()))
                        .activityId(newsfeedCreate.getActivityId())
                        .targetPostId(newsfeedCreate.getTargetPostId())
                        .createdAt(newsfeedCreate.getCreatedAt())
                        .updatedAt(newsfeedCreate.getUpdatedAt())
                        .isDeleted(newsfeedCreate.getIsDeleted())
                        .build();

        mongoTemplate.save(newsFeed,"newsfeeds");
    }

    /*
    뉴스피드 수정
    */
    @Override
    public void updateNewsFeed(NewsFeedUpdate newsFeedUpdate) {
        Query query = new Query(Criteria.where("activityId").is(newsFeedUpdate.getActivityId())
                .and("newsfeedType").is(NewsFeedType.of(newsFeedUpdate.getNewsFeedType())));
        Update update = new Update();
        update.set("updatedAt", newsFeedUpdate.getUpdatedAt());

        mongoTemplate.updateFirst(query, update, "newsfeeds");
    }

    @Override
    public void deleteNewsFeed(NewsFeedDelete newsFeedDelete) {
        Query query = new Query(Criteria.where("activityId").is(newsFeedDelete.getActivityId())
                .and("newsfeedType").is(NewsFeedType.of(newsFeedDelete.getNewsFeedType())));
        Update update = new Update();
        update.set("is_deleted", true);

        mongoTemplate.updateFirst(query, update, "newsfeeds");
    }
}
