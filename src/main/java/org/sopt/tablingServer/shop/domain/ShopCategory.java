package org.sopt.tablingServer.shop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ShopCategory {

    ITALIAN("양식"),
    KOREAN("한식");

    private final String value;

}
