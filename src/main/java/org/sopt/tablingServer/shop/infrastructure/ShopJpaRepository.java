package org.sopt.tablingServer.shop.infrastructure;

import org.sopt.tablingServer.common.exception.model.BusinessException;
import org.sopt.tablingServer.common.exception.model.ErrorType;
import org.sopt.tablingServer.shop.domain.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopJpaRepository extends JpaRepository<Shop, Long> {
    List<Shop> findAllByOrderByAverageWaitingDesc();

    default Shop findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(
                () -> new BusinessException(ErrorType.NOT_FOUND_SHOP_ERROR));
    }
}