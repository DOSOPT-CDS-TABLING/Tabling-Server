package org.sopt.tablingServer.order.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.sopt.tablingServer.order.domain.Order;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record OrderCompleteResponse(
        Long orderId,
        int waitingNumber,
        int beforeCount,
        String shopName,
        int personCount,
        String orderStatus
) {
    public static OrderCompleteResponse of(Order order) {
        return new OrderCompleteResponse(
                order.getId(),
                order.getWaitingNumber(),
                order.getBeforeCount(),
                order.getShopName(),
                order.getPersonCount(),
                order.getOrderStatus().getValue()
        );
    }
}
