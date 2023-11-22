package org.sopt.tablingServer.shop.domain;

import jakarta.persistence.*;
import lombok.*;
import org.sopt.tablingServer.common.domain.BaseTimeEntity;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Shop extends BaseTimeEntity {

    @Id
    @Column(name = "shop_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 매장 관련 기본 정보
     */
    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ShopCategory shopCategory;

    @Column(nullable = false)
    private String shortAddress;

    private String longAddress;

    @Column(nullable = false)
    private String profilePhotoUrl;

    @Column(name = "url", nullable = false)
    @ElementCollection
    private List<String> detailPhotoList;

    /**
     * 매장 관련 세부 정보
     */
    private int averageWaiting;

    private int currentWaiting;

    public void changeCurrentWaiting(int currentWaiting) {
        this.currentWaiting = currentWaiting;
    }

    @Embedded
    private SalesInfo salesInfo;

    @Column(name = "tag")
    @ElementCollection
    private List<String> hashTagList;

    private String introduceContent;

    /**
     * 메뉴 관련 정보
     */
    @OneToMany
    @JoinColumn(name = "shop_id")
    private List<Menu> menuList;

    /**
     * 리뷰 관련 정보
     */
    @OneToMany
    @JoinColumn(name = "shop_id")
    private List<Review> reviewList;

    private float averageStar;

    @Column(name = "star")
    @ElementCollection
    private List<Float> detailStarList;

    private int reviewCount;
}
