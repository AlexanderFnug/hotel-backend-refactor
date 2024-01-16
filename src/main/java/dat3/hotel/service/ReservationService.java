package dat3.hotel.service;

import dat3.hotel.dto.ReservationRequest;

import dat3.hotel.entity.Reservation;
import dat3.hotel.entity.Room;
import dat3.hotel.repository.GuestRepository;
import dat3.hotel.repository.ReservationRepository;
import dat3.hotel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private GuestRepository guestRepository;

    @Transactional
    public void makeReservation(ReservationRequest request) {
        // Fetch the room based on roomId from the request
        Room room = roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        // Check if there's an existing reservation for the room on the requested date
        boolean isAvailable = reservationRepository.findByRoomAndDate(room, request.getDate()).isEmpty();

        if (!isAvailable) {
            throw new RuntimeException("Room is not available on the requested date");
        }

        // Create and save the reservation
        Reservation reservation = new Reservation();
        reservation.setRoom(room);
        reservation.setDate(request.getDate());
        reservation.setGuest(guestRepository.findByUserName(request.getGuestUsername())); // Assuming you have guestName in your request
        // Set other reservation details from the request

        reservationRepository.save(reservation);
    }
}
