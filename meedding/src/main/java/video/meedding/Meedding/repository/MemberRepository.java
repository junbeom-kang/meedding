package video.meedding.Meedding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import video.meedding.Meedding.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    @Query("select DISTINCT m from Member m join fetch m.sentMessage join fetch m.receivedMessage ")
    List<Member> test();

    List<Member> findByName(String name);
    Optional<Member> findByNickname(String name);
    Optional<Member> findByLoginid(String loginid);
}
