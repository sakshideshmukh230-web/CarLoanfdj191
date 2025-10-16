package in.nexa.cibilcalculator.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CibilcalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CibilcalculatorApplication.class, args);
	}

}
