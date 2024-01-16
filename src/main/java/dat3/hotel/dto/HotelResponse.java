package dat3.hotel.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelResponse {
    private Long id;
    private String name;
    private String street;
    private String city;
    private String zip;
    private String country;
    private String address; // Concatenated address
    private int numberOfRooms;
}
