package video.meedding.Meedding.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import video.meedding.Meedding.dto.response.Result;
import video.meedding.Meedding.model.OAuthToken;
import video.meedding.Meedding.service.AuthService;
import video.meedding.Meedding.service.ResponseService;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final ResponseService responseService;

    //반환타입교체필요
    @GetMapping("api/auth/kakao/callback")
    public Result kakaoTokenCallBack(String code) {
        OAuthToken token = authService.getTokenInfo(code);
        System.out.println("절반성공");
        String jwt = authService.getProfile(token);
        return responseService.getSingleResult(jwt);

    }
}

