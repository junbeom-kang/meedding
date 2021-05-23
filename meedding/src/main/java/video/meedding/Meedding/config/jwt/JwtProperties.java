package video.meedding.Meedding.config.jwt;

public interface JwtProperties {
    String SECRET = "조익현"; // 우리 서버만 알고 있는 비밀값
    int EXPIRATION_TIME = 6000000; // 10일 (1/1000초)
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}