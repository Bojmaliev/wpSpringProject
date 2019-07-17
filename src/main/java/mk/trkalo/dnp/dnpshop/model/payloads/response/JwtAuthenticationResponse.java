package mk.trkalo.dnp.dnpshop.model.payloads.response;

public class JwtAuthenticationResponse extends ApiResponse{
    public String accessToken;
    public String tokenType = "Bearer";

    public JwtAuthenticationResponse(String accessToken) {
        super("Успешна најава!");
        this.accessToken = accessToken;
    }
}