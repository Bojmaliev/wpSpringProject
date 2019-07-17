package mk.trkalo.dnp.dnpshop.service;

import mk.trkalo.dnp.dnpshop.model.payloads.request.LoginRequest;

public interface AuthService {


    String login(LoginRequest loginRequest);
}