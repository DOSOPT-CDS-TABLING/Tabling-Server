package org.sopt.tablingServer.common.exception.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessType {

    /**
     * 200 OK
     */
    PROCESS_SUCCESS(HttpStatus.OK, "OK"),
    GET_ORDER_LIST_SUCCESS(HttpStatus.OK,"주문 내역의 목록 반환이 완료되었습니다.")

    /**
     * 201 CREATED
     */

    /**
     * 204 NO CONTENT
     */
    ;

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
