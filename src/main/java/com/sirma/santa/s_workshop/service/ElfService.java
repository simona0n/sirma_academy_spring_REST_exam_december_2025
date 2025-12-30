package com.sirma.santa.s_workshop.service;
import com.sirma.santa.s_workshop.model.Elf;
import com.sirma.santa.s_workshop.model.Gift;
import com.sirma.santa.s_workshop.model.Status;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ElfService {
    public List<Elf> elves = new ArrayList<>();
    public long idCounter = 1;
    public GiftService giftService;

    public ElfService(GiftService _giftService) {
        this.giftService = _giftService;
    }

    public Elf createElf(Elf _elf) {
        _elf.setId(idCounter);
        idCounter++;
        elves.add(_elf);
        return _elf;
    }
    public Elf findElfById(Long _id) {
        for (Elf e : elves) {
            if (e.getId().equals(_id)) {
                return e;
            }
        }
        return null;
    }

    public Elf assignGiftToElf(Long elfId, Long giftId) throws Exception {
        Elf elf = findElfById(elfId);
        Gift gift = giftService.getById(giftId).orElse(null);
        if (elf == null || gift == null) {
            throw new Exception("Missing elf id or gift id!");
        }

        if (gift.getStatus() == Status.DELIVERED) {
            throw new RuntimeException("Invalid status");
        }

        elf.getAssignedGiftIds().add(giftId);
        return elf;
    }
}