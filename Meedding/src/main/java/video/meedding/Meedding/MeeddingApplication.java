package video.meedding.Meedding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MeeddingApplication {

	public static void main(String[] args) {
		System.out.println("배포 버전확인 21-12-14");
		SpringApplication.run(MeeddingApplication.class, args);
	}

}
