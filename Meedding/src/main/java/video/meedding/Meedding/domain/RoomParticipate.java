package video.meedding.Meedding.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class RoomParticipate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roomParticipate_id")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private String token;

    public static RoomParticipate roomParticipate(String token,Member member,Room room) {
        RoomParticipate roomParticipate=new RoomParticipate();
        roomParticipate.token=token;
        roomParticipate.member=member;
        roomParticipate.setRoom(room);
        return roomParticipate;
    }

    //연관관계 메서드
    public void setRoom(Room room) {
        this.room=room;
        room.getParticipateList().add(this);
    }
}
