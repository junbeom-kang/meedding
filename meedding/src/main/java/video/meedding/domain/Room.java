package video.meedding.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Room {
    @Id @GeneratedValue
    @Column(name="room_id")
    private Long id;

    private Long roomNumber;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member createMember;

}
