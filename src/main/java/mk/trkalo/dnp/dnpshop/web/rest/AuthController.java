package mk.trkalo.dnp.dnpshop.web.rest;

import mk.trkalo.dnp.dnpshop.model.payloads.request.LoginRequest;
import mk.trkalo.dnp.dnpshop.exception.Error;
import mk.trkalo.dnp.dnpshop.model.payloads.response.ApiResponse;
import mk.trkalo.dnp.dnpshop.model.payloads.response.JwtAuthenticationResponse;
import mk.trkalo.dnp.dnpshop.service.impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    @Autowired
    private AuthServiceImpl authService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult res) {
        if(res.hasErrors()) return ResponseEntity.badRequest().body(new ApiResponse(res));

        String jwt =  authService.login(loginRequest);

        JwtAuthenticationResponse token = new JwtAuthenticationResponse(jwt);
        return ResponseEntity.ok(token);
    }

    /*@PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest) throws IOException, MessagingException {

        User result = userManagementService.register(signUpRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{id}")
                .buildAndExpand(result.id).toUri();

        return ResponseEntity.created(location).body(new ApiResponse( "User registered successfully"));
    }*/

}
