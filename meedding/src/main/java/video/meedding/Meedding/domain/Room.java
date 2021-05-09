package video.meedding.Meedding.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Room {
    @Id @GeneratedValue
    @Column(name="room_id")
    private Long id;
    private String roomTitle;
    private String roomNumber;
    private String roomPassword;
    @ManyToOne
    @JoinColumn(name="member_id")
    private Member createMember;

    //생성 메서드
    public static Room createRoom(Member member, String roomTitle, String roomNumber,String roomPassword) {
        Room room=new Room();
        room.createMember=member;
        room.roomTitle=roomTitle;
        room.roomNumber=roomNumber;
        room.roomPassword=roomPassword;
        return room;
    }

}