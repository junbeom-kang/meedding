package video.meedding.Meedding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import video.meedding.Meedding.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    List<Member> findByName(String name);
    Optional<Member> findByNickname(String name);
    Optional<Member> findByLoginid(String loginid);
}
