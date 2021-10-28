package video.meedding.Meedding.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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
    @ApiOperation(value = "로그인 요청", notes = "로그인 성공시 jwt토큰을 return 한다.")
    @PostMapping("/login")
    public Result signIn(@RequestBody LoginRequestDto loginRequestDto) {
        Member member = memberService.findMemberByLoginId(loginRequestDto.getUsername());
        if (!bCryptPasswordEncoder.matches(loginRequestDto.getPassword(), member.getPassword()))
            throw new PasswordDiffException();

        return responseService.getSingleResult(jwtTokenProvider.createToken(String.valueOf(member.getId()), member.getRole()));

    }
    @ApiOperation(value = "모든 회원 조회", notes = "모든 회원을 조회한다")
    @ApiImplicitParam(name = "Authorization", value = "access-token", required = true, dataType = "String", paramType = "header")
    @GetMapping("/members")
    public Result getMemberList() {
        List<Member> allMember = memberService.getAllMember();
        List<ResponseMemberDto> memberList = allMember.stream()
                .map(ResponseMemberDto::convertUserToDto)
                .collect(Collectors.toList());
        return responseService.getListResult(memberList);
    }
    @ApiOperation(value = "회원가입 요청", notes = "회원가입을 요청한다.")
    @PostMapping("/members")
    public Result memberJoin(@RequestBody CreateMemberDto createMemberDto) {
        memberService.join(createMemberDto);
        return responseService.getSuccessResult();
    }
    @ApiOperation(value = "pk로 회원 조회", notes = "모든 회원을 조회한다")
    @ApiImplicitParam(name = "Authorization", value = "access-token", required = true, dataType = "String", paramType = "header")
    @GetMapping("/members/{member_id}")
    public Result getIdMember(@PathVariable Long member_id) {
        Member m = memberService.findMemberById(member_id);
        return responseService.getSingleResult(ResponseMemberDto.convertUserToDto(m));
    }
    @ApiOperation(value = "회원 정보 업데이트", notes = "회원의 개인정보를 업데이트한다")
    @ApiImplicitParam(name = "Authorization", value = "access-token", required = true, dataType = "String", paramType = "header")
    @PostMapping("/members/update")
    public Result updateMember(@RequestBody UpdateMemberDto updateMemberDto, @AuthenticationPrincipal PrincipalDetails principal) {
        memberService.updateMemberInfo(principal.getMember().getId(), updateMemberDto);
        return responseService.getSuccessResult();
    }
    @ApiOperation(value = "이름으로 회원 조회", notes = "검색 이름을 가진 회원을 리스트로 반환한다")
    @ApiImplicitParam(name = "Authorization", value = "access-token", required = true, dataType = "String", paramType = "header")
    @GetMapping("/members/name/{name}")
    public Result readUserByName(@PathVariable String name) {
        List<Member> memberByName = memberService.findMemberByName(name);
        List<ResponseMemberDto> memberList = memberByName.stream()
                .map(ResponseMemberDto::convertUserToDto)
                .collect(Collectors.toList());
        return responseService.getListResult(memberList);
    }
    @ApiOperation(value = "닉네임으로 회원 회원 조회", notes = "검색 닉네임의 회원으로 조회한다")
    @ApiImplicitParam(name = "Authorization", value = "access-token", required = true, dataType = "String", paramType = "header")
    @GetMapping("/members/nickname/{nickname}")
    public Result readUserByNickname(@PathVariable String nickname) {
        return responseService.getSingleResult(ResponseMemberDto.convertUserToDto(memberService.findMemberByNickName(nickname)));
    }
    @ApiOperation(value = "이메일로 회원 회원 조회", notes = "검색 이메일로 회원으로 조회한다")
    @ApiImplicitParam(name = "Authorization", value = "access-token", required = true, dataType = "String", paramType = "header")
    @GetMapping("/members/email/{email}")
    public Result readUserByLogin_id(@PathVariable String email) {
        return responseService.getSingleResult(ResponseMemberDto.convertUserToDto(memberService.findMemberByLoginId(email)));
    }
    @ApiOperation(value = "내 정보 조회", notes = "내 회원 정보를 조회한다")
    @ApiImplicitParam(name = "Authorization", value = "access-token", required = true, dataType = "String", paramType = "header")
    @GetMapping("/members/me")
    public Result getMyInfo(@AuthenticationPrincipal PrincipalDetails principal) {
        return responseService.getSingleResult(memberService.getMyInfo(principal.getMember().getId()));
    }
    @ApiOperation(value = "비밀번호 변경", notes = "비밀번호를 변경한다")
    @ApiImplicitParam(name = "Authorization", value = "access-token", required = true, dataType = "String", paramType = "header")
    @PostMapping("/members/changePassword")
    public Result changePW(@AuthenticationPrincipal PrincipalDetails principal, @RequestBody ChangePasswordDto changePasswordDto) {
        memberService.updatePassword(principal.getMember().getId(), changePasswordDto);
        return responseService.getSuccessResult();
    }
    @ApiOperation(value = "회원 삭제", notes = "본인일 경우 확인 후 삭제한다")
    @ApiImplicitParam(name = "Authorization", value = "access-token", required = true, dataType = "String", paramType = "header")
    @DeleteMapping("members/delete")
    public Result deleteMember(@AuthenticationPrincipal PrincipalDetails principalDetails,@RequestParam("password")String password) {
        memberService.deleteMember(principalDetails.getMember().getId(),password);
        return responseService.getSuccessResult();
    }
    @ApiOperation(value = "프로필 s3를 통한 업로드", notes = "프로필을 s3 bucket에 업로드하고 저장한다")
    @ApiImplicitParam(name = "Authorization", value = "access-token", required = true, dataType = "String", paramType = "header")
    @PostMapping("members/profile")
    public Result updateProfile(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestParam("image")MultipartFile multipartFile) throws IOException {
        String url = s3Uploader.upload(multipartFile, "profile");
        memberService.updateProfile(url,principalDetails.getMember().getId());
        return responseService.getSuccessResult();
    }
}
