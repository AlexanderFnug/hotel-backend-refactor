package dat3.hotel.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelRequest {
    private String name;
    private String street;
    private String city;
    private String zip;
    private String country;
}
