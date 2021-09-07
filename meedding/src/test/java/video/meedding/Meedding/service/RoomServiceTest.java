package video.meedding.Meedding.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import video.meedding.Meedding.domain.Room;
import video.meedding.Meedding.dto.CreateMemberDto;
import video.meedding.Meedding.dto.RoomDto;
import video.meedding.Meedding.exception.NoMatchingRoomException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class RoomServiceTest {
    @Autowired MemberService memberService;
    @Autowired RoomService roomService;
    /*
    @Test
    public void 방_생성_JPA_테스트() throws Exception {
        //given
        Long makeMemberId = memberService.join(new CreateMemberDto("snet1@n.c", "sample", "sample", "sent", "sentNick1"));
        RoomDto roomDto =new RoomDto("방제목","1234");

        //when
        String room = roomService.Room(makeMemberId, roomDto);

        //then
        assertThat(room).isEqualTo(roomService.findBySession(room).getSession().toString());
    }
    @Test
    public void 방_조회_방목록_조회() throws Exception {
        //given
        Long makeMemberId = memberService.join(new CreateMemberDto("snet1@n.c", "sample", "sample", "sent", "sentNick1"));
        RoomDto roomDto =new RoomDto("방제목");
        RoomDto roomDto1 = new RoomDto("방제목1");
        RoomDto roomDto2 = new RoomDto("방제목2");
        //when
        Long room = roomService.Room(makeMemberId, roomDto);
        roomService.Room(makeMemberId, roomDto1);
        roomService.Room(makeMemberId, roomDto2);
        Room result=roomService.findById(room);
        //then
        assertThat(room).isEqualTo(result.getId());
        assertThat(roomService.allRoom().size()).isEqualTo(3);
        assertThat(roomService.findRoom(result.getRoomNumber())).isEqualTo(result);
        
    }

    @Test
    public void 방_삭제_JPA_테스트() throws Exception {
        Long makeMemberId = memberService.join(new CreateMemberDto("snet1@n.c", "sample", "sample", "sent", "sentNick1"));
        RoomDto roomDto =new RoomDto("방제목");
        RoomDto roomDto1 = new RoomDto("방제목1");
        //when
        Long room = roomService.Room(makeMemberId, roomDto);
        Long room1 = roomService.Room(makeMemberId, roomDto1);
        Room result=roomService.findById(room);
        roomService.deleteRoom(room1);
        Exception e = assertThrows(NoMatchingRoomException.class, () -> roomService.findById(room1));
        //then
        assertThat(roomService.allRoom().size()).isEqualTo(1);
        assertThat(e.getMessage()).isEqualTo("해당 방이 없습니다");

    }

     */

}