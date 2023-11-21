package org.sopt.tablingServer.shop;

import lombok.RequiredArgsConstructor;
import org.sopt.tablingServer.shop.dto.response.ShopDetailResponse;
import org.sopt.tablingServer.shop.dto.response.ShopResponse;
import org.sopt.tablingServer.shop.infrastructure.ShopJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ShopService {

    private final ShopJpaRepository shopJpaRepository;


    public List<ShopResponse> getShopListByAverageWaiting() {

        return shopJpaRepository.findAllByOrderByAverageWaitingDesc().stream()
                .map(ShopResponse::of)
                .collect(Collectors.toList());
    }

    public ShopDetailResponse getShopDetailInfo(Long shopId) {

        return ShopDetailResponse.of(shopJpaRepository.findByIdOrThrow(shopId));
    }
}
