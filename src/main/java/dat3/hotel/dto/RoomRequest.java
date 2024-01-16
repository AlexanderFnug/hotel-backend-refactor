package dat3.hotel.dto;


import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomRequest {
    private String roomNumber;
    private int numberOfBeds;
    private BigDecimal price;
    private Long hotelId; // Optional, based on your design
}

