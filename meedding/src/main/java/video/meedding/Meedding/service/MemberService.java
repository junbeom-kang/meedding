package video.meedding.Meedding.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import video.meedding.Meedding.domain.Member;
import video.meedding.Meedding.dto.CreateMemberDto;
import video.meedding.Meedding.exception.NoMemberException;
import video.meedding.Meedding.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    public void join(Member member) {
        memberRepository.save(member);
    }

    public List<Member> getAllMember() {
        return memberRepository.findAll();
    }

    public Optional<Member> findMemberById(Long id) {
        return memberRepository.findById(id);
    }

    public void updateMemberInfo(Long id, CreateMemberDto createMemberDto) {

        Member member=memberRepository.findById(id).orElseThrow(()-> new NoMemberException("해당 회원이 없습니다"));
        member.setName(createMemberDto.getName());
        member.setLogin_id(createMemberDto.getEmail());
        member.setPassword(createMemberDto.getPassword());
        member.setNickname(createMemberDto.getNickname());
    }


}
