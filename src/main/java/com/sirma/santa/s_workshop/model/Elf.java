package com.sirma.santa.s_workshop.model;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Elf {
    public Long id;
    public String name;
    public SkillLever skillLevel;
    public List<Long> assignedGiftIds = new ArrayList<>();
}