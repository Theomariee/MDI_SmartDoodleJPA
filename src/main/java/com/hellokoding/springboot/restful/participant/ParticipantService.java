package com.hellokoding.springboot.restful.participant;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

@RequiredArgsConstructor
public class ParticipantService {
    private final ParticipantRepository participantRepository;

    public List<Participant> findAll() {
        return participantRepository.findAll();
    }

    public Optional<Participant> findById(Long id) {
        return participantRepository.findById(id);
    }

    public Participant save(Participant participant) {
        return participantRepository.save(participant);
    }

    public void deleteById(Long id) {
    	participantRepository.deleteById(id);
    }
}
