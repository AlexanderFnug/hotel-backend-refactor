package dat3.hotel.service;

import dat3.hotel.dto.HotelRequest;
import dat3.hotel.dto.HotelResponse;
import dat3.hotel.entity.Hotel;
import dat3.hotel.entity.Room;
import dat3.hotel.repository.HotelRepository;
import dat3.hotel.repository.ReservationRepository;
import dat3.hotel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;

    private ReservationRepository reservationRepository;

    @Autowired
    public HotelService(HotelRepository hotelRepository, RoomRepository roomRepository, ReservationRepository reservationRepository) {
        this.hotelRepository = hotelRepository;
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<HotelResponse> findAllHotels() {
        return hotelRepository.findAll().stream()
                .map(this::convertToHotelListResponse)
                .collect(Collectors.toList());
    }

    public HotelResponse createHotel(HotelRequest hotelRequest) {
        Hotel hotel = new Hotel();
        hotel.setName(hotelRequest.getName());
        hotel.setStreet(hotelRequest.getStreet());
        hotel.setCity(hotelRequest.getCity());
        hotel.setZip(hotelRequest.getZip());
        hotel.setCountry(hotelRequest.getCountry());
        hotel.setCreated(LocalDateTime.now());
        hotel.setUpdated(LocalDateTime.now());

        Hotel savedHotel = hotelRepository.save(hotel);


        return convertToHotelFormResponse(savedHotel);
    }

    public HotelResponse updateHotel(Long id, HotelRequest hotelRequest) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel not found"));
        hotel.setName(hotelRequest.getName());
        hotel.setStreet(hotelRequest.getStreet());
        hotel.setCity(hotelRequest.getCity());
        hotel.setZip(hotelRequest.getZip());
        hotel.setCountry(hotelRequest.getCountry());
        hotel.setUpdated(LocalDateTime.now());

        Hotel updatedHotel = hotelRepository.save(hotel);

        return convertToHotelFormResponse(updatedHotel);
    }

    public void deleteHotel(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel not found"));

        LocalDate today = LocalDate.now(); // Use LocalDate

        // Get all rooms for this hotel
        List<Room> rooms = roomRepository.findByHotelId(hotel.getId());

        // Check if any of these rooms have future reservations
        boolean hasFutureReservations = rooms.stream()
                .anyMatch(room -> !reservationRepository.findByRoomIdAndDateGreaterThan(
                        room.getId(), today).isEmpty());

        if (hasFutureReservations) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot delete hotel with future reservations");
        }

        // If no future reservations, delete all rooms first
        roomRepository.deleteAll(rooms);

        // Finally, delete the hotel
        hotelRepository.delete(hotel);
    }



    public HotelResponse findHotelById(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel not found"));
        return convertToHotelFormResponse(hotel);
    }

    private HotelResponse convertToHotelListResponse(Hotel hotel) {
        HotelResponse hotelResponse = new HotelResponse();
        hotelResponse.setId(hotel.getId());
        hotelResponse.setName(hotel.getName());
        String address = String.format("%s, %s, %s", hotel.getStreet(), hotel.getCity(), hotel.getCountry());
        hotelResponse.setAddress(address); // Concatenated address for the list
        hotelResponse.setNumberOfRooms(hotel.getRooms() != null ? hotel.getRooms().size() : 0);
        return hotelResponse;
    }
    private HotelResponse convertToHotelFormResponse(Hotel hotel) {
        HotelResponse hotelResponse = new HotelResponse();
        hotelResponse.setId(hotel.getId());
        hotelResponse.setName(hotel.getName());
        hotelResponse.setStreet(hotel.getStreet());
        hotelResponse.setCity(hotel.getCity());
        hotelResponse.setZip(hotel.getZip());
        hotelResponse.setCountry(hotel.getCountry());
        // No need to set the concatenated address here
        hotelResponse.setNumberOfRooms(hotel.getRooms() != null ? hotel.getRooms().size() : 0);
        return hotelResponse;
    }

}
