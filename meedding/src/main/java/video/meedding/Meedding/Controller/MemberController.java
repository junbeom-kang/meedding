package video.meedding.Meedding.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import video.meedding.Meedding.config.auth.PrincipalDetails;
import video.meedding.Meedding.domain.Member;
import video.meedding.Meedding.dto.*;
import video.meedding.Meedding.dto.response.Result;
import video.meedding.Meedding.service.MemberService;
import video.meedding.Meedding.service.ResponseService;

import java.util.List;
import java.util.stream.Collectors;
@Api(tags="1. Member")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final ResponseService responseService;
    @ApiOperation(value="모든 회원 조회",notes ="모든 회원을 조회한다")
    @GetMapping("/members")
    public Result getMemberList() {

        List<Member> allMember = memberService.getAllMember();
        List<ResponseMemberDto> memberList = allMember.stream()
                .map(ResponseMemberDto::convertUserToDto)
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
        return responseService.getSingleResult(ResponseMemberDto.convertUserToDto(m));
    }

    @PostMapping("/members/update")
    public Result updateMember(@RequestBody UpdateMemberDto updateMemberDto, @AuthenticationPrincipal PrincipalDetails principal) {
        memberService.updateMemberInfo(principal.getMember().getId(), updateMemberDto);
        return responseService.getSuccessResult();
    }

    @GetMapping("/members/name/{name}")
    public Result readUserByName(@PathVariable String name) {
        List<Member> memberByName = memberService.findMemberByName(name);
        List<ResponseMemberDto> memberList = memberByName.stream()
                .map(ResponseMemberDto::convertUserToDto)
                .collect(Collectors.toList());
        return responseService.getListResult(memberList);
    }

    @GetMapping("/members/nickname/{nickname}")
    public Result readUserByNickname(@PathVariable String nickname) {
        return responseService.getSingleResult(ResponseMemberDto.convertUserToDto(memberService.findMemberByNickName(nickname)));
    }

    @GetMapping("/members/email/{email}")
    public Result readUserByLogin_id(@PathVariable String email) {
        return responseService.getSingleResult(ResponseMemberDto.convertUserToDto(memberService.findMemberByLoginId(email)));
    }

    @GetMapping("/members/me")
    public Result getMyInfo(@AuthenticationPrincipal PrincipalDetails principal) {
        Member m = principal.getMember();//트랜잭션이 따라서 LAZY에서 초기화도 못함
        memberService.findMemberById(9L);
        memberService.findMemberByLoginId("kgood1@mju.ac.kr");
        ResponseMyInfo responseMyInfo = new ResponseMyInfo(m.getLoginid(), m.getName(), m.getNickname(), m.getSignUpDate());
        return responseService.getSingleResult(responseMyInfo);
    }

    @PostMapping("/members/changePassword")
    public Result changePW(@AuthenticationPrincipal PrincipalDetails principal, @RequestBody ChangePasswordDto changePasswordDto) {
        memberService.updatePassword(principal.getMember().getId(),changePasswordDto);
        return responseService.getSuccessResult();
    }

}
