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
    //== DEFAULT ==//
    PROCESS_SUCCESS(HttpStatus.OK, "OK"),

    //== ORDER ==//
    GET_ORDER_LIST_SUCCESS(HttpStatus.OK,"주문 내역의 목록 반환이 완료되었습니다."),
    GET_ORDER_DETAIL_SUCCESS(HttpStatus.OK, "주문 내역의 상세 정보 반환이 완료되었습니다."),
    UPDATE_ORDER_STATUS_COMPLETE_SUCCESS(HttpStatus.OK, "주문 내역의 신청 상태를 이용완료로 변경하였습니다."),

    //== SHOP ==//
    GET_SHOP_LIST_BY_AVERAGE_WAITING_SUCCESS(HttpStatus.OK, "일 평균 대기인원을 기준으로 서울 남부 인기 매장 리스트 반환이 완료되었습니다."),
    GET_SHOP_DETAIL_SUCCESS(HttpStatus.OK, "매장 상세 정보 반환이 완료되었습니다."),
    RESERVE_ORDER_SUCCESS(HttpStatus.OK, "원격 줄서기 신청이 완료되었습니다."),

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
