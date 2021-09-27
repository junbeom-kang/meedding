package video.meedding.Meedding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import video.meedding.Meedding.domain.Room;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findByRoomTitle(String roomTitle);
    Optional<Room> findBySession(String session);
    @Query("select r from Room r order by r.peopleNum DESC")
    List<Room> findAllByPeopleNum();
    @Query("select r.session from Room r")
    List<String> findAllSession();

}

