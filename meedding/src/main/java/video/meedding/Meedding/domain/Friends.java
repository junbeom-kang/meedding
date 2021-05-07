package video.meedding.Meedding.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class Friends {
    @Id @GeneratedValue
    @Column(name="friend_id")
    private Long id;

    @OneToOne
    @JoinColumn(name="addMember_id")
    private Member addMember;

    @OneToOne
    @JoinColumn(name="targetMember_id")
    private Member targetMember;


}
