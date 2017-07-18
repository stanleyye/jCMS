package jcms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("jcms.repository")
public class JCmsApplication {
	public static void main(String[] args) {
		SpringApplication.run(JCmsApplication.class, args);
	}
}
