package video.meedding.Meedding.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import video.meedding.Meedding.domain.Message;
import video.meedding.Meedding.dto.CreateMemberDto;
import video.meedding.Meedding.dto.CreateMessageDto;
import video.meedding.Meedding.exception.NoMessageException;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class MessageServiceTest {
    @Autowired MemberService memberService;
    @Autowired MessageService messageService;



    @Test
    @Rollback(value = false)
    public void 메시지_저장_테스트() throws Exception {
        //given,when
        Long []messageId=createM();

        //then
        Message byMessage = messageService.findByMessageId(messageId[0]);
        assertThat(byMessage.getId()).isEqualTo(messageId[0]);
        System.out.println(byMessage);
    }
    @Test
    public void 보낸메시지목록_받은메시지목록_테스트() throws Exception {
        //given
        Long[] id = createM();

        //when
        List<Message> bySentMemberList = messageService.findBySentMember(memberService.findMemberById(id[2]));
        List<Message> byReceivedMemberList = messageService.findByReceivedMember(memberService.findMemberById(id[3]));
        //then
        assertThat(bySentMemberList.size()).isEqualTo(2);
        assertThat(byReceivedMemberList.size()).isEqualTo(1);
        assertThat(byReceivedMemberList.get(0)).isEqualTo(bySentMemberList.get(0));
        for (Message message : bySentMemberList) {
            System.out.println(message);
        }
        System.out.println("===============");
        for (Message message : byReceivedMemberList) {
            System.out.println(message);
        }

    }
    @Test
    public void 메시지_삭제_테스트() throws Exception {
        //given
        Long[] id = createM();

        //when
        messageService.deleteMessage(messageService.findByMessageId(id[0]));
        List<Message> bySentMember = messageService.findBySentMember(memberService.findMemberById(id[2]));
        List<Message> byReceivedMember = messageService.findByReceivedMember(memberService.findMemberById(id[3]));
        Exception e=assertThrows(NoMessageException.class,()->messageService.deleteMessage(messageService.findByMessageId(id[0])));
        //then
        assertThat(e.getMessage()).isEqualTo("없는 메시지입니다");
        assertThat(bySentMember.size()).isEqualTo(1);
        assertThat(byReceivedMember.size()).isEqualTo(0);
    }


    public  Long[] createM() {
        Long sentId = memberService.join(new CreateMemberDto("snet@n.c", "sample", "sample", "sent", "sentNick"));
        Long receiveId = memberService.join(new CreateMemberDto("receive@n.c", "sample", "sample", "receive", "receiveNick"));
        Long thirdId = memberService.join(new CreateMemberDto("abcd@n.c", "sample", "sample", "sammple", "thirdMember"));
        CreateMessageDto createMessageDto = new CreateMessageDto(sentId, receiveId, "메시지 테스트", "메시지 테스트 내용입니다");
        CreateMessageDto createMessageDto1=new CreateMessageDto(sentId,thirdId,"1-3메시지 테스트","1-3메시지 테스트 내용");

        Long messageId = messageService.Message(createMessageDto);
        Long messageId1 = messageService.Message(createMessageDto1);
        return new Long[]{messageId,messageId1,sentId,receiveId,thirdId};
    }


}