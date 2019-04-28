package mk.trkalo.dnp.dnpshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@SpringBootApplication
@ServletComponentScan
public class AdminshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminshopApplication.class, args);


	}

}


