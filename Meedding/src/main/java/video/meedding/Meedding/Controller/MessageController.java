package video.meedding.Meedding.Controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "메시지 전송", notes = "해당 회원에게 메시지를 전송한다.")
    @ApiImplicitParam(name = "Authorization", value = "access-token", required = true, dataType = "String", paramType = "header")
    @PostMapping("/message")
    public Result createMessage(@RequestBody @Valid CreateMessageDto createMessageDto,@AuthenticationPrincipal PrincipalDetails principal) {
        Long message = messageService.Message(principal.getMember().getId(),createMessageDto);
        return responseService.getSingleResult(message);
    }
    @ApiOperation(value = "보낸 메세지 목록", notes = "토큰 주인의 보낸 메세지 목록을 받아온다.")
    @ApiImplicitParam(name = "Authorization", value = "access-token", required = true, dataType = "String", paramType = "header")
    @GetMapping("/message/sent")
    public Result sentMessages(@AuthenticationPrincipal PrincipalDetails principal) {
        List<Message> bySentMember = messageService.findBySentMember(principal.getMember());
        List<MessageResponseDto> result=bySentMember.stream()
                .map(MessageResponseDto::covertMessageDto).
                        collect(Collectors.toList());
        return responseService.getListResult(result);
    }
    @ApiOperation(value = "받은 메세지 목록", notes = "토큰 주인의 받은 메세지 목록을 받아온다.")
    @ApiImplicitParam(name = "Authorization", value = "access-token", required = true, dataType = "String", paramType = "header")
    @GetMapping("/message/received")
    public Result receivedMessages(@AuthenticationPrincipal PrincipalDetails principal) {
        List<Message> byReceivedMember = messageService.findByReceivedMember(principal.getMember());
        List<MessageResponseDto> result=byReceivedMember.stream()
                .map(MessageResponseDto::covertMessageDto).
                collect(Collectors.toList());
        return responseService.getListResult(result);
    }

    @ApiOperation(value = "메시지 단건 조회", notes = "해당 PK의 메시지를 단건 조회한다.")
    @ApiImplicitParam(name = "Authorization", value = "access-token", required = true, dataType = "String", paramType = "header")
    @GetMapping("/message/{message_id}")
    public Result readMessage(@PathVariable Long message_id) {
        MessageResponseDto messageResponseDto=messageService.findByMessageId(message_id);
        return responseService.getSingleResult(messageResponseDto);
    }
}


