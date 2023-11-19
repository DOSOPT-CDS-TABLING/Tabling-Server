package org.sopt.tablingServer.order.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum OrderStatus {

    RESERVED("이용 예정"),
    COMPLETED("이용 완료");

    private final String value;
}