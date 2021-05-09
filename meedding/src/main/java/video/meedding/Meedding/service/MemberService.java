package video.meedding.Meedding.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import video.meedding.Meedding.domain.Member;
import video.meedding.Meedding.dto.CreateMemberDto;
import video.meedding.Meedding.exception.ExistedEmailException;
import video.meedding.Meedding.exception.NoMemberException;
import video.meedding.Meedding.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    @Transactional
    public void join(Member member) {
        Optional<Member> result = memberRepository.findByLoginid(member.getLoginid());
        if (result.isPresent()) throw new ExistedEmailException("이미 존재하는 이메일입니다");
        memberRepository.save(member);
    }

    public List<Member> getAllMember() {
        return memberRepository.findAll();
    }
    public Member findMemberByName(String name) {
        return memberRepository.findByName(name).orElseThrow(()->new NoMemberException("해당 회원이 없습니다"));
    }
    public Member findMemberByNickName(String nickname) {
        return memberRepository.findByNickname(nickname).orElseThrow(()->new NoMemberException("해당 회원이 없습니다"));
    }
    public Member findMemberById(Long id) {
        return memberRepository.findById(id).orElseThrow(()->new NoMemberException("해당 회원이 없습니다"));
    }
    @Transactional
    public void updateMemberInfo(Long id, CreateMemberDto createMemberDto) {
        Member member=memberRepository.findById(id).orElseThrow(()-> new NoMemberException("해당 회원이 없습니다"));
        member.setName(createMemberDto.getName());
        member.setLogin_id(createMemberDto.getEmail());
        member.setPassword(createMemberDto.getPassword());
        member.setNickname(createMemberDto.getNickname());
    }
    //@Transactional
    //public void updatePassword(Long id,)


}
