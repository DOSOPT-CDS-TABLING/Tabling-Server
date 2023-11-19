package org.sopt.tablingServer.shop.domain;

import jakarta.persistence.*;
import lombok.*;
import org.sopt.tablingServer.common.domain.BaseTimeEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Review extends BaseTimeEntity {

    @Id
    @Column(name = "review_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float star;

    @Column(nullable = false)
    private String reviewerName;

    private int dayBefore;

    private String content;
}
