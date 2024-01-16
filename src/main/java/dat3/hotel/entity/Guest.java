package dat3.hotel.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDateTime created;
    private LocalDateTime updated;

    @OneToMany(mappedBy = "guest")
    private Set<Reservation> reservations;
}

