package video.meedding.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Message {
    @Id @GeneratedValue
    @Column(name="message_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member sentMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member receivedMember;


    private String contents;

}
