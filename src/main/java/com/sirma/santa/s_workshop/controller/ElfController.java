package com.sirma.santa.s_workshop.controller;
import com.sirma.santa.s_workshop.model.Elf;
import com.sirma.santa.s_workshop.service.ElfService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/elves")
public class ElfController {

    public ElfService service;
    public ElfController(ElfService _service) {
        this.service = _service;
    }

    @PostMapping
    public Elf create(@RequestBody Elf _elf) {
        return service.createElf(_elf);
    }

    @GetMapping
    public List<Elf> getAll() {
        return service.elves;
    }

    @PostMapping("/{elfId}/assign/{_giftId}")
    public ResponseEntity assign(@PathVariable Long _elfId, @PathVariable Long _giftId) {
        try {
            Elf updated = service.assignGiftToElf(_elfId, _giftId);
            return ResponseEntity.status(200).body(updated);
        } catch (Exception _e) {
            if (_e.getMessage().equals("Missing elf id or gift id !")) {
                return ResponseEntity.status(404).body("Not Found");
            }
            return ResponseEntity.status(409).body("Conflict - status is bad");
        }
    }
}