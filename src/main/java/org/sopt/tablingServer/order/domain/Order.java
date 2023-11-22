package org.sopt.tablingServer.order.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;
import org.sopt.tablingServer.common.domain.BaseTimeEntity;
import org.sopt.tablingServer.common.exception.model.BusinessException;
import org.sopt.tablingServer.common.exception.model.ErrorType;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "orders")
public class Order extends BaseTimeEntity {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus orderStatus;

    @Column(nullable = false)
    private String shopName;

    private int personCount;

    private int waitingNumber;

    private int beforeCount;

    private int totalPrice;

    private String requestContent;

    private LocalDateTime OrderDate;

    public void changeOrderStatusComplete() {
        this.checkOrderStatusComplete();
        this.orderStatus = OrderStatus.COMPLETED;
    }

    private void checkOrderStatusComplete() {
        if (this.orderStatus.equals(OrderStatus.COMPLETED)) {
            throw new BusinessException(ErrorType.ORDER_ALREADY_COMPLETED);
        }
    }

}
