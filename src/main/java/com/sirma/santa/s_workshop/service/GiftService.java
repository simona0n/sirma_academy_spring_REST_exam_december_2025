package com.sirma.santa.s_workshop.service;
import com.sirma.santa.s_workshop.model.Gift;
import com.sirma.santa.s_workshop.model.Status;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GiftService {
    private final List<Gift> gifts = new ArrayList<>();
    private long nextId = 1;

    public Gift createGift(Gift _gift) {
       _gift.setId(nextId++);
        gifts.add(_gift);
        return _gift;
    }

    public List<Gift> getAllGifts() {
        return gifts;
    }

    public Optional<Gift> getById(Long _id) {
        return gifts.stream()
                .filter(g -> g.getId().equals(_id))
                .findFirst();
    }

    public Optional<Gift> wrapGift(Long _id) {
        return getById(_id).map(gift -> {
            gift.setWrapped(true);
            return gift;
        });
    }

    public boolean deleteGift(Long _id) {
        return gifts.removeIf(g -> g.getId().equals(_id));
    }
}