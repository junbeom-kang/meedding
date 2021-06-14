package video.meedding.Meedding.Controller;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import video.meedding.Meedding.config.auth.PrincipalDetails;
import video.meedding.Meedding.domain.Member;
import video.meedding.Meedding.dto.response.Result;
import video.meedding.Meedding.service.MemberService;
import video.meedding.Meedding.service.ResponseService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TestController {
    private final MemberService memberService;
    private final ResponseService responseService;

    @GetMapping("/home")
    public String home(@AuthenticationPrincipal PrincipalDetails principal) {

        return principal.getMember().getNickname();
    }
    /*
컬렉션조회 최적화 연습
 */
    @GetMapping("/members/all")
    public Result TEST() {
        List<Member> allMember = memberService.getAllMember();
        return responseService.getSingleResult(allMember);
    }
}
