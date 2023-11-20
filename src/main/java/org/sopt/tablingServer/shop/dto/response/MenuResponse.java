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
    // 메뉴의 카테고리에 따라 그룹핑 하여 반환
    public static List<MenuResponse> of(List<Menu> menuList) {
        Map<String, List<MenuInfoResponse>> groupedMenuList = menuList.stream()
                .collect(Collectors.groupingBy(
                        Menu::getMenuCategory,
                        Collectors.mapping(
                                MenuInfoResponse::of,
                                Collectors.toList()
                        )
                ));

        return groupedMenuList.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> new MenuResponse(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
