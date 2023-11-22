package org.sopt.tablingServer.shop.controller;

import lombok.RequiredArgsConstructor;
import org.sopt.tablingServer.common.dto.ApiResponse;
import org.sopt.tablingServer.shop.service.ShopService;
import org.sopt.tablingServer.shop.dto.response.shop.ShopDetailResponse;
import org.sopt.tablingServer.shop.dto.response.shop.ShopResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.sopt.tablingServer.common.exception.model.SuccessType.GET_SHOP_DETAIL_SUCCESS;
import static org.sopt.tablingServer.common.exception.model.SuccessType.GET_SHOP_LIST_BY_AVERAGE_WAITING_SUCCESS;

@RestController
@RequestMapping("/shops")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @GetMapping
    public ApiResponse<List<ShopResponse>> shopList() {
        List<ShopResponse> response = shopService.findShopListOrderByAverageWaiting()
                .stream()
                .map(ShopResponse::of)
                .collect(Collectors.toList());

        return ApiResponse.success(GET_SHOP_LIST_BY_AVERAGE_WAITING_SUCCESS, response);
    }

    @GetMapping("/{shopId}")
    public ApiResponse<ShopDetailResponse> shopDetail(@PathVariable Long shopId) {
        ShopDetailResponse response = ShopDetailResponse.of(shopService.findShopDetailInfo(shopId));

        return ApiResponse.success(GET_SHOP_DETAIL_SUCCESS, response);
    }
}
