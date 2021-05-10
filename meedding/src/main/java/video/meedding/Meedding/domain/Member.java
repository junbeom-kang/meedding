package video.meedding.Meedding.domain;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Member {
    @Id @GeneratedValue
    @Column(name="member_id")
    private Long id;
    private String name;
    private String nickname;
    private LocalDate signUpDate;
    private String loginid;
    private String password;
    @OneToMany(mappedBy = "receivedMember",cascade = CascadeType.ALL)
    private List<Message> receivedMessage;

    @OneToMany(mappedBy = "sentMember",cascade = CascadeType.ALL)
    private List<Message> sentMessage;
    private String token;

    public static Member createMember(String name, String nickname, String loginid, String password) {
        Member member=new Member();
        member.name = name;
        member.nickname = nickname;
        member.loginid = loginid;
        member.password = password;
        member.signUpDate=LocalDate.now();
        return member;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin_id(String login_id) {
        this.loginid = login_id;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


}