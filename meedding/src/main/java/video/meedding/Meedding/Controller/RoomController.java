package video.meedding.Meedding.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import video.meedding.Meedding.domain.Room;
import video.meedding.Meedding.dto.CreateRoomDto;
import video.meedding.Meedding.dto.RoomResponseDto;
import video.meedding.Meedding.dto.response.Result;
import video.meedding.Meedding.service.MemberService;
import video.meedding.Meedding.service.RoomService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;
/*
    @PostMapping("/rooms")
    public Result createRoom(@RequestBody CreateRoomDto createRoomDto) {
        Long id = SessionUser.getId();
        Long room = roomService.Room(id, createRoomDto);
        return new Result<Integer>(HttpStatus.OK.value());
    }

    @GetMapping("rooms")
    public Result roomList() {
        List<Room> rooms = roomService.allRoom();
        List<RoomResponseDto>room=rooms.stream()
                .map(m->new RoomResponseDto(m.getId(),m.getRoomTitle()))
                .collect(Collectors.toList());
        return new Result(room);
    }
    */
}


