package edu.du.sb1030.repository;

import edu.du.sb1030.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
