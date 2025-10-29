package in.nexa.operationalexecutive.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class OperationalexecutiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(OperationalexecutiveApplication.class, args);
	}
	
	@Bean
	public RestTemplate rt() {
		
		return new RestTemplate();
	}

}
