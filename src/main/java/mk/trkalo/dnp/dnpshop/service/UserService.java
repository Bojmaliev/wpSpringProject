package mk.trkalo.dnp.dnpshop.service;

import mk.trkalo.dnp.dnpshop.dto.NewOrderClientDto;
import mk.trkalo.dnp.dnpshop.model.LoggedUser;
import mk.trkalo.dnp.dnpshop.model.User;

import java.util.List;

public interface UserService {
    User findById(Long id);
    User save(User user);

    LoggedUser save(LoggedUser user);

    void deleteAll();
    List<User> findAll();
    void addPhoneNumber(Long userId, String phoneNumber);
    boolean hasPhoneNumber(Long userId, String phoneNumber);
    List<User> findByNameLikeOrPhoneNumbers(String query);

    User save(NewOrderClientDto newOrderClientDto);
}
