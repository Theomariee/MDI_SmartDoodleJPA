package com.hellokoding.springboot.restful.slot;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/slots")
@Slf4j
@RequiredArgsConstructor
public class SlotAPI {
    private final SlotService slotService;

    @GetMapping
    public ResponseEntity<List<Slot>> findAll() {
        return ResponseEntity.ok(slotService.findAll());
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody Slot slot) {
        return ResponseEntity.ok(slotService.save(slot));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Slot> findById(@PathVariable Long id) {
        Optional<Slot> slot = slotService.findById(id);
        if (!slot.isPresent()) {
            log.error("Id " + id + " not valid");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(slot.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Slot> update(@PathVariable Long id, @Valid @RequestBody Slot slot) {
        if (!slotService.findById(id).isPresent()) {
            log.error("Id " + id + " not valid");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(slotService.save(slot));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if (!slotService.findById(id).isPresent()) {
            log.error("Id " + id + " not valid");
            ResponseEntity.badRequest().build();
        }

        slotService.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
