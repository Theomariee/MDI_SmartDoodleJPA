package com.hellokoding.springboot.restful.poll;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hellokoding.springboot.restful.product.Product;

public interface PollRepository extends JpaRepository<Poll, Long> {
}
