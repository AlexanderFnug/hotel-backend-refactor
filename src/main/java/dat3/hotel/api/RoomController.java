package dat3.hotel.api;

import dat3.hotel.dto.RoomRequest;
import dat3.hotel.dto.RoomResponse;
import dat3.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    // Endpoint to create a room and associate it with a hotel
    @PostMapping
    public ResponseEntity<RoomResponse> addRoomToHotel(@RequestBody RoomRequest roomRequest) {
        RoomResponse newRoom = roomService.addRoomToHotel(roomRequest);
        return new ResponseEntity<>(newRoom, HttpStatus.CREATED);
    }

    // Other room-related endpoints (e.g., updateRoom, deleteRoom, getRoomById)

}

