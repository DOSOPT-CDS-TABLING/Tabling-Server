package org.sopt.tablingServer.order.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

import org.sopt.tablingServer.order.domain.Order;

import static org.sopt.tablingServer.common.constant.Constraint.MINUTES_IN_A_DAY;
import static org.sopt.tablingServer.common.constant.Constraint.REVIEW_DEADLINE_DAYS;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record OrderListResponse(
        Long orderId,
        String orderStatus,
        Long shopId,
        String shopName,
        String orderDate,
        int personCount,
        int waitingNumber,
        int beforeCount,
        long remainingReviewPeriod
) {
    public static OrderListResponse of(Order order) {

        return new OrderListResponse(
                order.getId(),
                order.getOrderStatus().getValue(),
                order.getShopId(),
                order.getShopName(),
                formatOrderDate(order.getOrderDate()),
                order.getPersonCount(),
                order.getWaitingNumber(),
                order.getBeforeCount(),
                getRemainingReviewPeriod(order.getOrderDate())
        );
    }

    private static long getRemainingReviewPeriod(LocalDateTime orderDate) {

        long minutesDifference = ChronoUnit.MINUTES.between(LocalDateTime.now(),
                orderDate.plusDays(REVIEW_DEADLINE_DAYS));

        if (minutesDifference > 0) {
            return minutesDifference / MINUTES_IN_A_DAY;
        }
        // REVIEW_DEADLINE이 지나면 -1일이라고 표시하기 위함
        return minutesDifference / MINUTES_IN_A_DAY - 1;
    }

    private static String formatOrderDate(LocalDateTime orderDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM월 dd일 (E)", Locale.KOREA);

        return orderDate.format(formatter);
    }
}
