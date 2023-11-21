package org.sopt.tablingServer.order.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.sopt.tablingServer.order.domain.Order;

import static org.sopt.tablingServer.common.constant.DefaultString.DEFAULT_ORDER_STATUS;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record OrderReserveResponse(
        Long orderId,
        int waitingNumber,
        int beforeCount,
        String shopName,
        int personCount,
        String orderStatus
) {
    public static OrderReserveResponse of(Order order) {
        return new OrderReserveResponse(
                order.getId(),
                order.getWaitingNumber(),
                order.getBeforeCount(),
                order.getShopName(),
                order.getPersonCount(),
                DEFAULT_ORDER_STATUS
        );
    }
}
