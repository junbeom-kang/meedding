package video.meedding.Meedding.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Message {
    @Id @GeneratedValue
    @Column(name="message_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="sent_member_id")
    private Member sentMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="received_member_id")
    private Member receivedMember;
    private String title;
    private String contents;
    private LocalDate date;

    //연관관계 메서드
    public void setSentMember(Member member) {
        this.sentMember=member;
        member.getSentMessage().add(this);
    }

    public void setReceivedMember(Member member) {
        this.receivedMember=member;
        member.getReceivedMessage().add(this);
    }

    //생성 메서드
    public static Message createMessage(Member sentMember, Member receivedMember,String title,String contents) {
        Message message=new Message();
        message.sentMember=sentMember;
        message.receivedMember=receivedMember;
        message.title=title;
        message.contents=contents;
        message.date=LocalDate.now();
        return message;
    }



}