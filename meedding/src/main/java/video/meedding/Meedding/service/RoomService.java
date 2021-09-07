package video.meedding.Meedding.service;

import io.openvidu.java.client.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import video.meedding.Meedding.domain.Member;
import video.meedding.Meedding.domain.Room;
import video.meedding.Meedding.domain.RoomParticipate;
import video.meedding.Meedding.dto.RoomDto;
import video.meedding.Meedding.exception.*;
import video.meedding.Meedding.repository.MemberRepository;
import video.meedding.Meedding.repository.RoomParticipateRepository;
import video.meedding.Meedding.repository.RoomRepository;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final MemberRepository memberRepository;
    private final OpenVidu openVidu;
    private final RoomParticipateRepository roomParticipateRepository;
    @Transactional
    public String Room(Long memberId, RoomDto roomDto) throws OpenViduJavaClientException, OpenViduHttpException {
        if (roomRepository.findByRoomTitle(roomDto.getTitle()).isPresent()) {
            throw new ExistedRoomNameException();
        }
        Member member = memberRepository.findById(memberId).orElseThrow(NoMemberException::new);
        Session session = openVidu.createSession();
        Room room=Room.createRoom(member, roomDto.getTitle(), roomDto.getPassword(),session);
        Room result=roomRepository.save(room);
        return result.getSession();
    }

    @Transactional
    public String join(Long memberId, RoomDto roomDto) throws OpenViduJavaClientException, OpenViduHttpException {
        Room room = roomRepository.findByRoomTitle(roomDto.getTitle()).orElseThrow(NoSuchRoomException::new);
        if (!roomDto.getPassword().equals(room.getRoomPassword())) {
            throw new PasswordDiffException();
        }
        Member participant = memberRepository.findById(memberId).orElseThrow(NoMemberException::new);
        ConnectionProperties connectionProperties = new ConnectionProperties.Builder()
                .type(ConnectionType.WEBRTC)
                .role(OpenViduRole.PUBLISHER)
                .data("userData")
                .build();
        List<Session> activeSessions = openVidu.getActiveSessions();
        Session session = activeSessions.stream()
                .filter(s -> s.getSessionId().equals(room.getSession()))
                .findFirst()
                .orElseThrow();
        System.out.println(session);
        if (session == null) {
            throw new NoRoomSessionException();
        }
        String token = session.createConnection(connectionProperties).getToken();
        roomParticipateRepository.save(RoomParticipate.roomParticipate(token,participant,room));
        return token;
    }

    public List<Room> allRoom() {
        return roomRepository.findAll();
    }

    @Transactional
    public void deleteRoom(Long member_id,Long roomId) {
        Room room=roomRepository.findById(roomId).orElseThrow(() -> new NoMatchingRoomException("일치하는 방이 없습니다"));
        if (!room.getCreateMember().getId().equals(member_id)) {
            throw new NoRoomCreatorException();
        }
        roomRepository.delete(room);
    }

    public Room findById(Long id) {
        return roomRepository.findById(id).orElseThrow(() -> new NoMatchingRoomException("해당 방이 없습니다"));
    }
    public Room findByTitle(String title) {
        return roomRepository.findByRoomTitle(title).orElseThrow(() -> new NoMatchingRoomException("일치하는 방이 없습니다."));
    }


}
