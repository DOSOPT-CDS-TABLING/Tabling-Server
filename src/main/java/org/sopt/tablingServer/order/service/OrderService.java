package org.sopt.tablingServer.order.service;

import org.sopt.tablingServer.order.domain.OrderStatus;
import org.sopt.tablingServer.order.dto.response.OrderCompleteResponse;
import org.sopt.tablingServer.order.dto.response.OrderDetailResponse;
import org.sopt.tablingServer.order.dto.response.OrderListResponse;
import org.sopt.tablingServer.order.dto.response.OrderReserveResponse;
import org.sopt.tablingServer.order.infrastructure.OrderJpaRepository;
import org.sopt.tablingServer.shop.infrastructure.ShopJpaRepository;

import org.sopt.tablingServer.order.domain.Order;
import org.sopt.tablingServer.shop.domain.Shop;

import org.sopt.tablingServer.common.exception.model.BusinessException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

import static java.util.Comparator.*;
import static org.sopt.tablingServer.common.constant.Constraint.*;
import static org.sopt.tablingServer.common.constant.DefaultString.DEFAULT_REQUEST_CONTENT;
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
                // 먼저 OrderStatus가 RESERVED인 경우와 아닌 경우로 분리하여 정렬
                .sorted(Comparator.comparing(
                                (Order order) -> order.getOrderStatus() != OrderStatus.RESERVED)
                        // RESERVED 상태이면 getOrderDate를 기준으로 정렬, 아니면 getUpdatedAt을 기준으로 정렬
                        .thenComparing(
                                order -> order.getOrderStatus() == OrderStatus.RESERVED ?
                                        order.getOrderDate() : order.getUpdatedAt(),
                                Comparator.reverseOrder()))
                .map(OrderListResponse::of)
                .collect(Collectors.toList());
    }

    public OrderDetailResponse findOrder(Long orderId) {
        return OrderDetailResponse.of(orderJpaRepository.findByIdOrThrow(orderId));
    }

    @Transactional
    public OrderCompleteResponse updateOrderStatusComplete(Long orderId) {
        Order orderToUpdate = orderJpaRepository.findByIdOrThrow(orderId);
        orderToUpdate.changeOrderStatusComplete();
        return OrderCompleteResponse.of(orderToUpdate);
    }

    @Transactional
    public OrderReserveResponse createOrder(Long shopId, int personCount) {
        if (personCount > MAX_PERSON_COUNT) {
            throw new BusinessException(TOO_MANY_PERSON_COUNT_ERROR);
        }
        Shop targetShop = shopJpaRepository.findByIdOrThrow(shopId);

        RandomResult result = getRandomResult();

        // 기왕 랜덤 생성한 김에 Shop에서도 수정
        // 이부분은 실제로는 동시성 이슈가 발생할 수 있어서, 기존의 값에 더하는 식으로 업데이트 해주거나
        // Shop에서 컬럼으로 관리 안하고 Order 테이블의 레코드를 활용해 계산하면 좋을 것임
        targetShop.changeCurrentWaiting(result.beforeCount() + 1);

        Order order = Order.builder()
                .orderStatus(RESERVED)
                .shopId(targetShop.getId())
                .shopName(targetShop.getName())
                .personCount(personCount)
                .waitingNumber(result.waitingNumber())
                .beforeCount(result.beforeCount())
                .totalPrice(result.totalPrice())
                .requestContent(DEFAULT_REQUEST_CONTENT)
                .orderDate(LocalDateTime.now())
                .build();

        orderJpaRepository.save(order);

        return OrderReserveResponse.of(orderJpaRepository.findByIdOrThrow(order.getId()));
    }

    private static RandomResult getRandomResult() {
        Random random = new Random();
        int waitingNumber = 1 + random.nextInt(MAX_PERSON_COUNT); // 1 to 99
        int beforeCount = 1 + random.nextInt(MAX_BEFORE_COUNT); // 1 to 20
        int totalPrice = 10000 + random.nextInt(MAX_PRICE_STEP) * 1000; // 10000 to 30000 in steps of 1000

        return new RandomResult(waitingNumber, beforeCount, totalPrice);
    }

    private record RandomResult(int waitingNumber, int beforeCount, int totalPrice) {
    }
}
