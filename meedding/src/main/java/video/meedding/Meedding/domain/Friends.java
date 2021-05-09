package video.meedding.Meedding.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Friend {
    @Id @GeneratedValue
    @Column(name="friend_id")
    private Long id;

    @OneToOne
    @JoinColumn(name="addMember_id")
    private Member addMember;

    @OneToOne
    @JoinColumn(name="targetMember_id")
    private Member targetMember;

    public static Friend createFriend(Member addMember, Member targetMember) {
        Friend friend = new Friend();
        friend.addMember=addMember;
        friend.targetMember=targetMember;
        return friend;
    }

}
