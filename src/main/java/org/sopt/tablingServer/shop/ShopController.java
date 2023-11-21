package org.sopt.tablingServer.shop;

import lombok.RequiredArgsConstructor;
import org.sopt.tablingServer.common.dto.ApiResponse;
import org.sopt.tablingServer.shop.dto.response.ShopDetailResponse;
import org.sopt.tablingServer.shop.dto.response.ShopResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.sopt.tablingServer.common.exception.model.SuccessType.GET_SHOP_DETAIL_SUCCESS;
import static org.sopt.tablingServer.common.exception.model.SuccessType.GET_SHOP_LIST_BY_AVERAGE_WAITING_SUCCESS;

@RestController
@RequestMapping("/shops")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @GetMapping
    public ApiResponse<List<ShopResponse>> getShopList() {
        return ApiResponse.success(GET_SHOP_LIST_BY_AVERAGE_WAITING_SUCCESS, shopService.getShopListByAverageWaiting());
    }

    @GetMapping("/{shopId}")
    public ApiResponse<ShopDetailResponse> getShopDetail(@PathVariable Long shopId) {
        return ApiResponse.success(GET_SHOP_DETAIL_SUCCESS, shopService.getShopDetailInfo(shopId));
    }
}
