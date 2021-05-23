package video.meedding.Meedding.Controller;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import video.meedding.Meedding.config.auth.PrincipalDetails;

@RestController
public class TestController {
    @GetMapping("/home")
    public String home(@AuthenticationPrincipal PrincipalDetails principal) {

        return principal.getMember().getNickname();
    }
}
