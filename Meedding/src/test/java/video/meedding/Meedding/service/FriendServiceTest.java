package video.meedding.Meedding.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import video.meedding.Meedding.domain.Friend;
import video.meedding.Meedding.dto.CreateMemberDto;
import video.meedding.Meedding.exception.CantMakeFriendException;
import video.meedding.Meedding.exception.DuplicateFriendException;
import video.meedding.Meedding.exception.NoFriendRelationException;
import video.meedding.Meedding.repository.MemberRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

/*
@SpringBootTest
@Transactional()
class FriendServiceTest {
    @Autowired MemberService memberService;
    @Autowired FriendService friendService;

    @Test
    public void 친구_추가_JPA_테스트() throws Exception {
        //given
        Long sentId = memberService.join(new CreateMemberDto("snet1@n.c", "sample", "sample", "sent", "sentNick1"));
        Long receiveId = memberService.join(new CreateMemberDto("receive@n.c", "sample", "sample", "receive", "receiveNick1"));

        //when
        Long result = friendService.makeFriend(sentId, receiveId);
        List<Friend> resultList = friendService.addFriendList(sentId);
        Friend friend = friendService.findById(result);

        //then
        assertThat(friend.getId()).isEqualTo(result);

    }
    @Test
    public void 회원으로_조회_테스트() throws Exception {
        //given
        Long id[]=createMember();
        //when
        Long relation1 = friendService.makeFriend(id[0], id[1]);
        Long relation2 = friendService.makeFriend(id[0], id[2]);
        List<Friend> addFriendList = friendService.addFriendList(id[0]);
        List<Friend> friends = friendService.targetFriend(id[2]);
        //then
        assertThat(addFriendList.size()).isEqualTo(2);
        assertThat(friends.size()).isEqualTo(1);
    }

    @Test
    public void 친구_삭제_테스트() throws Exception {
        //given
        Long id[]=createMember();
        Long relation1 = friendService.makeFriend(id[0], id[1]);
        Long relation2 = friendService.makeFriend(id[0], id[2]);


        //when
        friendService.removeFriend(id[0],id[2]);
        Exception e = assertThrows(NoFriendRelationException.class, () ->friendService.findById(relation2));
        List<Friend> addFriendList = friendService.addFriendList(id[0]);
        List<Friend> friends = friendService.targetFriend(id[2]);

        //then
        assertThat(addFriendList.size()).isEqualTo(1);
        assertThat(friends.size()).isEqualTo(0);
        assertThat(e.getMessage()).isEqualTo("없는 친구관계입니다");

    }
    @Test
    public void 친구추가_중복_자신_추가_테스트() throws Exception {
        //given
        Long id[]=createMember();
        Long relation1 = friendService.makeFriend(id[0], id[1]);
        Long relation2 = friendService.makeFriend(id[0], id[2]);

        //when
        Exception e=assertThrows(DuplicateFriendException.class, () -> friendService.makeFriend(id[0], id[1]));
        Exception e1 = assertThrows(CantMakeFriendException.class, () -> friendService.makeFriend(id[1], id[1]));
        //then
        assertThat(e.getMessage()).isEqualTo("이미 추가된 친구입니다");
        assertThat(e1.getMessage()).isEqualTo("나 자신을 추가할 수 없습니다");
    }


    public Long[] createMember() {
        Long sentId = memberService.join(new CreateMemberDto("snet@n.c", "sample", "sample", "sent", "sentNick"));
        Long receiveId = memberService.join(new CreateMemberDto("receive@n.c", "sample", "sample", "receive", "receiveNick"));
        Long thirdId = memberService.join(new CreateMemberDto("abcd@n.c", "sample", "sample", "sammple", "thirdMember"));

        return new Long[]{sentId,receiveId,thirdId};
    }

}

 */