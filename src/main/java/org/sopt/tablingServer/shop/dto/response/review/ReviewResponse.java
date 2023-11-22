package org.sopt.tablingServer.shop.dto.response.review;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.sopt.tablingServer.shop.domain.Review;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ReviewResponse(
        Long reviewId,
        float star,
        String reviewerName,
        int dayBefore,
        String reviewContent
) {
    public static ReviewResponse of(Review review) {
        return new ReviewResponse(
                review.getId(),
                review.getStar(),
                review.getReviewerName(),
                review.getDayBefore(),
                review.getReviewContent()
        );
    }
}
