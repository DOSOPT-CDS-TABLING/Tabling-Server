package org.sopt.tablingServer.shop.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesInfo {

    @Column(nullable = false)
    private String salesTime;

    @Column(nullable = false)
    private String waitingTime;

    private String restTime;

    private String restDay;

    @Column(nullable = false)
    private String phoneNumber;
}
