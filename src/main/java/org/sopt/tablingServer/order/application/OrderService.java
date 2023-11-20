package org.sopt.tablingServer.order.application;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.sopt.tablingServer.order.dto.response.OrderResponse;
import org.sopt.tablingServer.order.infrastructure.OrderJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final OrderJpaRepository orderJpaRepository;

    public List<OrderResponse> getOrders() {
        return orderJpaRepository.findAll()
                .stream()
                .map(OrderResponse::of)
                .collect(Collectors.toList());
    }
}
