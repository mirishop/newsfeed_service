package com.hh.mirishop.newsfeed.newsfeed.service;

import com.hh.mirishop.newsfeed.follow.repository.FollowRepository;
import com.hh.mirishop.newsfeed.newsfeed.dto.NewsFeedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NewsFeedQueryServiceImpl implements NewsFeedQueryService {

    private final FollowRepository followRepository;
    private final MongoTemplate mongoTemplate;

    /*
    Todo: query문을 어떻게 최적화 할지 고민
    */
    @Override
    @Transactional(readOnly = true)
    public Page<NewsFeedResponse> getNewsfeedForMember(int page, int size, Long currentMemberNumber) {
//        // 팔로우 유저에 대한 정보
//        List<NewsFeed> activitiesForFollows = getActivitiesForFollowing(currentMemberNumber);
//        // 나의 글에 달린 댓글과 좋아요에 대한 정보
//        List<NewsFeed> activitiesForMyPosts = getActivitiesForMyPosts(currentMemberNumber);
//        // 다른 글에 달은 댓글과 좋아요에 대한 정보
//        List<NewsFeed> activitiesForOtherPosts = getActivitiesForOtherPosts(currentMemberNumber);
//        // 대댓글과 대댓글에 대한 좋아요에 대한 정보
//        List<NewsFeed> activitiesForReplies = getActivitiesForReplies(currentMemberNumber);
//
//        List<NewsFeed> activities = Stream.of(activitiesForFollows, activitiesForMyPosts, activitiesForOtherPosts,
//                        activitiesForReplies)
//                .flatMap(Collection::stream)
//                .toList();

        /*
        int totalActivities = activities.size();
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        List<NewsFeedResponse> newsfeedResponses = activities.stream().map(NewsFeedResponse::fromActivity).toList();

        // 팔로잉하는 유저들의 ID 목록 조회
        List<Long> followingUserIds = followingRepository.findByUserId(userId)
                .stream()
                .map(Following::getFollowingUserId)
                .collect(Collectors.toList());

        // 현재 사용자의 ID도 목록에 추가
        followingUserIds.add(userId);

        // MongoDB에서 팔로잉하는 유저들의 뉴스피드 조회
        Query query = new Query(new Criteria().orOperator(
                Criteria.where("memberNumber").in(followingUserIds)
        )).with(Sort.by(Sort.Direction.DESC, "createdAt")).limit(5);

        return mongoTemplate.find(query, "NewsFeeds");
        */


//        return new PageImpl<>(newsfeedRespons, pageable, totalActivities);
        return null;
    }
/*
    private List<NewsFeed> getActivitiesForFollowing(Long currentMemberNumber) {
        List<Follow> followings = followRepository.findAllByFollowerId(currentMemberNumber);
        List<Long> followingMemberNumbers = followings.stream()
                .map(follow -> follow.getFollowing().getNumber())
                .toList();

        Query queryForFollows = new Query(Criteria.where("memberNumber").in(followingMemberNumbers)
                .andOperator(
                        Criteria.where("isDeleted").is(false)
                ));

        return mongoTemplate.find(queryForFollows, NewsFeed.class, "activities");
    }

    private List<NewsFeed> getActivitiesForMyPosts(Long currentMemberNumber) {
        List<Long> myPostIds = postRepository.findByMemberNumberAndIsDeletedFalse(currentMemberNumber);

        Query queryForMyPosts = new Query(Criteria.where("targetPostId").in(myPostIds)
                .andOperator(
                        Criteria.where("activityType").in(NewsFeedType.COMMENT, NewsFeedType.LIKE),
                        Criteria.where("isDeleted").is(false)
                ));

        return mongoTemplate.find(queryForMyPosts, NewsFeed.class, "activities");
    }

    private List<NewsFeed> getActivitiesForOtherPosts(Long currentMemberNumber) {
        Query queryForOtherPosts = new Query(Criteria.where("memberNumber").is(currentMemberNumber)
                .andOperator(
                        Criteria.where("activityType").in(NewsFeedType.COMMENT, NewsFeedType.LIKE),
                        Criteria.where("isDeleted").is(false)
                ));
        return mongoTemplate.find(queryForOtherPosts, NewsFeed.class, "activities");
    }

    private List<NewsFeed> getActivitiesForReplies(Long currentMemberNumber) {
        System.out.println(3);
        List<Long> commentIds = commentService.findCommentIdsByMemberNumber(currentMemberNumber);

        Query queryForRepliesAndLikes = new Query(Criteria.where("parentCommentId").in(commentIds)
                .andOperator(
                        Criteria.where("activityType").in(NewsFeedType.COMMENT, NewsFeedType.LIKE),
                        Criteria.where("isDeleted").is(false)
                ));

        return mongoTemplate.find(queryForRepliesAndLikes, NewsFeed.class, "activities");
    }

    @Override
    @Transactional
    public void createActivityForLike(Like like) {
        Long postId = findRelatedPostIdForLike(like);

        NewsFeed newsfeed = NewsFeed.builder()
                .memberNumber(like.getMember().getNumber())
                .newsfeedType(NewsFeedType.LIKE)
                .activityId(like.getLikeId())
                .targetPostId(postId)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .isDeleted(false)
                .build();

        // MongoDB에 Activity 문서 저장
        mongoTemplate.save(newsfeed, "activities");
    }

    @Override
    @Transactional
    public void deleteActivityForUnlike(Like like) {
        Long postId = findRelatedPostIdForLike(like);

        // 타입이 LIKE 인 것 중에서 postId를 기준으로 삭제
        Query query = new Query(Criteria
                .where("activityType").is(NewsFeedType.LIKE)
                .and("activityId").is(postId));
        mongoTemplate.remove(query, NewsFeed.class, "activities");
    }

    @Transactional(readOnly = true)
    private Long findRelatedPostIdForLike(Like like) {
        if (like.getLikeType() == LikeType.POST) {
            return like.getItemId();
        } else if (like.getLikeType() == LikeType.COMMENT) {
            return commentService.findPostIdByCommentId(like.getItemId());
        }
        return null;
    }*/
}