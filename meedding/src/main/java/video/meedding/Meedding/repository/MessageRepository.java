package video.meedding.Meedding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import video.meedding.Meedding.domain.Member;
import video.meedding.Meedding.domain.Message;

import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {
    Optional<Message> findBySentMember(Member member);
    Optional<Message> findByReceivedMember(Member member);
}
