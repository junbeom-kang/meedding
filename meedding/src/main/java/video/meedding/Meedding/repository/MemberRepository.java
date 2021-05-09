package video.meedding.Meedding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import video.meedding.Meedding.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByName(String name);
    Optional<Member> findByNickname(String name);
    Optional<Member> findByLoginid(String login_id);
}
