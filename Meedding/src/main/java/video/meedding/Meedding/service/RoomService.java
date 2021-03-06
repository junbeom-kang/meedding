package video.meedding.Meedding.service;

import io.openvidu.java.client.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import video.meedding.Meedding.domain.Member;
import video.meedding.Meedding.domain.Room;
import video.meedding.Meedding.domain.RoomParticipate;
import video.meedding.Meedding.dto.ResponseParticipateDto;
import video.meedding.Meedding.dto.ResponseRoomDto;
import video.meedding.Meedding.dto.RoomDto;
import video.meedding.Meedding.exception.*;
import video.meedding.Meedding.repository.MemberRepository;
import video.meedding.Meedding.repository.RoomParticipatorRepository;
import video.meedding.Meedding.repository.RoomRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final MemberRepository memberRepository;
    private final OpenVidu openVidu;
    private final RoomParticipatorRepository roomParticipateRepository;
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
        Room room = roomRepository.findByRoomTitle(roomDto.getTitle()).orElseThrow(NoMatchingRoomException::new);
        if (!roomDto.getPassword().equals(room.getRoomPassword())) {
            throw new RoomPasswordWrongException();
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
        if (session == null) {
            roomRepository.delete(room);
            throw new NoRoomSessionException();
        }
        String token = session.createConnection(connectionProperties).getToken();
        roomParticipateRepository.save(RoomParticipate.roomParticipate(token,participant,room));
        room.plusPeopleNum();
        return token;
    }

    public List<Room> allRoom() {
        return roomRepository.findAllByPeopleNum();
    }

    @Transactional
    public void deleteRoom(Long member_id,Long roomId) {
        Room room=roomRepository.findById(roomId).orElseThrow(() -> new NoMatchingRoomException("???????????? ?????? ????????????"));
        if (!room.getCreateMember().getId().equals(member_id)) {
            throw new NoRoomCreatorException();
        }
        roomRepository.delete(room);
    }

    public Room findById(Long id) {
        return roomRepository.findById(id).orElseThrow(() -> new NoMatchingRoomException("?????? ?????? ????????????"));
    }
    public ResponseRoomDto findByTitle(String title) {
        Room room = roomRepository.findByRoomTitle(title).orElseThrow(() -> new NoMatchingRoomException("???????????? ?????? ????????????."));
        return ResponseRoomDto.convertToResponseRoomDto(room);
    }
    @Transactional
    public void leaveSession(Long member_id,Long room_id,String token) throws OpenViduJavaClientException, OpenViduHttpException {
        Room room = roomRepository.findById(room_id).orElseThrow(NoMatchingRoomException::new);
        List<Session> activeSessions = openVidu.getActiveSessions();
        Session session = activeSessions.stream()
                .filter(s -> s.getSessionId().equals(room.getSession()))
                .findFirst()
                .orElseThrow();
        List<Connection> activeConnections = session.getConnections();
        Connection connection = activeConnections.stream()
                .filter(s -> s.getToken().equals(token))
                .findFirst()
                .orElseThrow();
        session.forceDisconnect(connection);
        RoomParticipate roomParticipate = roomParticipateRepository.findRoomParticipateByMemberAndRoom(member_id, room_id).orElseThrow(NoMatchingRoomException::new);
        room.getParticipateList().remove(roomParticipate);
        room.minusPeopleNum();
        roomParticipateRepository.delete(roomParticipate);
        if (room.getPeopleNum() == 0) {
            roomRepository.delete(room);
        }
    }
    @Transactional
    public void deleteAll() {
        roomRepository.deleteAll();
    }
    public List<ResponseParticipateDto> getParticipate(Long room_id) {
        Room room = roomRepository.findById(room_id).orElseThrow(NoMatchingRoomException::new);
        List<RoomParticipate> participateList = room.getParticipateList();
        return participateList.stream().map(ResponseParticipateDto::convertToDto).collect(Collectors.toList());
    }

}
