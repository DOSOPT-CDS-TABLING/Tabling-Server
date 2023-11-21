package org.sopt.tablingServer.order.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.sopt.tablingServer.order.dto.response.OrderDetailResponse;
import org.sopt.tablingServer.order.dto.response.OrderListResponse;
import org.sopt.tablingServer.order.infrastructure.OrderJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final OrderJpaRepository orderJpaRepository;

    public List<OrderListResponse> findOrderList() {
        return orderJpaRepository.findAll()
                .stream()
                .map(OrderListResponse::of)
                .collect(Collectors.toList());
    }

    public OrderDetailResponse findOrder(Long orderId) {
        return OrderDetailResponse.of(orderJpaRepository.findByIdOrThrow(orderId));
    }
}
