package video.meedding.Meedding;

import io.openvidu.java.client.OpenVidu;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenViduConfig {
    @Bean
    public OpenVidu openVidu(@Value("${openvidu.secret}") String secret, @Value("${openvidu.url}") String url){
        return new OpenVidu(url, secret);
    }
}
