package org.sopt.tablingServer.order.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.time.LocalDateTime;
import org.sopt.tablingServer.order.domain.Order;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public record OrderDetailResponse(
        Long orderId,
        String shopName,
        int waitingNumber,
        int beforeCount,
        LocalDateTime orderDate,
        int personCount,
        String orderStatus,
        int totalPrice
) {
    public static OrderDetailResponse of(Order order) {
        return new OrderDetailResponse(
                order.getId(),
                order.getShopName(),
                order.getWaitingNumber(),
                order.getBeforeCount(),
                order.getOrderDate(),
                order.getPersonCount(),
                order.getOrderStatus().getValue(),
                order.getTotalPrice()
        );
    }
}
