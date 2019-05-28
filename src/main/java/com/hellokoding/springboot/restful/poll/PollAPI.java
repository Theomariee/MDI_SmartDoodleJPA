package com.hellokoding.springboot.restful.poll;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/polls")
@Slf4j
@RequiredArgsConstructor
public class PollAPI {
    private final PollService pollService;

    @GetMapping
    public ResponseEntity<List<Poll>> findAll() {
        return ResponseEntity.ok(pollService.findAll());
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody Poll poll) {
        return ResponseEntity.ok(pollService.save(poll));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Poll> findById(@PathVariable Long id) {
        Optional<Poll> poll = pollService.findById(id);
        if (!poll.isPresent()) {
            log.error("Id " + id + " not valid");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(poll.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Poll> update(@PathVariable Long id, @Valid @RequestBody Poll poll) {
        if (!pollService.findById(id).isPresent()) {
            log.error("Id " + id + " not valid");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(pollService.save(poll));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if (!pollService.findById(id).isPresent()) {
            log.error("Id " + id + " not valid");
            ResponseEntity.badRequest().build();
        }

        pollService.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
