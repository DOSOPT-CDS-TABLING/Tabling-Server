package org.sopt.tablingServer.shop.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.sopt.tablingServer.common.domain.BaseTimeEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Menu extends BaseTimeEntity {

    @Id
    @Column(name = "menu_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String menuCategory;

    //    @ColumnDefault("https://github.com/DOSOPT-CDS-TABLING/Tabling-Server/assets/67463603/378964af-b46a-45c2-87ed-31d8a78fa002.png")
    @Column(nullable = false)
    private String menuPhotoUrl;

    @Column(nullable = false)
    private String menuName;

    @Column(nullable = false)
    private Integer price;
}
