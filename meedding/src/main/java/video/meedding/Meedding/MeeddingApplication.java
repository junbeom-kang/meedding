package video.meedding.Meedding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MeeddingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeeddingApplication.class, args);
	}

}
