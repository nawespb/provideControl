package com.nawe.provideControl.repository;

import com.nawe.provideControl.domain.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findByNameContainsIgnoreCase(String name);

}

