package video.meedding.Meedding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import video.meedding.Meedding.domain.Member;
import video.meedding.Meedding.domain.Message;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySentMember(Member member);
    List<Message> findByReceivedMember(Member member);
}
