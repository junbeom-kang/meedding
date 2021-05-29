package video.meedding.Meedding.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import video.meedding.Meedding.domain.Member;
import video.meedding.Meedding.dto.ChangePasswordDto;
import video.meedding.Meedding.dto.CreateMemberDto;
import video.meedding.Meedding.dto.UpdateMemberDto;
import video.meedding.Meedding.exception.*;
import video.meedding.Meedding.repository.MemberRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MemberRepository memberRepository;
    @Transactional
    public Long join(CreateMemberDto createMemberDto) {
        if (memberRepository.findByLoginid(createMemberDto.getEmail()).isPresent()) {
            throw new ExistedEmailException("이미 존재하는 이메일입니다");
        }
        if (memberRepository.findByNickname(createMemberDto.getNickname()).isPresent()){
            throw new ExistedNickNameException("이미 존재하는 닉네임입니다");
        }
        if (!createMemberDto.getPassword().equals(createMemberDto.getCheckPassword())) {
            throw new PasswordDiffException("확인 패스워드가 일치하지 않습니다");
        }
        createMemberDto.setPassword(bCryptPasswordEncoder.encode(createMemberDto.getPassword()));
        Member save = memberRepository.save(Member.createMember(createMemberDto.getName(), createMemberDto.getNickname(), createMemberDto.getEmail(), createMemberDto.getPassword()));
        return save.getId();
    }

    public List<Member> getAllMember() {
        return memberRepository.findAll();
    }
    public List<Member> findMemberByName(String name) {
        return memberRepository.findByName(name);
    }
    public Member findMemberByNickName(String nickname) {
        return memberRepository.findByNickname(nickname).orElseThrow(()->new NoMemberException("해당 회원이 없습니다"));
    }
    public Member findMemberById(Long id) {
        return memberRepository.findById(id).orElseThrow(()->new NoMemberException("해당 회원이 없습니다"));
    }
    public Member findMemberByLoginId(String Login_id) {
        return memberRepository.findByLoginid(Login_id).orElseThrow(() -> new NoMemberException("해당 회원이 없습니다"));
    }
    @Transactional
    public void updateMemberInfo(Long id, UpdateMemberDto updateMemberDto) {
        Member member=memberRepository.getOne(id);
        if (!member.getNickname().equals(updateMemberDto.getNickname())) {
            if (memberRepository.findByNickname(updateMemberDto.getNickname()).isEmpty()) {
                member.setNickname(updateMemberDto.getNickname());
            } else {
                throw new ExistedNickNameException("존재하는 닉네임입니다");
            }
        }
        member.setName(updateMemberDto.getName());
    }

    @Transactional
    public void updatePassword(Long id, ChangePasswordDto changePasswordDto) {
        Member member = memberRepository.findById(id).orElseThrow(()->new NoMemberException("해당 회원이 없습니다"));

        if (!bCryptPasswordEncoder.matches(changePasswordDto.getOldPassword(), member.getPassword())) {
            throw new PasswordDiffException("기존 패스워드가 일치하지 않습니다");
        } else if (bCryptPasswordEncoder.matches(changePasswordDto.getNewPassword(), member.getPassword())) {
            throw new SamePasswordException("패스워드가 이전 패스워드와 동일합니다");
        }
        member.setPassword(bCryptPasswordEncoder.encode(changePasswordDto.getNewPassword()));
    }


}
