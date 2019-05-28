package com.hellokoding.springboot.restful.poll;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

@RequiredArgsConstructor
public class PollService {
    private final PollRepository pollRepository;

    public List<Poll> findAll() {
        return pollRepository.findAll();
    }

    public Optional<Poll> findById(Long id) {
        return pollRepository.findById(id);
    }

    public Poll save(Poll poll) {
        return pollRepository.save(poll);
    }

    public void deleteById(Long id) {
    	pollRepository.deleteById(id);
    }
}
