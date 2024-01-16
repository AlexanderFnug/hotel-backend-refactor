package dat3.hotel.api;

import dat3.hotel.dto.ReservationRequest;

import dat3.hotel.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/hotels/{hotelId}/reserve")
    public ResponseEntity<?> makeReservation(@PathVariable Long hotelId, @RequestBody ReservationRequest request) {
        // Set the hotelId in the request object
        request.setRoomId(hotelId); // Assuming that the roomId in ReservationRequest refers to hotelId
        reservationService.makeReservation(request);
        return ResponseEntity.ok().build();
    }
}

