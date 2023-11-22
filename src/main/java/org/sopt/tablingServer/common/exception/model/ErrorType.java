package org.sopt.tablingServer.common.exception.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorType {

    /**
     * 400 BAD REQUEST
     */
    ORDER_ALREADY_COMPLETED(HttpStatus.BAD_REQUEST,"이미 이용 완료된 주문입니다."),

    /**
     * 404 NOT FOUND
     */
    NOT_FOUND_MEMBER_ERROR(HttpStatus.NOT_FOUND, "존재하지 않는 멤버입니다"),
    NOT_FOUND_ORDER_ERROR(HttpStatus.NOT_FOUND, "일치하는 주문 내역 정보가 없습니다"),
    NOT_FOUND_SHOP_ERROR(HttpStatus.NOT_FOUND, "일치하는 매장 정보가 없습니다"),

  /**
     * 500 INTERNAL SERVER ERROR
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 서버 에러가 발생했습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
