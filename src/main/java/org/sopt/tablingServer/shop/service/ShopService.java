package org.sopt.tablingServer.shop.service;

import lombok.RequiredArgsConstructor;
import org.sopt.tablingServer.shop.domain.Shop;
import org.sopt.tablingServer.shop.infrastructure.ShopJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ShopService {

    private final ShopJpaRepository shopJpaRepository;


    public List<Shop> findShopListOrderByAverageWaiting() {

        return shopJpaRepository.findAllByOrderByAverageWaitingDesc();
    }

    public Shop findShopDetailInfo(Long shopId) {

        return shopJpaRepository.findByIdOrThrow(shopId);
    }
}
