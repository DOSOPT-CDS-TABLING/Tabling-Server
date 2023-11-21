package org.sopt.tablingServer.order.infrastructure;

import org.sopt.tablingServer.common.exception.model.BusinessException;
import org.sopt.tablingServer.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.sopt.tablingServer.common.exception.model.ErrorType;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {
    default Order findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(
                () -> new BusinessException(ErrorType.NOT_FOUND_ORDER_ERROR));
    }
}
