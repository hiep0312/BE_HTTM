package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class TtsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TtsApplication.class, args);

		System.out.println();
		System.out.println("Present Project Directory : "+ System.getProperty("user.dir"));
	}

}
