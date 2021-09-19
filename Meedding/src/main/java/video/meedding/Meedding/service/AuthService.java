package video.meedding.Meedding.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import video.meedding.Meedding.config.jwt.JwtProperties;
import video.meedding.Meedding.domain.Member;
import video.meedding.Meedding.dto.CreateMemberDto;
import video.meedding.Meedding.dto.KakaoProfile;
import video.meedding.Meedding.dto.LoginRequestDto;
import video.meedding.Meedding.exception.KakaoCommunicationFailureException;
import video.meedding.Meedding.exception.NoMemberException;
import video.meedding.Meedding.model.OAuthToken;
import video.meedding.Meedding.repository.MemberRepository;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final Gson gson;
    @Value("${domain}")
    private String domain;
    @Value("${kakao.clientId}")
    private String clientId;
    @Value("${cosKey}")
    private String cosKey;
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

    public String getProfile(OAuthToken oauthToken) {
        RestTemplate rt2 = new RestTemplate();
        // HttpHeader 오브젝트 생성
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization", "Bearer "+oauthToken.getAccess_token());
        headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 =
                new HttpEntity<>(headers2);

        // Http 요청하기 - Post방식으로 - 그리고 response 변수의 응답 받음.
        ResponseEntity<String> response2 = rt2.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest2,
                String.class
        );

        KakaoProfile kakaoProfile = null;
        if (response2.getStatusCode() == HttpStatus.OK) {
            kakaoProfile = gson.fromJson(response2.getBody(), KakaoProfile.class);
        } else {
            throw new KakaoCommunicationFailureException();
        }
        System.out.println(kakaoProfile.getKakao_account().getEmail());
        Long id=null;
        Optional<Member> byLoginid = memberRepository.findByLoginid(kakaoProfile.getKakao_account().getEmail());
        if (byLoginid.isEmpty()) {
            id = memberService.join(new CreateMemberDto(kakaoProfile.getKakao_account().getEmail(), cosKey, cosKey,
                    kakaoProfile.getProperties().getNickname(), "Kakao_" + kakaoProfile.getProperties().getNickname()));
            memberRepository.findById(id).orElseThrow(NoMemberException::new).setImage(kakaoProfile.getProperties().getProfile_image());
        } else {
            id=byLoginid.get().getId();
        }
        System.out.println("자동 로그인을 진행합니다.");
        /*
        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity("localhost:8080/login", new LoginRequestDto(kakaoProfile.getKakao_account().getEmail(), cosKey),String.class);
        System.out.println(stringResponseEntity);

         */

        // 로그인 처리

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoProfile.kakao_account.getEmail(), cosKey));
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        String jwtToken = JWT.create()
                .withSubject(kakaoProfile.getKakao_account().getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis()+ JwtProperties.EXPIRATION_TIME))
                .withClaim("id", id)
                .withClaim("name", kakaoProfile.properties.getNickname())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));

        return JwtProperties.TOKEN_PREFIX+jwtToken;



    }
}
