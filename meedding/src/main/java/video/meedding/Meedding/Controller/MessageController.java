package video.meedding.Meedding.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import video.meedding.Meedding.domain.Message;
import video.meedding.Meedding.dto.CreateMessageDto;
import video.meedding.Meedding.dto.MessageResponseDto;
import video.meedding.Meedding.dto.ResponseMemberDto;
import video.meedding.Meedding.dto.response.Result;
import video.meedding.Meedding.service.MessageService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @PostMapping("/messages")
    public Result createMessage(@RequestBody @Valid CreateMessageDto createMessageDto) {
        messageService.Message(createMessageDto);
        return new Result<Integer>(HttpStatus.OK.value());
    }


        @GetMapping("/message/sent")
        public Result sentMessages() {
            Long id=SessionUser.getId();
            List<Message> bySentMember = messageService.findBySentMember(id);
            List<MessageResponseDto> result=bySentMember.stream()
                    .map(m->new MessageResponseDto(m.getId(),m.getTitle(),m.getContents())).collect(Collectors.toList());
            return new Result(result);
        }

        @GetMapping("/message/received")
        public Result receivedMessages() {
            Long id=SessionUser.getId();
            List<Message> byReceivedMember = messageService.findByReceivedMember(id);
            List<MessageResponseDto> result=bySentMember.stream()
                    .map(m->new MessageResponseDto(m.getId(),m.getTitle(),m.getContents())).collect(Collectors.toList());
            return new Result(result);
        }


    @GetMapping("/messages/{id}")
    public Result readMessage(@PathVariable Long id) {
        Message message = messageService.findByMessageId(id);
        return new Result(message);
    }
}
