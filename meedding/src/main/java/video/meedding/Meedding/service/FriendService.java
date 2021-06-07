package video.meedding.Meedding.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import video.meedding.Meedding.domain.Friend;
import video.meedding.Meedding.domain.Member;
import video.meedding.Meedding.exception.CantMakeFriendException;
import video.meedding.Meedding.exception.DuplicateFriendException;
import video.meedding.Meedding.exception.NoFriendRelationException;
import video.meedding.Meedding.exception.NoMemberException;
import video.meedding.Meedding.repository.FriendRepository;
import video.meedding.Meedding.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class FriendService {
    private final MemberRepository memberRepository;
    private final FriendRepository friendRepository;
    //친구추가
    @Transactional
    public Long makeFriend(Long addMemberId, Long targetMemberId) {
        Member member = memberRepository.findById(addMemberId).orElseThrow(() -> new NoMemberException("없는 회원입니다"));
        Member member1 = memberRepository.findById(targetMemberId).orElseThrow(() -> new NoMemberException("없는 회원입니다"));
        if (member == member1) {
            throw new CantMakeFriendException("나 자신을 추가할 수 없습니다");
        } else if (friendRepository.findByAddMemberAndTargetMember(member,member1).isPresent()) {
            throw new DuplicateFriendException("이미 추가된 친구입니다");
        } else {
            Friend friend = friendRepository.save(Friend.createFriend(member, member1));
            return friend.getId();

        }
    }

    public Friend findById(Long id) {
        return friendRepository.findById(id).orElseThrow(() -> new NoFriendRelationException("없는 친구관계입니다"));
    }
    //추가한 친구 목록
    public List<Friend> addFriendList(Long myId) {
        //Member result = memberRepository.findById(myId).orElseThrow(() -> new NoMemberException("없는 회원입니다"));
        return friendRepository.findByAddMember(myId);
    }
    //회원을 친구로 추가된 친구 목록
    public List<Friend> targetFriend(Long myId) {
        return friendRepository.findByTargetMember(myId);
    }

    //친구삭제
    @Transactional
    public void removeFriend(Long myId, Long targetId) {
        Member me = memberRepository.getOne(myId);
        Member targetMember = memberRepository.findById(targetId).orElseThrow(
                () -> new NoMemberException("없는 회원입니다"));
        Friend result=friendRepository.findByAddMemberAndTargetMember(me, targetMember).orElseThrow(
                ()->new NoFriendRelationException("친구 사이가 아닙니다."));
        friendRepository.delete(result);
    }


}
