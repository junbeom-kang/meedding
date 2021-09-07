package video.meedding.Meedding.Controller;

import io.openvidu.java.client.OpenViduHttpException;
import io.openvidu.java.client.OpenViduJavaClientException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import video.meedding.Meedding.config.auth.PrincipalDetails;
import video.meedding.Meedding.domain.Member;
import video.meedding.Meedding.domain.Room;
import video.meedding.Meedding.dto.RoomDto;
import video.meedding.Meedding.dto.RoomResponseDto;
import video.meedding.Meedding.dto.RoomTitleRequestDto;
import video.meedding.Meedding.dto.response.Result;
import video.meedding.Meedding.service.ResponseService;
import video.meedding.Meedding.service.RoomService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;
    private final ResponseService responseService;

    @PostMapping("/rooms")
    public Result createRoom(@RequestBody RoomDto roomDto, @AuthenticationPrincipal PrincipalDetails principal) throws OpenViduJavaClientException, OpenViduHttpException {
        String session = roomService.Room(principal.getMember().getId(), roomDto);
        return responseService.getSingleResult(session);
    }

    @PostMapping("/rooms/join")
    public Result join(@RequestBody RoomDto roomDto, @AuthenticationPrincipal PrincipalDetails principal) throws OpenViduJavaClientException, OpenViduHttpException {
        String session = roomService.join(principal.getMember().getId(), roomDto);
        return responseService.getSingleResult(session);
    }

    @GetMapping("/rooms")
    public Result roomList() {
        List<Room> rooms = roomService.allRoom();
        List<RoomResponseDto> room = rooms.stream()
                .map(m -> new RoomResponseDto(m.getId(), m.getRoomTitle(), m.getSession(), m.getParticipateList().size()))
                .collect(Collectors.toList());
        return responseService.getListResult(room);
    }

    @PostMapping("/rooms/title")
    public Result findByTitle(@RequestBody RoomTitleRequestDto roomTitleRequestDto) {
        Room result = roomService.findByTitle(roomTitleRequestDto.getTitle());
        return responseService.getSingleResult(result);
    }

    @DeleteMapping("/rooms/{room_id}")
    public Result deleteRoom(@AuthenticationPrincipal PrincipalDetails principalDetails,@PathVariable Long room_id){
        roomService.deleteRoom(principalDetails.getMember().getId(),room_id);
        return responseService.getSuccessResult();
    }
    @GetMapping("rooms/leave/{room_id}")
    public Result leaveRoom(@AuthenticationPrincipal PrincipalDetails principalDetails,@PathVariable Long room_id,@RequestHeader("token")String token) throws OpenViduJavaClientException, OpenViduHttpException {
        roomService.leaveSession(principalDetails.getMember().getId(),room_id,token);
        return responseService.getSuccessResult();
    }

}


