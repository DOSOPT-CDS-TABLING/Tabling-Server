package org.sopt.tablingServer.shop.infrastructure;

import org.sopt.tablingServer.shop.domain.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopJpaRepository extends JpaRepository<Shop, Long> {
    List<Shop> findAllByOrderByAverageWaitingDesc();
}