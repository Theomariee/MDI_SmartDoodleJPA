package com.hellokoding.springboot.restful.participant;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hellokoding.springboot.restful.product.Product;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}
