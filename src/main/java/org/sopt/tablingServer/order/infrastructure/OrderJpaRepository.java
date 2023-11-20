package org.sopt.tablingServer.order.infrastructure;

import org.sopt.tablingServer.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {

}
