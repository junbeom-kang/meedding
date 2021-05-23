package video.meedding.Meedding.domain;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Member {
    @Id @GeneratedValue
    @Column(name="member_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false,unique = true)
    private String nickname;

    @Column(nullable = false,unique = true)
    private String loginid;

    private String password;
    private LocalDate signUpDate;

    @OneToMany(mappedBy = "receivedMember",cascade = CascadeType.ALL)
    private List<Message> receivedMessage;

    @OneToMany(mappedBy = "sentMember",cascade = CascadeType.ALL)
    private List<Message> sentMessage;
    private String token;

    private String roles;

    public static Member createMember(String name, String nickname, String loginid, String password) {
        Member member=new Member();
        member.name = name;
        member.nickname = nickname;
        member.loginid = loginid;
        member.password = password;
        member.signUpDate=LocalDate.now();
        member.roles= "ROLE_USER";
        return member;
    }
    public List<String> getRoleList(){
        if(this.roles.length() > 0){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
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