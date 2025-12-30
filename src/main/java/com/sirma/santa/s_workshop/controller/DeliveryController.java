package com.sirma.santa.s_workshop.controller;
import com.sirma.santa.s_workshop.model.*;
import com.sirma.santa.s_workshop.service.DeliveryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {
    public DeliveryService service;

    public DeliveryController(DeliveryService _service) {
        this.service = _service;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Delivery _delivery) {
        try {
            Delivery created = service.createDelivery(_delivery);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (IllegalStateException _e) {
            return ResponseEntity.status(409).body(_e.getMessage());
        } catch (Exception _e) {
            return ResponseEntity.status(404).body(_e.getMessage());
        }
    }

    @GetMapping
    public List<Delivery> getAll() {
        return service.deliveries;
    }

    @PatchMapping("/{_id}/status")
    public ResponseEntity<?> changeStatus(@PathVariable Long _id, @RequestBody Delivery _statusUpdate) {
        try {
            Delivery updated = service.updateStatus(_id, _statusUpdate.getDeliveryStatus());
            return ResponseEntity.ok(updated);
        } catch (Exception _e) {
            return ResponseEntity.status(404).body(_e.getMessage());
        }
    }
}