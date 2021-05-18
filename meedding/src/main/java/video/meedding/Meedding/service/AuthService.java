package video.meedding.Meedding.service;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import video.meedding.Meedding.dto.KakaoCommunicationFailureException;
import video.meedding.Meedding.model.OAuthToken;
/*
@Service
@RequiredArgsConstructor
public class AuthService {
    private final Gson gson;
    @Value("${domain}")
    private String domain;
    @Value("${kakao.clientId}")
    private String clientId;
    @Value("${kakao.redirectUri}")
    private String redirectUri;
    //반환타입교체필요
    public OAuthToken getTokenInfo(String code) {
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers=new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("redirect_uri", redirectUri);
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token", HttpMethod.POST, request, String.class);;
        if(response.getStatusCode() == HttpStatus.OK) {
            return gson.fromJson(response.getBody(), OAuthToken.class);
        }
        throw new KakaoCommunicationFailureException();

    }
}


 */