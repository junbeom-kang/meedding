package video.meedding.Meedding.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import video.meedding.Meedding.config.auth.PrincipalDetails;
import video.meedding.Meedding.domain.Room;
import video.meedding.Meedding.dto.CreateRoomDto;
import video.meedding.Meedding.dto.RoomNumberDto;
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
    public Result createRoom(@RequestBody CreateRoomDto createRoomDto, @AuthenticationPrincipal PrincipalDetails principal) {
        Long room = roomService.Room(principal.getMember().getId(), createRoomDto);
        return responseService.getSingleResult(room);
    }

    @GetMapping("rooms")
    public Result roomList() {
        List<Room> rooms = roomService.allRoom();
        List<RoomResponseDto> room = rooms.stream()
                .map(m -> new RoomResponseDto(m.getId(), m.getRoomTitle()))
                .collect(Collectors.toList());
        return responseService.getListResult(room);
    }

    @PostMapping("/rooms/title")
    public Result findByTitle(@RequestBody RoomTitleRequestDto roomTitleRequestDto) {
        Room result = roomService.findByTitle(roomTitleRequestDto.getTitle());
        return responseService.getSingleResult(result);
    }
    @PostMapping("/rooms/roomNumber")
    public Result findByTitle(@RequestBody RoomNumberDto roomNumberDto) {
        Room result = roomService.findRoom(roomNumberDto.getRoomNumber());
        return responseService.getSingleResult(result);
    }
}


