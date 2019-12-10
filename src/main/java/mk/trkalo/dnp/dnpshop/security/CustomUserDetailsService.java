package mk.trkalo.dnp.dnpshop.security;

import mk.trkalo.dnp.dnpshop.model.user.LoggedUser;
import mk.trkalo.dnp.dnpshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoggedUser user = repo
                .findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("Email not found"));
        return new CustomUserDetails(user);
    }

    public UserDetails loadUserById(Long userId) {
        LoggedUser user = (LoggedUser) repo.findById(userId).orElseThrow(()-> new UsernameNotFoundException("User not foun"));
        return new CustomUserDetails(user);
    }
}