package video.meedding.Meedding.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import video.meedding.Meedding.domain.Member;
import video.meedding.Meedding.domain.Message;
import video.meedding.Meedding.exception.NoMemberException;
import video.meedding.Meedding.repository.MemberRepository;
import video.meedding.Meedding.repository.MessageRepository;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MessageService {
    private final MemberRepository memberRepository;
    private final MessageRepository messageRepository;

    @Transactional
    public void join(Long sentId, Long receivedId, String contents) {
        Member sentM = memberRepository.findById(sentId).orElseThrow(()->new NoMemberException());
        Member receivedM = memberRepository.findById(receivedId).orElseThrow(()->new NoMemberException());
        Message message = Message.createMessage(sentM, receivedM, contents);
        messageRepository.save(message);
    }

    public List<Message> findBySentMember(Member member) {
        return messageRepository.findBySentMember(member);
    }

    public List<Message> findByReceivedMember(Member member) {
        return messageRepository.findByReceivedMember(member);
    }

    public void deleteMessage(Message message) {
        messageRepository.delete(message);
    }
}
