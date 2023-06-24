package jejemint.akkijang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class AkkijangApplication {

	public static void main(String[] args) {
		SpringApplication.run(AkkijangApplication.class, args);
	}

}
