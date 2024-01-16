/*
package dat3.hotel.config;

import dat3.hotel.entity.Hotel;
import dat3.hotel.entity.Room;
import dat3.hotel.repository.HotelRepository;
import dat3.hotel.repository.RoomRepository;
import dat3.security.entity.Role;
import dat3.security.entity.UserWithRoles;
import dat3.security.repository.UserWithRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.stream.IntStream;

@Configuration
public class DeveloperData implements ApplicationRunner {

    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private UserWithRolesRepository userWithRolesRepository;

    private final Random random = new Random();

    @Override
    public void run(ApplicationArguments args) throws Exception {
        setupUserWithRoleUsers();
        setupHotelData();
    }

    private void setupHotelData() {
        IntStream.rangeClosed(1, 250).forEach(i -> createHotelWithRooms("Hotel " + i));
    }

    private void createHotelWithRooms(String hotelName) {
        Hotel hotel = new Hotel();
        hotel.setName(hotelName);
        hotel.setStreet("Street " + random.nextInt(100));
        hotel.setCity("City " + random.nextInt(50));
        hotel.setZip("Zip " + random.nextInt(9000) + 1000);
        hotel.setCountry("Country " + random.nextInt(10));
        hotel.setCreated(LocalDateTime.now());
        hotel.setUpdated(LocalDateTime.now());

        Hotel savedHotel = hotelRepository.save(hotel);

        int numberOfRooms = 10 + random.nextInt(31);
        IntStream.rangeClosed(1, numberOfRooms).forEach(i -> {
            Room room = new Room();
            room.setRoomNumber("Room " + i);
            room.setNumberOfBeds(1 + random.nextInt(4));
            room.setPrice(new BigDecimal(50 + random.nextInt(150)));
            room.setHotel(savedHotel);
            roomRepository.save(room);
        });
    }

****************************************************************************************
   NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL
   iT'S ONE OF THE TOP SECURITY FLAWS YOU CAN DO
   ****************************************************************************************



  private void setupUserWithRoleUsers() {

    final String passwordUsedByAll = "testyall";
    System.out.println("******************************************************************************");
    System.out.println("******* NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL ************");
    System.out.println("******* REMOVE THIS BEFORE DEPLOYMENT, AND SETUP DEFAULT USERS DIRECTLY  *****");
    System.out.println("**** ** ON YOUR REMOTE DATABASE                 ******************************");
    System.out.println("******************************************************************************");

    UserWithRoles user1 = new UserWithRoles("user1", passwordUsedByAll, "user1@a.dk");
    UserWithRoles user2 = new UserWithRoles("user2", passwordUsedByAll, "user2@a.dk");
    UserWithRoles user3 = new UserWithRoles("user3", passwordUsedByAll, "user3@a.dk");
    UserWithRoles user4 = new UserWithRoles("user4", passwordUsedByAll, "user4@a.dk");
    user1.addRole(Role.USER);
    user1.addRole(Role.ADMIN);
    user2.addRole(Role.USER);
    user3.addRole(Role.ADMIN);
    //No Role assigned to user4
    userWithRolesRepository.save(user1);
    userWithRolesRepository.save(user2);
    userWithRolesRepository.save(user3);
    userWithRolesRepository.save(user4);
  }


}
*/
