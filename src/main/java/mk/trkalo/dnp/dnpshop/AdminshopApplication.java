package mk.trkalo.dnp.dnpshop;

import mk.trkalo.dnp.dnpshop.model.user.LoggedUser;
import mk.trkalo.dnp.dnpshop.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;;


@SpringBootApplication
@ServletComponentScan
public class AdminshopApplication {

	@Bean
	public CommandLineRunner setupDefaultUser(UserService service) {
		LoggedUser user = LoggedUser.createNewAdmin(
				"Martin Bojmaliev",
				"mbojmaliev@gmail.com",
				"maneken45874587"
		);
		LoggedUser riki = LoggedUser.createNewAdmin("Riste Stojanov", "riste.stojanov@finki.ukim.mk", "ristes123");

		return args -> {
			service.save(user);
			service.save(riki);
		};
	}
	public static void main(String[] args) {
		SpringApplication.run(AdminshopApplication.class, args);
	}


}


