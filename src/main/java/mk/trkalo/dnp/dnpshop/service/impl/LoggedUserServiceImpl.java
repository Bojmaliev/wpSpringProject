package mk.trkalo.dnp.dnpshop.service.impl;

import mk.trkalo.dnp.dnpshop.model.LoggedUser;
import mk.trkalo.dnp.dnpshop.model.User;
import mk.trkalo.dnp.dnpshop.security.CustomUserDetails;
import mk.trkalo.dnp.dnpshop.service.LoggedUserService;
import mk.trkalo.dnp.dnpshop.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LoggedUserServiceImpl implements LoggedUserService {

    private final UserService userService;

    public LoggedUserServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CustomUserDetails getMyDetails() {
        return (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public LoggedUser get() {
        return (LoggedUser) userService.findById(getMyDetails().getId());

    }
}
