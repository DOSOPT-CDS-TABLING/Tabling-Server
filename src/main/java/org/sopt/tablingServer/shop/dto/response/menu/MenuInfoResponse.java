package org.sopt.tablingServer.shop.dto.response.menu;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.sopt.tablingServer.shop.domain.Menu;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record MenuInfoResponse(
        Long menuId,
        String menuPhotoUrl,
        String menuName,
        int price
) {
    public static MenuInfoResponse of(Menu menu) {
        return new MenuInfoResponse(
                menu.getId(),
                menu.getMenuPhotoUrl(),
                menu.getMenuName(),
                menu.getPrice()
        );
    }
}
