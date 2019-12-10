package mk.trkalo.dnp.dnpshop.service;

import mk.trkalo.dnp.dnpshop.model.user.LoggedUser;
import mk.trkalo.dnp.dnpshop.security.CustomUserDetails;

public interface LoggedUserService {

    CustomUserDetails getMyDetails();
    LoggedUser get();

}
