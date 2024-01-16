package dat3.hotel.api;

import dat3.hotel.dto.HotelRequest;
import dat3.hotel.dto.HotelResponse;
import dat3.hotel.dto.RoomRequest;
import dat3.hotel.dto.RoomResponse;
import dat3.hotel.service.HotelService;
import dat3.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RoomService roomService;

    @PostMapping
    public ResponseEntity<HotelResponse> createHotel(@RequestBody HotelRequest hotelRequest) {
        HotelResponse newHotel = hotelService.createHotel(hotelRequest);
        return new ResponseEntity<>(newHotel, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<HotelResponse>> getAllHotels() {
        List<HotelResponse> hotels = hotelService.findAllHotels();
        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }

    // Endpoint to find a hotel by id
    @GetMapping("/{id}")
    public ResponseEntity<HotelResponse> getHotelById(@PathVariable Long id) {
        HotelResponse hotel = hotelService.findHotelById(id);
        return new ResponseEntity<>(hotel, HttpStatus.OK);
    }

    // Endpoint to update a hotel
    @PutMapping("/{id}")
    public ResponseEntity<HotelResponse> updateHotel(@PathVariable Long id, @RequestBody HotelRequest hotelRequest) {
        HotelResponse updatedHotel = hotelService.updateHotel(id, hotelRequest);
        return new ResponseEntity<>(updatedHotel, HttpStatus.OK);
    }

    // Endpoint to delete a hotel
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

