package tn.esprit.vbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;

@SpringBootApplication()
@EnableAutoConfiguration(exclude = { FreeMarkerAutoConfiguration.class })
public class VirtualBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(VirtualBankApplication.class, args);
	}

}
