package com.hellokoding.springboot.restful.slot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

@RequiredArgsConstructor
public class SlotService {
    private final SlotRepository slotRepository;

    public List<Slot> findAll() {
        return slotRepository.findAll();
    }

    public Optional<Slot> findById(Long id) {
        return slotRepository.findById(id);
    }

    public Slot save(Slot participant) {
        return slotRepository.save(participant);
    }

    public void deleteById(Long id) {
    	slotRepository.deleteById(id);
    }
}
