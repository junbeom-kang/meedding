package video.meedding.Meedding.Controller;

import io.openvidu.java.client.OpenViduHttpException;
import io.openvidu.java.client.OpenViduJavaClientException;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import video.meedding.Meedding.config.auth.PrincipalDetails;
import video.meedding.Meedding.domain.Member;
import video.meedding.Meedding.domain.Room;
import video.meedding.Meedding.dto.*;
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
    @ApiOperation(value = "회의방 생성", notes = "회의방 생성 성공시 세션값을 반환한다.")
    @ApiImplicitParam(name = "Authorization", value = "access-token", required = true, dataType = "String", paramType = "header")
    @PostMapping("/rooms")
    public Result createRoom(@RequestBody RoomDto roomDto, @AuthenticationPrincipal PrincipalDetails principal) throws OpenViduJavaClientException, OpenViduHttpException {
        String session = roomService.Room(principal.getMember().getId(), roomDto);
        return responseService.getSingleResult(session);
    }
    @ApiOperation(value = "회의방 참가", notes = "회의방 참가 성공시 토큰값을 반환한다.")
    @ApiImplicitParam(name = "Authorization", value = "access-token", required = true, dataType = "String", paramType = "header")
    @PostMapping("/rooms/join")
    public Result join(@RequestBody RoomDto roomDto, @AuthenticationPrincipal PrincipalDetails principal) throws OpenViduJavaClientException, OpenViduHttpException {
        String session = roomService.join(principal.getMember().getId(), roomDto);
        return responseService.getSingleResult(session);
    }
    @ApiOperation(value = "회의방 목록", notes = "회의방 목록을 반환한다.")
    @ApiImplicitParam(name = "Authorization", value = "access-token", required = true, dataType = "String", paramType = "header")
    @GetMapping("/rooms")
    public Result roomList() {
        List<Room> rooms = roomService.allRoom();
        List<RoomResponseDto> room = rooms.stream()
                .map(m -> new RoomResponseDto(m.getId(), m.getRoomTitle(), m.getSession(), m.getPeopleNum()))
                .collect(Collectors.toList());
        return responseService.getListResult(room);
    }
    @ApiOperation(value = "회의방 제목으로 찾기", notes = "회의방을 제목으로 검색하여 반환한다.")
    @ApiImplicitParam(name = "Authorization", value = "access-token", required = true, dataType = "String", paramType = "header")
    @PostMapping("/rooms/title")
    public Result findByTitle(@RequestBody RoomTitleRequestDto roomTitleRequestDto) {
        return responseService.getSingleResult(roomService.findByTitle(roomTitleRequestDto.getTitle()));
    }
    @ApiOperation(value = "회의방 제거", notes = "회의방을 제거한다")
    @ApiImplicitParam(name = "Authorization", value = "access-token", required = true, dataType = "String", paramType = "header")
    @DeleteMapping("/rooms/{room_id}")
    public Result deleteRoom(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long room_id) {
        roomService.deleteRoom(principalDetails.getMember().getId(), room_id);
        return responseService.getSuccessResult();
    }
    @ApiOperation(value = "회의방 나가기", notes = "회의방 세션에서 나가지고 참여자를 줄인다")
    @ApiImplicitParam(name = "Authorization", value = "access-token", required = true, dataType = "String", paramType = "header")
    @GetMapping("rooms/leave/{room_id}")
    public Result leaveRoom(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long room_id, @RequestHeader("token") String token) throws OpenViduJavaClientException, OpenViduHttpException {
        roomService.leaveSession(principalDetails.getMember().getId(), room_id, token);
        return responseService.getSuccessResult();
    }

    @GetMapping("rooms/deleteAll")
    public void deleteAll() {
        roomService.deleteAll();
    }
    @ApiOperation(value = "회의방 참여자 목록", notes = "해당 회의방의 참여자 목록을 반환한다.")
    @ApiImplicitParam(name = "Authorization", value = "access-token", required = true, dataType = "String", paramType = "header")
    @GetMapping("rooms/People/{room_id}")
    public Result getParticipateList(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long room_id){
        List<ResponseParticipateDto> participate = roomService.getParticipate(room_id);
        return responseService.getListResult(participate);

    }

}


