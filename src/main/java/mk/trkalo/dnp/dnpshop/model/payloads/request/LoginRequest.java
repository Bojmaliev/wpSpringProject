package mk.trkalo.dnp.dnpshop.model.payloads.request;

import javax.validation.constraints.*;

public class LoginRequest {
    @NotBlank(message = "Електронската пошта е задолжителна")
    public String email;
    @NotBlank(message = "Лозинката е задолжителна")
    public String password;
}