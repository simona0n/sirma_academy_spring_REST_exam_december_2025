package com.sirma.santa.s_workshop.controller;
import com.sirma.santa.s_workshop.model.Category;
import com.sirma.santa.s_workshop.model.Gift;
import com.sirma.santa.s_workshop.model.Status;
import com.sirma.santa.s_workshop.service.GiftService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/gifts")
public class GiftController {

    private final GiftService giftService;
    public GiftController(GiftService _giftService) {
        this.giftService = _giftService;
    }

    @PostMapping
    public ResponseEntity<Gift> create(@Valid @RequestBody Gift _gift) {
        Gift created = giftService.createGift(_gift);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Gift> getAll(
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) Category category) {

        List<Gift> allGifts = giftService.getAllGifts();
        return allGifts.stream()
                .filter(g -> status == null || g.getStatus().equals(status))
                .filter(g -> category == null || g.getCategory().equals(category))
                .toList();
    }

    @GetMapping("/{_id}")
    public ResponseEntity<Gift> getOne(@PathVariable Long _id) {
        return giftService.getById(_id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{_id}")
    public ResponseEntity<Gift> update(@PathVariable Long _id, @Valid @RequestBody Gift _giftUpdate) {
        return giftService.getById(_id).map(existingGift -> {
            existingGift.setName(_giftUpdate.getName());
            existingGift.setCategory(_giftUpdate.getCategory());
            existingGift.setTargetAge(_giftUpdate.getTargetAge());
            existingGift.setWrapped(_giftUpdate.isWrapped());
            existingGift.setStatus(_giftUpdate.getStatus());
            return ResponseEntity.ok(existingGift);
        }).orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{_id}/wrap")
    public ResponseEntity<Gift> wrap(@PathVariable Long _id) {
        return giftService.wrapGift(_id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{_id}")
    public ResponseEntity<Void> delete(@PathVariable Long _id) {
        if (giftService.deleteGift(_id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}