package video.meedding.Meedding.Controller;
import io.openvidu.java.client.OpenVidu;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import video.meedding.Meedding.config.auth.PrincipalDetails;
import video.meedding.Meedding.domain.Member;
import video.meedding.Meedding.dto.response.Result;
import video.meedding.Meedding.service.MemberService;
import video.meedding.Meedding.service.ResponseService;
import video.meedding.Meedding.service.RoomService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TestController {
    private final MemberService memberService;
    private final ResponseService responseService;
    private final RoomService roomService;
    private final OpenVidu openVidu;
    private final ApplicationContext applicationContext;

    @GetMapping("/home")
    public String home() {
        Object memberService1 = applicationContext.getBean("memberService");
        System.out.println(memberService1.getClass());
        System.out.println(this.memberService.getClass());
        return "hello";
    }
    /*
컬렉션조회 최적화 연습
 */
    @GetMapping("/members/all")
    public Result TEST() {
        List<Member> allMember = memberService.getAllMember();
        return responseService.getSingleResult(allMember);
    }

    @GetMapping("/test/session")
    public Result TEST1() {
        System.out.println(openVidu.getActiveSessions());
        return responseService.getSuccessResult();
    }
    /*
    AuthenticationPrincipalArgumentResolver 동작원리 스터디
     */

    @GetMapping("/test/security")
    public Result TEST2(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        System.out.println(SecurityContextHolder.getContext());
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("PrincipalDetails");
        System.out.println(expression.getExpressionString());
        System.out.println(principalDetails.getUsername());
        return responseService.getSuccessResult();
    }
    @GetMapping("/test/security1")
    public Result TEST2() {
        System.out.println(SecurityContextHolder.getContext());
        return responseService.getSuccessResult();
    }


}
