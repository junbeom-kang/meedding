package video.meedding.Meedding.Controller.Exception;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import video.meedding.Meedding.exception.AccessDeniedException;
import video.meedding.Meedding.exception.AuthenticationEntryPointException;

@RestController
@RequestMapping(value = "/exception")
public class ExceptionController {
    @GetMapping(value = "/entrypoint")
    public void entrypointException() {
        throw new AuthenticationEntryPointException();
    }

    @GetMapping(value = "/accessdenied")
    public void accessDeniedException() {
        throw new AccessDeniedException();
    }

}