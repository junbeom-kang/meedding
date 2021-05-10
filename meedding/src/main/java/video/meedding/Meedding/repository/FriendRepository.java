package video.meedding.Meedding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import video.meedding.Meedding.domain.Friend;
import video.meedding.Meedding.domain.Member;

import java.util.List;
import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend, Long> {
    List<Friend> findByAddMember(Member member);
    List<Friend> findByTargetMember(Member member);
    Optional<Friend> findByAddMemberAndTargetMember(Member addMember, Member targetMember);
}
