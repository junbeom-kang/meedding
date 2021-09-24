package video.meedding.Meedding.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import video.meedding.Meedding.config.auth.PrincipalDetails;
import video.meedding.Meedding.config.jwt.JwtTokenProvider;
import video.meedding.Meedding.domain.Member;
import video.meedding.Meedding.dto.*;
import video.meedding.Meedding.dto.response.Result;
import video.meedding.Meedding.exception.PasswordDiffException;
import video.meedding.Meedding.service.MemberService;
import video.meedding.Meedding.service.ResponseService;
import video.meedding.Meedding.service.S3Uploader;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "1. Member")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final ResponseService responseService;
    private final S3Uploader s3Uploader;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public Result signIn(@RequestBody LoginRequestDto loginRequestDto) {
        Member member = memberService.findMemberByLoginId(loginRequestDto.getUsername());
        if (!bCryptPasswordEncoder.matches(loginRequestDto.getPassword(), member.getPassword()))
            throw new PasswordDiffException();

        return responseService.getSingleResult(jwtTokenProvider.createToken(String.valueOf(member.getId()), member.getRole()));

    }

    @ApiOperation(value = "모든 회원 조회", notes = "모든 회원을 조회한다")
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
        Member m = principal.getMember();
        ResponseMyInfo responseMyInfo = new ResponseMyInfo(m.getLoginid(),m.getImage(), m.getName(), m.getNickname(), m.getSignUpDate());
        return responseService.getSingleResult(responseMyInfo);
    }

    @PostMapping("/members/changePassword")
    public Result changePW(@AuthenticationPrincipal PrincipalDetails principal, @RequestBody ChangePasswordDto changePasswordDto) {
        memberService.updatePassword(principal.getMember().getId(), changePasswordDto);
        return responseService.getSuccessResult();
    }

    @DeleteMapping("members/delete")
    public Result deleteMember(@AuthenticationPrincipal PrincipalDetails principalDetails,@RequestParam("password")String password) {
        memberService.deleteMember(principalDetails.getMember().getId(),password);
        return responseService.getSuccessResult();
    }
    @PostMapping("members/profile")
    public Result updateProfile(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestParam("image")MultipartFile multipartFile) throws IOException {
        String url = s3Uploader.upload(multipartFile, "profile");
        memberService.updateProfile(url,principalDetails.getMember().getId());
        return responseService.getSuccessResult();
    }
}
