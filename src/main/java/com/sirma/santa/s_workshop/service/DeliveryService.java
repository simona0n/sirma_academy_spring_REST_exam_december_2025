package com.sirma.santa.s_workshop.service;
import com.sirma.santa.s_workshop.model.*;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryService {
    public List<Delivery> deliveries = new ArrayList<>();
    public long counter = 1;
    public GiftService giftService;

    public DeliveryService(GiftService _giftService) {
        this.giftService = _giftService;
    }

    public Delivery createDelivery(Delivery _delivery) throws Exception {

        for (Long giftId : _delivery.getGiftIds()) {
            Gift gift = giftService.getById(giftId).orElse(null);
            if (gift == null) {
                throw new Exception("Gift with that id not found: " + giftId);
            }

            if (gift.getStatus() == Status.PENDING || gift.getStatus() == Status.DELIVERED) {
                throw new IllegalStateException("Gift " + giftId + " is not ready!");
            }
        }

        for (Long giftId :_delivery.getGiftIds()) {
            Gift gift = giftService.getById(giftId).get();
            gift.setStatus(Status.LOADED);
        }


        _delivery.setId(counter++);
        deliveries.add(_delivery);
        return _delivery;
    }

    public Delivery updateStatus(Long id, DeliveryStatus _newStatus) throws Exception {
        for (Delivery d : deliveries) {
            if (d.getId().equals(id)) {

                if (_newStatus == DeliveryStatus.DELIVERED) {
                    for (Long giftId : d.getGiftIds()) {
                        giftService.getById(giftId).ifPresent(g -> g.setStatus(Status.DELIVERED));
                    }
                }
                d.setDeliveryStatus(_newStatus);
                return d;
            }
        }
        throw new Exception("Delivery not found");
    }
}