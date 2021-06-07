package video.meedding.Meedding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import video.meedding.Meedding.domain.Friend;
import video.meedding.Meedding.domain.Member;

import java.util.List;
import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend, Long> {
    @Query("select f from Friend f join fetch f.addMember join fetch f.targetMember where f.addMember.id=:addMember_id")
    List<Friend> findByAddMember(@Param("addMember_id") Long id);
    @Query("select f from Friend f join fetch f.addMember join fetch f.targetMember where f.targetMember.id=:targetMember_id")
    List<Friend> findByTargetMember(@Param("targetMember_id") Long id);
    Optional<Friend> findByAddMemberAndTargetMember(Member addMember, Member targetMember);
}
