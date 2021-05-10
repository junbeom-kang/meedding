package video.meedding.Meedding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import video.meedding.Meedding.domain.Friends;
import video.meedding.Meedding.domain.Member;

import java.util.Optional;

public interface FriendsRepository extends JpaRepository<Friends, Long> {
    Optional<Friends> findByAddMember(Member member);
    Optional<Friends> findByTargetMember(Member member);
}
