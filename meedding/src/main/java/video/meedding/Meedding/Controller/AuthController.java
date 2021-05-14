package video.meedding.Meedding.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
public class AuthController {
    @Value("${domain}")
    private String domain;
    @Value("${kakao.clientId}")
    private String clientId;
    @Value("${kakao.redirectUri}")
    private String redirectUri;

    //반환타입교체필요
    @GetMapping("/auth/kakao/callback")
    public @ResponseBody ResponseEntity kakaoTokenCallBack(String code) {
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers=new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("redirect_uri", domain + redirectUri);
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token", HttpMethod.POST, request, String.class);

        return response;
    }
}
