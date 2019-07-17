package mk.trkalo.dnp.dnpshop.web.rest;

import mk.trkalo.dnp.dnpshop.service.LoggedUserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/me", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoggedController {

    private final LoggedUserService loggedUserService;

    public LoggedController(LoggedUserService loggedUserService) {
        this.loggedUserService = loggedUserService;
    }

    @GetMapping
    public ResponseEntity<?> getDetails(){
        return ResponseEntity.ok(loggedUserService.getMyDetails());
    }
}
