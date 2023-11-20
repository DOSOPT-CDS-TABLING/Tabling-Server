package org.sopt.tablingServer.order.presentation;

import java.util.List;
import lombok.RequiredArgsConstructor;

import org.sopt.tablingServer.common.dto.ApiResponse;
import org.sopt.tablingServer.order.application.OrderService;
import org.sopt.tablingServer.order.dto.response.OrderResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.sopt.tablingServer.common.exception.model.SuccessType.GET_ORDER_LIST_SUCCESS;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    public ApiResponse<List<OrderResponse>> getOrderList() {
        return ApiResponse.success(GET_ORDER_LIST_SUCCESS, orderService.getOrders());
    }
}
