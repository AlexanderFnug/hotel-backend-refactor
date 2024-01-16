package dat3.hotel.dto;


import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomResponse {
    private Long id;
    private String roomNumber;
    private int numberOfBeds;
    private BigDecimal price;
    private String hotelName; // Optional, include if needed


}

