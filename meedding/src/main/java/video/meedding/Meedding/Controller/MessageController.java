package video.meedding.Meedding.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import video.meedding.Meedding.config.auth.PrincipalDetails;
import video.meedding.Meedding.domain.Message;
import video.meedding.Meedding.dto.CreateMessageDto;
import video.meedding.Meedding.dto.MessageResponseDto;
import video.meedding.Meedding.dto.response.Result;
import video.meedding.Meedding.service.MessageService;
import video.meedding.Meedding.service.ResponseService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;
    private final ResponseService responseService;

    @PostMapping("/message")
    public Result createMessage(@RequestBody @Valid CreateMessageDto createMessageDto,@AuthenticationPrincipal PrincipalDetails principal) {
        Long message = messageService.Message(principal.getMember().getId(),createMessageDto);
        return responseService.getSingleResult(message);
    }

    @GetMapping("/message/sent")
    public Result sentMessages(@AuthenticationPrincipal PrincipalDetails principal) {
        List<Message> bySentMember = messageService.findBySentMember(principal.getMember());
        List<MessageResponseDto> result=bySentMember.stream()
                .map(MessageResponseDto::covertMessageDto).
                        collect(Collectors.toList());
        return responseService.getListResult(result);
    }

    @GetMapping("/message/received")
    public Result receivedMessages(@AuthenticationPrincipal PrincipalDetails principal) {
        List<Message> byReceivedMember = messageService.findByReceivedMember(principal.getMember());
        List<MessageResponseDto> result=byReceivedMember.stream()
                .map(MessageResponseDto::covertMessageDto).
                collect(Collectors.toList());
        return responseService.getListResult(result);
    }


    @GetMapping("/message/{message_id}")
    public Result readMessage(@PathVariable Long message_id) {
        MessageResponseDto messageResponseDto=messageService.findByMessageId(message_id);
        return responseService.getSingleResult(messageResponseDto);
    }
}


