package com.hellokoding.springboot.restful.participant;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/participants")
@Slf4j
@RequiredArgsConstructor
public class ParticipantAPI {
    private final ParticipantService participantService;

    @GetMapping
    public ResponseEntity<List<Participant>> findAll() {
        return ResponseEntity.ok(participantService.findAll());
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody Participant participant) {
        return ResponseEntity.ok(participantService.save(participant));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Participant> findById(@PathVariable Long id) {
        Optional<Participant> participant = participantService.findById(id);
        if (!participant.isPresent()) {
            log.error("Id " + id + " not valid");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(participant.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Participant> update(@PathVariable Long id, @Valid @RequestBody Participant participant) {
        if (!participantService.findById(id).isPresent()) {
            log.error("Id " + id + " not valid");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(participantService.save(participant));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if (!participantService.findById(id).isPresent()) {
            log.error("Id " + id + " not valid");
            ResponseEntity.badRequest().build();
        }

        participantService.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
