package com.sirma.santa.s_workshop.model;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Delivery {
    public Long id;
    @NotBlank @Size(min = 5, max = 120)
    public String address;
    @NotBlank
    public String recipientName;
    @NotEmpty
    public List<Long> giftIds;
    public DeliveryStatus deliveryStatus = DeliveryStatus.PLANNED;
    public LocalDateTime estimatedArrival;
}