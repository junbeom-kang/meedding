package video.meedding.Meedding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import video.meedding.Meedding.domain.Member;
import video.meedding.Meedding.domain.Message;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySentMember(Member member);
    @Query("select m from Message m join fetch m.receivedMember join fetch m.sentMember where m.receivedMember.id=:member_id")
    List<Message> findByReceivedMember(@Param("member_id") Long id);
    //List<Message> findByReceivedMember(Member member);
}
