package mk.trkalo.dnp.dnpshop.service;

import mk.trkalo.dnp.dnpshop.model.Product;
import mk.trkalo.dnp.dnpshop.model.User;

import java.util.List;

public interface UserService {
    User findById(Long id);
    User save(User user);
    void deleteAll();
    List<User> findAll();
    void addPhoneNumber(Long userId, String phoneNumber);
    boolean hasPhoneNumber(Long userId, String phoneNumber);
}
