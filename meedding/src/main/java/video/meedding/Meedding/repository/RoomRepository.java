package video.meedding.Meedding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import video.meedding.Meedding.domain.Room;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findByRoomNumber(String number);
}
