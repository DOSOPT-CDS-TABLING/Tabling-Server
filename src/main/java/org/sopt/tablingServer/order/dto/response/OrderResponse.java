package org.sopt.tablingServer.order.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import org.sopt.tablingServer.order.domain.Order;

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

        return new OrderResponse(
                order.getId(),
                order.getOrderStatus().getValue(),
                order.getShopName(),
                order.getPersonCount(),
                order.getWaitingNumber(),
                order.getBeforeCount(),
                getRemainingReviewPeriod(order.getOrderDate())
        );
    }

    private static long getRemainingReviewPeriod(LocalDateTime orderDate) {
        final int REVIEW_DEADLINE_DAYS = 4;
        final int MINUTES_IN_A_DAY = 1440;

        long minutesDifference = ChronoUnit.MINUTES.between(LocalDateTime.now(),
                orderDate.plusDays(REVIEW_DEADLINE_DAYS));

        if (minutesDifference > 0) {
            return minutesDifference / MINUTES_IN_A_DAY;
        }
        // REVIEW_DEADLINE이 지나면 -1일이라고 표시하기 위함
        return minutesDifference / MINUTES_IN_A_DAY - 1;
    }
}
