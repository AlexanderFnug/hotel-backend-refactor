package dat3.hotel.repository;

import dat3.hotel.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GuestRepository extends JpaRepository<Guest, Long> {
    Guest findByUserName(String userName);
}

