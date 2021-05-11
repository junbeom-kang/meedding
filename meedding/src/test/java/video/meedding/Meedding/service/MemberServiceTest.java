package video.meedding.Meedding.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import video.meedding.Meedding.domain.Member;
import video.meedding.Meedding.dto.ChangePasswordDto;
import video.meedding.Meedding.dto.CreateMemberDto;
import video.meedding.Meedding.dto.UpdateMemberDto;
import video.meedding.Meedding.exception.ExistedEmailException;
import video.meedding.Meedding.exception.ExistedNickNameException;
import video.meedding.Meedding.exception.NoMemberException;
import video.meedding.Meedding.exception.PasswordDiffException;
import video.meedding.Meedding.repository.MemberRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired MemberRepository memberRepository;
    @Autowired MemberService memberService;

    @BeforeEach
    void before() {
        Member existedMember = Member.createMember("sampleName", "sampleNickName", "sample@naver.com", "samplePw");
        memberRepository.save(existedMember);
    }


    @Test
    public void 회원가입_테스트() throws Exception {
        //given
        CreateMemberDto createMemberDto = new CreateMemberDto(
                "k@n.com", "qwer", "qwer", "준범", "abcd");
        //when
        Long id=memberService.join(createMemberDto);
        //then
        assertThat(memberRepository.getOne(id).getLoginid()).isEqualTo("k@n.com");

    }
    @Test
    public void 중복_아이디_중복_닉네임_테스트() throws Exception {
        //given
        CreateMemberDto duplicateId = new CreateMemberDto(
                "sample@naver.com", "qwer", "qwer", "준범", "abcd");
        CreateMemberDto duplicateNickName = new CreateMemberDto(
                "new@naver.com", "qwer", "qwer", "준범", "sampleNickName");
        //when
        ExistedEmailException e = assertThrows(ExistedEmailException.class, () -> memberService.join(duplicateId));
        ExistedNickNameException e1=assertThrows(ExistedNickNameException.class,()->memberService.join(duplicateNickName));

        //then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 이메일입니다");
        assertThat(e1.getMessage()).isEqualTo("이미 존재하는 닉네임입니다");
    }
    @Test
    public void 확인비밀번호_오류_테스트() throws Exception {
        //given
        CreateMemberDto createMemberDto = new CreateMemberDto(
                "sample1@naver.com", "qwer", "qwer1", "준범", "abcd");

        //when
        PasswordDiffException e=assertThrows(PasswordDiffException.class,()->memberService.join(createMemberDto));

        //then
        assertThat(e.getMessage()).isEqualTo("확인 패스워드가 일치하지 않습니다");

    }
    @Test
    public void 조회_테스트() throws Exception {
        //given
        CreateMemberDto createMemberDto = new CreateMemberDto(
                "sample1@naver.com", "qwer", "qwer", "준범", "abcd");

        //when
        Long memberId = memberService.join(createMemberDto);
        List<Member> allMember = memberService.getAllMember();
        Member findByNameMember = memberService.findMemberByName("준범");
        Member findByNickNameMember = memberService.findMemberByNickName("abcd");
        Member findMemberById = memberService.findMemberById(memberId);
        NoMemberException e = assertThrows(NoMemberException.class,()->memberService.findMemberById(9999L));

        //then
        assertThat(allMember.size()).isEqualTo(2);
        assertThat(findByNameMember.getId()).isEqualTo(findByNickNameMember.getId()).isEqualTo(memberId);
        assertThat(findMemberById.getId()).isEqualTo(memberId);
        assertThat(e.getMessage()).isEqualTo("해당 회원이 없습니다");

    }
    @Test
    public void 회원정보_업데이트_테스트() throws Exception {
        //given
        CreateMemberDto createMemberDto = new CreateMemberDto(
                "sample1@naver.com", "qwer", "qwer", "Name", "nickname");
        Long memberId = memberService.join(createMemberDto);
        UpdateMemberDto updateMemberDto = new UpdateMemberDto("변경된Name","변경된 nickname");
        //when
        memberService.updateMemberInfo(memberId,updateMemberDto);

        //then
        Member member=memberRepository.getOne(memberId);
        assertThat(member.getName()).isEqualTo("변경된Name");
        assertThat(member.getNickname()).isEqualTo("변경된 nickname");
    }
    @Test
    public void 정보업데이트시_닉네임_중복에러() throws Exception {
        //given
        CreateMemberDto createMemberDto = new CreateMemberDto(
                "sample1@naver.com", "qwer", "qwer", "Name", "nickname");
        Long memberId = memberService.join(createMemberDto);
        UpdateMemberDto updateMemberDto = new UpdateMemberDto("변경된Name","sampleNickName");
        //when
        ExistedNickNameException e = assertThrows(ExistedNickNameException.class, () -> memberService.updateMemberInfo(memberId, updateMemberDto));

        //then
        assertThat(e.getMessage()).isEqualTo("존재하는 닉네임입니다");
    }
    @Test
    public void 패스워드_변경_테스트() throws Exception {
        //given
        CreateMemberDto createMemberDto = new CreateMemberDto(
                "sample1@naver.com", "qwer", "qwer", "Name", "nickname");
        Long memberId = memberService.join(createMemberDto);
        ChangePasswordDto changePasswordDto=new ChangePasswordDto("qwer","qwert");
        //when
        memberService.updatePassword(memberId,changePasswordDto);

        //then
        assertThat(memberService.findMemberById(memberId).getPassword()).isEqualTo("qwert");

    }
    @Test
    public void 기존비밀번호검증오류_동일비밀번호오류() throws Exception {
        //given
        CreateMemberDto createMemberDto = new CreateMemberDto(
                "sample1@naver.com", "qwer", "qwer", "Name", "nickname");
        Long memberId = memberService.join(createMemberDto);
        ChangePasswordDto passwordErrorDto=new ChangePasswordDto("qwert","qwer");
        ChangePasswordDto noChangeErrorDto=new ChangePasswordDto("qwer","qwer");

        //when
        Exception e=assertThrows(Exception.class,()->memberService.updatePassword(memberId,passwordErrorDto));
        Exception e1=assertThrows(Exception.class,()->memberService.updatePassword(memberId,noChangeErrorDto));

        //then
        assertThat(e.getMessage()).isEqualTo("기존 패스워드가 일치하지 않습니다");
        assertThat(e1.getMessage()).isEqualTo("패스워드가 이전 패스워드와 동일합니다");


    }

}