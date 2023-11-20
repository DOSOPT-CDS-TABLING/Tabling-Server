package org.sopt.tablingServer.shop.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.sopt.tablingServer.shop.domain.Shop;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ShopResponse(
        String name,
        float averageStar,
        int reviewCount,
        String shopCategory,
        String shortAddress,
        int averageWaiting,
        int currentWaiting,
        String profilePhotoUrl
) {

    public static ShopResponse of(Shop shop) {
        return new ShopResponse(
                shop.getName(),
                shop.getAverageStar(),
                shop.getReviewCount(),
                shop.getShopCategory().getValue(),
                shop.getShortAddress(),
                shop.getAverageWaiting(),
                shop.getCurrentWaiting(),
                shop.getProfilePhotoUrl()
        );
    }
}
