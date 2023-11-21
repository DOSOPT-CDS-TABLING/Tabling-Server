package org.sopt.tablingServer.order.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import org.sopt.tablingServer.order.domain.Order;
import org.sopt.tablingServer.order.domain.OrderStatus;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public record OrderResponse(
        Long orderId,
        String orderStatus,
        String shopName,
        int personCount,
        int waitingNumber,
        int beforeCount,
        long remainingReviewPeriod
) {
    public static OrderResponse of(Order order) {
        final int REVIEW_DEADLINE_DAYS = 3;
        return new OrderResponse(
                order.getId(),
                order.getOrderStatus().getValue(),
                order.getShopName(),
                order.getPersonCount(),
                order.getWaitingNumber(),
                order.getBeforeCount(),
                ChronoUnit.DAYS.between(LocalDateTime.now(), order.getOrderDate().plusDays(REVIEW_DEADLINE_DAYS))
        );
    }
}
