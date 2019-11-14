package com.nawe.provideControl.repository;

import com.nawe.provideControl.domain.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
