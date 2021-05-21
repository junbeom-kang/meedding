package video.meedding.Meedding.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/home")
    public String home() {
        return "<h1>home</h1>";
    }
}
