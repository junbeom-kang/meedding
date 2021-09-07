package video.meedding.Meedding.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.openvidu.java.client.Session;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Room {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="room_id")
    private Long id;

    private String roomTitle;

    private String roomPassword;

    private String session;

    @OneToMany(mappedBy = "room",orphanRemoval = true)
    private List<RoomParticipate> participateList=new ArrayList<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="member_id")
    private Member createMember;


    //생성 메서드
    public static Room createRoom(Member member, String roomTitle, String roomPassword, Session session) {
        Room room=new Room();
        room.createMember=member;
        room.roomTitle=roomTitle;
        room.roomPassword=roomPassword;
        room.session=session.getSessionId();
        return room;
    }

}