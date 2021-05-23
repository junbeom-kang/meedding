package video.meedding.Meedding.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import video.meedding.Meedding.domain.Member;
import video.meedding.Meedding.domain.Room;
import video.meedding.Meedding.dto.CreateRoomDto;
import video.meedding.Meedding.exception.NoMatchingRoomException;
import video.meedding.Meedding.exception.NoMemberException;
import video.meedding.Meedding.repository.MemberRepository;
import video.meedding.Meedding.repository.RoomRepository;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long Room(Long memberId, CreateRoomDto createRoomDto) {
        String roomNumber=getRoomNumber();
        String roomPassword=getRoomPassword();
        Member member = memberRepository.findById(memberId).orElseThrow(()->new NoMemberException());
        Room room=Room.createRoom(member,createRoomDto.getTitle(),roomNumber,roomPassword);
        Room result=roomRepository.save(room);
        return result.getId();
    }
    public List<Room> allRoom() {
        return roomRepository.findAll();
    }

    public Room findRoom(String roomNumber) {
        return roomRepository.findByRoomNumber(roomNumber).orElseThrow(
                () -> new NoMatchingRoomException("일치하는 방이 없습니다"));
    }
    @Transactional
    public void deleteRoom(Long roomId) {
        Room room=roomRepository.findById(roomId).orElseThrow(() -> new NoMatchingRoomException("일치하는 방이 없습니다"));
        roomRepository.delete(room);
    }

    public Room findById(Long id) {
        return roomRepository.findById(id).orElseThrow(() -> new NoMatchingRoomException("해당 방이 없습니다"));
    }
    public Room findByTitle(String title) {
        return roomRepository.findByRoomTitle(title).orElseThrow(() -> new NoMatchingRoomException("일치하는 방이 없습니다."));
    }




    private static String getRoomNumber() {
        return UUID.randomUUID().toString();
    }
    private static String getRoomPassword() {
        return UUID.randomUUID().toString();
    }
}
