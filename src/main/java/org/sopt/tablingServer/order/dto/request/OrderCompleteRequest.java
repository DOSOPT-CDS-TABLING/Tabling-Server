package org.sopt.tablingServer.order.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OrderCompleteRequest(
        @JsonProperty("order_id")
        Long orderId
) {
}
