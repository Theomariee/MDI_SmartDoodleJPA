package com.hellokoding.springboot.restful.slot;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hellokoding.springboot.restful.product.Product;

public interface SlotRepository extends JpaRepository<Product, Long> {
}
