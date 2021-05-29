package video.meedding.Meedding.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        System.out.println("수정됨0406");
        registry.addMapping("/**")
                .allowedOrigins("https://meedding.kro.kr")
                .allowCredentials(true)
                .allowedMethods("*")
                .allowedHeaders("*");
    }
}
