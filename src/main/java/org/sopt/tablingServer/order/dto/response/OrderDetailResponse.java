package org.sopt.tablingServer.order.dto.response;

import org.sopt.tablingServer.order.domain.Order;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public record OrderDetailResponse(
        Long orderId,
        String shopName,
        int waitingNumber,
        int beforeCount,
        String orderDate,
        int personCount,
        String orderStatus,
        int totalPrice,
        String requestContent
) {
    public static OrderDetailResponse of(Order order) {
        return new OrderDetailResponse(
                order.getId(),
                order.getShopName(),
                order.getWaitingNumber(),
                order.getBeforeCount(),
                formatOrderDate(order.getOrderDate()),
                order.getPersonCount(),
                order.getOrderStatus().getValue(),
                order.getTotalPrice(),
                order.getRequestContent()
        );
    }

    private static String formatOrderDate(LocalDateTime orderDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd (E) HH:mm", Locale.KOREA);

        return orderDate.format(formatter);
    }
}
