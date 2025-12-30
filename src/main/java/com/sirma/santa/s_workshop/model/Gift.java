package com.sirma.santa.s_workshop.model;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Gift {
    private Long id;
    @NotBlank @Size(min = 2, max = 50)
    private String name;
    @NotNull
    private Category category;
    @Min(0) @Max(99)
    private Integer targetAge;
    private boolean isWrapped = false;
    private Status status = Status.PENDING;
    private LocalDateTime createdAt = LocalDateTime.now();
}


