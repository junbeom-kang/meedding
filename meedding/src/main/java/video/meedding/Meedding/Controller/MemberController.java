package video.meedding.Meedding.Controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import video.meedding.Meedding.config.auth.PrincipalDetails;
import video.meedding.Meedding.domain.Member;
import video.meedding.Meedding.dto.CreateMemberDto;
import video.meedding.Meedding.dto.ResponseMemberDto;
import video.meedding.Meedding.dto.UpdateMemberDto;
import video.meedding.Meedding.dto.response.Result;
import video.meedding.Meedding.service.MemberService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/v1/user")
    public String user(Authentication authentication) {
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        System.out.println("principal : "+principal.getMember().getId());
        System.out.println("principal : "+principal.getMember().getLoginid());
        System.out.println("principal : "+principal.getMember().getPassword());

        return "<h1>user</h1>";
    }
    @GetMapping("/members")
    public Result getMemberList() {
        List<Member> allMember = memberService.getAllMember();
        List<ResponseMemberDto> memberList = allMember.stream()
                .map(m -> new ResponseMemberDto(m.getLoginid(), m.getName()))
                .collect(Collectors.toList());
        return new Result(memberList);
    }

    @PostMapping("/members")
    public Result memberJoin(@RequestBody CreateMemberDto createMemberDto) {
        System.out.println("들어왔읍");
        memberService.join(createMemberDto);
        return new Result<Integer>(HttpStatus.OK.value());
    }


    @GetMapping("/members/{id}")
    public Result getIdMember(@PathVariable Long id) {
        Member memberById = memberService.findMemberById(id);
        return new Result(memberById);
    }
/*
    @PostMapping("members/update")
    public Result updateMember(@RequestBody UpdateMemberDto updateMemberDto) {
     SessionUser user=(SessionUser)httpSession.getAttribute("user");
     memberService.updateMemberInfo(user.getId(),updateMemberDto);
        return new Result<Integer>(HttpStatus.OK.value());
    }
 */

}
