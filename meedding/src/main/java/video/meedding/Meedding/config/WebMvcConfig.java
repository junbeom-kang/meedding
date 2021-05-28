package video.meedding.Meedding.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://meedding.kro.kr")
                .allowedOrigins("http://meedding.kro.kr")
                .allowedOrigins("http://localhost:3000")
                .allowedOrigins("http://meedding.kro.kr:3000")
                .allowCredentials(true)
                .allowedMethods("*")
                .allowedHeaders("*");
    }
}
