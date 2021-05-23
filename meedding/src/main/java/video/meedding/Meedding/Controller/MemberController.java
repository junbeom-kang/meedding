package video.meedding.Meedding.Controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import video.meedding.Meedding.config.auth.PrincipalDetails;
import video.meedding.Meedding.domain.Member;
import video.meedding.Meedding.dto.CreateMemberDto;
import video.meedding.Meedding.dto.ResponseMemberDto;
import video.meedding.Meedding.dto.ResponseMyInfo;
import video.meedding.Meedding.dto.UpdateMemberDto;
import video.meedding.Meedding.dto.response.Result;
import video.meedding.Meedding.service.MemberService;
import video.meedding.Meedding.service.ResponseService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final ResponseService responseService;

    @GetMapping("/members")
    public Result getMemberList() {

        List<Member> allMember = memberService.getAllMember();
        List<ResponseMemberDto> memberList = allMember.stream()
                .map(m -> new ResponseMemberDto(m.getLoginid(), m.getName()))
                .collect(Collectors.toList());
        return responseService.getListResult(memberList);
    }

    @PostMapping("/members")
    public Result memberJoin(@RequestBody CreateMemberDto createMemberDto) {
        memberService.join(createMemberDto);
        return responseService.getSuccessResult();
    }


    @GetMapping("/members/{id}")
    public Result getIdMember(@PathVariable Long id) {
        Member m = memberService.findMemberById(id);
        return responseService.getSingleResult(new ResponseMemberDto(m.getLoginid(),m.getName()));
    }

    @PostMapping("members/update")
    public Result updateMember(@RequestBody UpdateMemberDto updateMemberDto,@AuthenticationPrincipal PrincipalDetails principal) {
     memberService.updateMemberInfo(principal.getMember().getId(),updateMemberDto);
        return responseService.getSuccessResult();
    }

    @GetMapping("/members/me")
    public Result getMyInfo(@AuthenticationPrincipal PrincipalDetails principal) {
        Member m=principal.getMember();
        ResponseMyInfo responseMyInfo=new ResponseMyInfo(m.getLoginid(),m.getName(),m.getNickname(),m.getSignUpDate());
        return responseService.getSingleResult(responseMyInfo);
    }


}
