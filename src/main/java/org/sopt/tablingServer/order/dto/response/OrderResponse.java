package org.sopt.tablingServer.order.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public record ShopResponse(
        Long orderId,
        String orderStatus,
        String shopName,
        int personCount,
        int waitingNumber,
        
) {

}
