package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entites.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
