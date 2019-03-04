package mk.trkalo.dnp.dnpshop;

import mk.trkalo.dnp.dnpshop.model.Size;
import mk.trkalo.dnp.dnpshop.model.Type;
import mk.trkalo.dnp.dnpshop.service.SizeService;
import mk.trkalo.dnp.dnpshop.service.TypeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

import java.io.Console;

@SpringBootApplication
@ServletComponentScan
public class AdminshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminshopApplication.class, args);


	}

}


