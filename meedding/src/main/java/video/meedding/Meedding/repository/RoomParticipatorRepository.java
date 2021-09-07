package video.meedding.Meedding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import video.meedding.Meedding.domain.RoomParticipate;

import java.util.Optional;

public interface RoomParticipatorRepository extends JpaRepository<RoomParticipate, Long> {
    @Query("select p from RoomParticipate p where p.member.id=:member_id and p.room.id=:room_id")
    Optional<RoomParticipate> findRoomParticipateByMemberAndRoom(@Param("member_id")Long member_id,@Param("room_id")Long room_id);
}
