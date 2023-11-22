package org.sopt.tablingServer.order.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

//request record에서는 @JsonNaming 적용 불가능
public record OrderReserveRequest(
        @JsonProperty("shop_id")
        Long shopId,

        @JsonProperty("person_count")
        int personCount
) {
}
