package dat3.hotel.repository;

import dat3.hotel.entity.Reservation;
import dat3.hotel.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByRoomIdAndDateGreaterThan(Long roomId, LocalDate date);
    List<Reservation> findByRoomAndDate(Room room, LocalDate date);
}

