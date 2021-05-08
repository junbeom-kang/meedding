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
    private String nickname;


    private String login_id;
    private String password;
    @OneToMany(mappedBy = "receivedMember",cascade = CascadeType.ALL)
    private List<Message> receivedMessage;

    @OneToMany(mappedBy = "sentMember",cascade = CascadeType.ALL)
    private List<Message> sentMessage;


    public void setName(String name) {
        this.name = name;
    }

    public void setLogin_id(String login_id) {
        this.login_id = login_id;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


}