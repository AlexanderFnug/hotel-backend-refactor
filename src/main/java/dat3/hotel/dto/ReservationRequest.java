package dat3.hotel.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationRequest {
    private Long roomId;
    private String guestUsername;
    private LocalDate date;
}

