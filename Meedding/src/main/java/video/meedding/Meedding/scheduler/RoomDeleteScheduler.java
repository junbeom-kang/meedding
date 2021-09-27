package video.meedding.Meedding.scheduler;

import io.openvidu.java.client.OpenVidu;
import io.openvidu.java.client.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import video.meedding.Meedding.exception.NoRoomSessionException;
import video.meedding.Meedding.repository.RoomRepository;
import video.meedding.Meedding.service.RoomService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomDeleteScheduler {
    private final OpenVidu openVidu;
    private final RoomRepository roomRepository;

    @Scheduled(fixedDelay = 1000L * 300)
    public void deleteTrashRoom() {
        Set<String> collect = openVidu.getActiveSessions().stream().map(Session::getSessionId).collect(Collectors.toSet());
        List<String> allSession = roomRepository.findAllSession();
        for (String session : allSession) {
            if (!collect.contains(session)) {
                roomRepository.delete(roomRepository.findBySession(session).orElseThrow(NoRoomSessionException::new));
            }
        }

    }

}
