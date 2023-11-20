package org.sopt.tablingServer.shop.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.sopt.tablingServer.shop.domain.Menu;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record MenuResponse(
        String menuCategory,
        List<MenuInfoResponse> menuInfoList
) {
    public static List<MenuResponse> of(List<Menu> menuList) {
        Map<String, List<MenuInfoResponse>> groupedMenus = menuList.stream()
                .collect(Collectors.groupingBy(
                        Menu::getMenuCategory,
                        Collectors.mapping(
                                MenuInfoResponse::of,
                                Collectors.toList()
                        )
                ));

        return groupedMenus.entrySet().stream()
                .map(entry -> new MenuResponse(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
