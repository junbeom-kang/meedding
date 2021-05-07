package video.meedding.Meedding.domain;


import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class Member {
    @Id @GeneratedValue
    @Column(name="member_id")
    private Long id;
    private String name;
    private String login_id;
    private String password;
    @OneToMany(mappedBy = "receivedMember",cascade = CascadeType.ALL)
    private List<Message> receivedMessage;

    @OneToMany(mappedBy = "sentMember",cascade = CascadeType.ALL)
    private List<Message> sentMessage;

    @OneToOne(mappedBy="member")
    private Friends friends;


}