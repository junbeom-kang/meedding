package video.meedding.Meedding.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import video.meedding.Meedding.domain.Member;
import video.meedding.Meedding.domain.Message;
import video.meedding.Meedding.dto.CreateMessageDto;
import video.meedding.Meedding.exception.NoMemberException;
import video.meedding.Meedding.exception.NoMessageException;
import video.meedding.Meedding.repository.MemberRepository;
import video.meedding.Meedding.repository.MessageRepository;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MessageService {
    private final MemberRepository memberRepository;
    private final MessageRepository messageRepository;

    @Transactional
    public Long Message(Long id,CreateMessageDto createMessageDto) {
        Member sentM = memberRepository.findById(id).orElseThrow(()->new NoMemberException());
        Member receivedM = memberRepository.findById(createMessageDto.getReceivedMemberId()).orElseThrow(()->new NoMemberException());
        Message message = Message.createMessage(sentM, receivedM, createMessageDto.getTitle(), createMessageDto.getContents());
        message.setSentMember(sentM);
        message.setReceivedMember(receivedM);
        Message save = messageRepository.save(message);
        return save.getId();
    }
    public Message findByMessageId(Long id) {
        return messageRepository.findById(id).orElseThrow(()->new NoMessageException("없는 메시지입니다"));
    }
    public List<Message> findBySentMember(Member member) {
        return messageRepository.findBySentMember(member.getId());
    }

    public List<Message> findByReceivedMember(Member member) {
        return messageRepository.findByReceivedMember(member.getId());
    }
    @Transactional
    public void deleteMessage(Message message) {
        messageRepository.delete(message);
    }
}
