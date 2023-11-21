package org.sopt.tablingServer.order.presentation;

import java.util.List;
import lombok.RequiredArgsConstructor;

import org.sopt.tablingServer.common.dto.ApiResponse;
import org.sopt.tablingServer.order.application.OrderService;
import org.sopt.tablingServer.order.dto.response.OrderDetailResponse;
import org.sopt.tablingServer.order.dto.response.OrderListResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.sopt.tablingServer.common.exception.model.SuccessType.GET_ORDER_DETAIL_SUCCESS;
import static org.sopt.tablingServer.common.exception.model.SuccessType.GET_ORDER_LIST_SUCCESS;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ApiResponse<List<OrderListResponse>> getOrderList() {
        return ApiResponse.success(GET_ORDER_LIST_SUCCESS, orderService.getOrders());
    }

    @GetMapping("/{orderId}")
    public ApiResponse<OrderDetailResponse> getOrderDetail(@PathVariable Long orderId) {
        return ApiResponse.success(GET_ORDER_DETAIL_SUCCESS, orderService.getOrderByOrderId(orderId));
    }
}
