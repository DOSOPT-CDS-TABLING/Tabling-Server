package org.sopt.tablingServer.order.application;

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

    public List<OrderListResponse> findOrders() {
        return orderJpaRepository.findAll()
                .stream()
                .map(OrderListResponse::of)
                .collect(Collectors.toList());
    }

    public OrderDetailResponse findOne(Long orderId) {
        return OrderDetailResponse.of(orderJpaRepository.findByIdOrThrow(orderId));
    }
}
