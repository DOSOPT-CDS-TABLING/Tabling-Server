package org.sopt.tablingServer.order.controller;

import jakarta.validation.Valid;
import org.sopt.tablingServer.common.dto.ApiResponse;
import org.sopt.tablingServer.order.dto.request.OrderReserveRequest;
import org.sopt.tablingServer.order.dto.response.OrderReserveResponse;
import org.sopt.tablingServer.order.dto.response.OrderDetailResponse;
import org.sopt.tablingServer.order.dto.response.OrderListResponse;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

import org.sopt.tablingServer.order.service.OrderService;
import org.springframework.web.bind.annotation.*;

import static org.sopt.tablingServer.common.exception.model.SuccessType.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ApiResponse<List<OrderListResponse>> orderList() {
        List<OrderListResponse> responses = orderService.findOrderList()
                .stream()
                .map(OrderListResponse::of)
                .collect(Collectors.toList());

        return ApiResponse.success(GET_ORDER_LIST_SUCCESS, responses);
    }

    @GetMapping("/{orderId}")
    public ApiResponse<OrderDetailResponse> orderDetail(@PathVariable Long orderId) {
        OrderDetailResponse response = OrderDetailResponse.of(orderService.findOrder(orderId));
        return ApiResponse.success(GET_ORDER_DETAIL_SUCCESS, response);
    }

    @PostMapping("/reserve")
    public ApiResponse<OrderReserveResponse> orderReserve(@RequestBody @Valid OrderReserveRequest request) {
        OrderReserveResponse response = OrderReserveResponse.of(orderService.createOrder(request));
        return ApiResponse.success(RESERVE_ORDER_SUCCESS, response);
    }
}