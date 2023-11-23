package org.sopt.tablingServer.order.controller;

import jakarta.validation.Valid;
import org.sopt.tablingServer.common.dto.ApiResponse;
import org.sopt.tablingServer.order.dto.response.OrderReserveResponse;
import org.sopt.tablingServer.order.dto.response.OrderDetailResponse;
import org.sopt.tablingServer.order.dto.response.OrderListResponse;
import org.sopt.tablingServer.order.dto.response.OrderCompleteResponse;

import org.sopt.tablingServer.order.dto.request.OrderReserveRequest;
import org.sopt.tablingServer.order.dto.request.OrderCompleteRequest;

import org.sopt.tablingServer.order.service.OrderService;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.sopt.tablingServer.common.exception.model.SuccessType.GET_ORDER_DETAIL_SUCCESS;
import static org.sopt.tablingServer.common.exception.model.SuccessType.GET_ORDER_LIST_SUCCESS;
import static org.sopt.tablingServer.common.exception.model.SuccessType.UPDATE_ORDER_STATUS_COMPLETE_SUCCESS;

import org.springframework.web.bind.annotation.*;

import static org.sopt.tablingServer.common.exception.model.SuccessType.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ApiResponse<List<OrderListResponse>> orderList() {
        return ApiResponse.success(GET_ORDER_LIST_SUCCESS, orderService.findOrderList());
    }

    @GetMapping("/{orderId}")
    public ApiResponse<OrderDetailResponse> orderDetail(@PathVariable Long orderId) {
        return ApiResponse.success(GET_ORDER_DETAIL_SUCCESS, orderService.findOrder(orderId));
    }

    @PatchMapping("/complete")
    public ApiResponse<OrderCompleteResponse> completeOrderStatus(@RequestBody OrderCompleteRequest request) {
        return ApiResponse.success(UPDATE_ORDER_STATUS_COMPLETE_SUCCESS, orderService.updateOrderStatusComplete(request.orderId()));
    }

    @PostMapping("/reserve")
    public ApiResponse<OrderReserveResponse> orderReserve(@RequestBody @Valid OrderReserveRequest request) {
        return ApiResponse.success(RESERVE_ORDER_SUCCESS, orderService.createOrder(request.shopId(), request.personCount()));
    }
}
