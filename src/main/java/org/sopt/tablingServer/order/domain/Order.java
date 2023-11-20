package org.sopt.tablingServer.order.domain;

import jakarta.persistence.*;
import java.util.Date;
import lombok.*;
import org.sopt.tablingServer.common.domain.BaseTimeEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
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

    private Date OrderDate;

}
