package org.sopt.tablingServer.order.presentation;

import org.sopt.tablingServer.common.dto.ApiResponse;
import org.sopt.tablingServer.order.application.OrderService;
import org.sopt.tablingServer.order.dto.request.OrderReserveRequest;
import org.sopt.tablingServer.order.dto.response.OrderReserveResponse;
import org.sopt.tablingServer.order.dto.response.OrderDetailResponse;
import org.sopt.tablingServer.order.dto.response.OrderListResponse;

import java.util.List;
import lombok.RequiredArgsConstructor;

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

    @PostMapping("/reserve")
    public ApiResponse<OrderReserveResponse> orderReserve(@RequestBody OrderReserveRequest request) {
        return ApiResponse.success(RESERVE_ORDER_SUCCESS, orderService.createOrder(request));
    }
}
