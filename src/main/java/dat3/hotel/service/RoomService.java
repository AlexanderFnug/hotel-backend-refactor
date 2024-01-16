package dat3.hotel.service;

import dat3.hotel.dto.RoomRequest;
import dat3.hotel.dto.RoomResponse;
import dat3.hotel.entity.Hotel;
import dat3.hotel.entity.Room;
import dat3.hotel.repository.HotelRepository;
import dat3.hotel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    public RoomResponse addRoomToHotel(RoomRequest roomRequest) {
        Hotel hotel = hotelRepository.findById(roomRequest.getHotelId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel not found"));
        Room room = new Room();
        room.setRoomNumber(roomRequest.getRoomNumber());
        room.setNumberOfBeds(roomRequest.getNumberOfBeds());
        room.setPrice(roomRequest.getPrice());
        room.setHotel(hotel);

        Room savedRoom = roomRepository.save(room);
        return convertToRoomResponse(savedRoom);
    }

    // Add other room-related methods here (e.g., updateRoom, deleteRoom, findRoomById)

    private RoomResponse convertToRoomResponse(Room room) {
        RoomResponse roomResponse = new RoomResponse();
        roomResponse.setId(room.getId());
        roomResponse.setRoomNumber(room.getRoomNumber());
        roomResponse.setNumberOfBeds(room.getNumberOfBeds());
        roomResponse.setPrice(room.getPrice());
        // Include hotel name if needed
        return roomResponse;
    }
}

