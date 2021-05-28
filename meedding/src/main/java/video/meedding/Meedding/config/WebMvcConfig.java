package video.meedding.Meedding.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        System.out.println("수정됨1034");
        registry.addMapping("/**")
                .allowedOrigins("/meedding.kro.kr,/localhost:3000,/localhost:8080,'*'")
                .allowCredentials(true)
                .allowedMethods("*")
                .allowedHeaders("*");
    }
}
