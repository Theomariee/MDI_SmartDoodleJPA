package com.hellokoding.springboot.restful.slot;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hellokoding.springboot.restful.slot.Slot;

public interface SlotRepository extends JpaRepository<Slot, Long> {
}
