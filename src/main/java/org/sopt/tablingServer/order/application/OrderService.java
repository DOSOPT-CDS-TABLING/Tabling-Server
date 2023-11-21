package org.sopt.tablingServer.order.application;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.sopt.tablingServer.common.exception.model.BusinessException;
import org.sopt.tablingServer.order.domain.Order;
import org.sopt.tablingServer.order.dto.request.OrderReserveRequest;
import org.sopt.tablingServer.order.dto.response.OrderDetailResponse;
import org.sopt.tablingServer.order.dto.response.OrderListResponse;
import org.sopt.tablingServer.order.dto.response.OrderReserveResponse;
import org.sopt.tablingServer.order.infrastructure.OrderJpaRepository;
import org.sopt.tablingServer.shop.domain.Shop;
import org.sopt.tablingServer.shop.infrastructure.ShopJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.sopt.tablingServer.common.exception.model.ErrorType.TOO_MANY_PERSON_COUNT_ERROR;
import static org.sopt.tablingServer.order.domain.OrderStatus.RESERVED;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final OrderJpaRepository orderJpaRepository;
    private final ShopJpaRepository shopJpaRepository;

    public List<OrderListResponse> findOrderList() {
        return orderJpaRepository.findAll()
                .stream()
                .map(OrderListResponse::of)
                .collect(Collectors.toList());
    }

    public OrderDetailResponse findOrder(Long orderId) {
        return OrderDetailResponse.of(orderJpaRepository.findByIdOrThrow(orderId));
    }

    @Transactional
    public OrderReserveResponse createOrder(OrderReserveRequest request) {
        Shop targetShop = shopJpaRepository.findByIdOrThrow(request.shopId());
        if (request.personCount() > 99) {
            throw new BusinessException(TOO_MANY_PERSON_COUNT_ERROR);
        }

        Random random = new Random();
        int waitingNumber = 1 + random.nextInt(99); // 1 to 99
        int beforeCount = 1 + random.nextInt(20); // 1 to 20
        int totalPrice = 10000 + random.nextInt(20) * 1000; // 10000 to 30000 in steps of 1000

        // 기왕 랜덤 생성한 김에 Shop에서도 수정
        targetShop.changeCurrentWaiting(beforeCount + 1);

        Order order = Order.builder()
                .orderStatus(RESERVED)
                .shopName(targetShop.getName())
                .personCount(request.personCount())
                .waitingNumber(waitingNumber)
                .beforeCount(beforeCount)
                .totalPrice(totalPrice)
                .requestContent("테이크아웃 하겠습니다!")
                .orderDate(LocalDateTime.now())
                .build();

        orderJpaRepository.save(order);

        return OrderReserveResponse.of(order);
    }
}
