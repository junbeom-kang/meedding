package video.meedding.Meedding.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import video.meedding.Meedding.domain.Room;
import video.meedding.Meedding.dto.CreateMemberDto;
import video.meedding.Meedding.dto.CreateRoomDto;
import video.meedding.Meedding.exception.NoMatchingRoomException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class RoomServiceTest {
    @Autowired MemberService memberService;
    @Autowired RoomService roomService;

    @Test
    public void 방_생성_JPA_테스트() throws Exception {
        //given
        Long makeMemberId = memberService.join(new CreateMemberDto("snet1@n.c", "sample", "sample", "sent", "sentNick1"));
        CreateRoomDto createRoomDto=new CreateRoomDto("방제목");

        //when
        Long roomId = roomService.Room(makeMemberId, createRoomDto);

        //then
        assertThat(roomId).isEqualTo(roomService.findById(roomId).getId());
    }
    @Test
    public void 방_조회_방목록_조회() throws Exception {
        //given
        Long makeMemberId = memberService.join(new CreateMemberDto("snet1@n.c", "sample", "sample", "sent", "sentNick1"));
        CreateRoomDto createRoomDto=new CreateRoomDto("방제목");
        CreateRoomDto createRoomDto1 = new CreateRoomDto("방제목1");
        CreateRoomDto createRoomDto2 = new CreateRoomDto("방제목2");
        //when
        Long room = roomService.Room(makeMemberId, createRoomDto);
        roomService.Room(makeMemberId,createRoomDto1);
        roomService.Room(makeMemberId, createRoomDto2);
        Room result=roomService.findById(room);
        //then
        assertThat(room).isEqualTo(result.getId());
        assertThat(roomService.allRoom().size()).isEqualTo(3);
        assertThat(roomService.findRoom(result.getRoomNumber())).isEqualTo(result);
        
    }
    @Test
    public void 방_삭제_JPA_테스트() throws Exception {
        Long makeMemberId = memberService.join(new CreateMemberDto("snet1@n.c", "sample", "sample", "sent", "sentNick1"));
        CreateRoomDto createRoomDto=new CreateRoomDto("방제목");
        CreateRoomDto createRoomDto1 = new CreateRoomDto("방제목1");
        //when
        Long room = roomService.Room(makeMemberId, createRoomDto);
        Long room1 = roomService.Room(makeMemberId, createRoomDto1);
        Room result=roomService.findById(room);
        roomService.deleteRoom(room1);
        Exception e = assertThrows(NoMatchingRoomException.class, () -> roomService.findById(room1));
        //then
        assertThat(roomService.allRoom().size()).isEqualTo(1);
        assertThat(e.getMessage()).isEqualTo("해당 방이 없습니다");

    }

}