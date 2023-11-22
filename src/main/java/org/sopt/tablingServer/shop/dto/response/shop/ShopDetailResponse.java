package org.sopt.tablingServer.shop.dto.response.shop;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.sopt.tablingServer.shop.domain.Review;
import org.sopt.tablingServer.shop.domain.Shop;
import org.sopt.tablingServer.shop.dto.response.review.ReviewResponse;
import org.sopt.tablingServer.shop.dto.response.menu.MenuResponse;

import java.util.List;
import java.util.stream.Collectors;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ShopDetailResponse(
        // 기본 정보
        Long shopId,
        List<String> detailPhotoList,
        String name,
        String longAddress,

        // 영업 정보
        String salesTime,
        String waitingTime,
        String restTime,
        String restDay,
        String phoneNumber,

        // 해시 태그 (매장 PICK!), 매장 소개
        List<String> hashTagList,
        String introduceContent,

        // 전체 메뉴
        List<MenuResponse> menuList,

        // 리뷰 정보
        float averageStar,
        int reviewCount,
        List<Float> detailStarList,
        List<ReviewResponse> reviewList
) {
    public static ShopDetailResponse of(Shop shop) {
        return new ShopDetailResponse(
                shop.getId(),
                shop.getDetailPhotoList(),
                shop.getName(),
                shop.getLongAddress(),

                shop.getSalesInfo().getSalesTime(),
                shop.getSalesInfo().getWaitingTime(),
                shop.getSalesInfo().getRestTime(),
                shop.getSalesInfo().getRestDay(),
                shop.getSalesInfo().getPhoneNumber(),

                shop.getHashTagList(),
                shop.getIntroduceContent(),

                MenuResponse.of(shop.getMenuList()),

                shop.getAverageStar(),
                shop.getReviewCount(),
                shop.getDetailStarList(),
                convertReviewsToResponses(shop.getReviewList())
        );
    }

    private static List<ReviewResponse> convertReviewsToResponses(List<Review> reviewList) {
        return reviewList.stream()
                .map(ReviewResponse::of)
                .collect(Collectors.toList());
    }
}
