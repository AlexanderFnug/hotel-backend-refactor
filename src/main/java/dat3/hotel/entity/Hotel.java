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
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String street;
    private String city;
    private String zip;
    private String country;
    private LocalDateTime created;
    private LocalDateTime updated;

    @OneToMany(mappedBy = "hotel")
    private Set<Room> rooms;
}

