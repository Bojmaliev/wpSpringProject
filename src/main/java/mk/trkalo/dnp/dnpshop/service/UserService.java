package mk.trkalo.dnp.dnpshop.service;

import mk.trkalo.dnp.dnpshop.dto.NewOrderClientDto;
import mk.trkalo.dnp.dnpshop.dto.address.AddressesDto;
import mk.trkalo.dnp.dnpshop.model.user.LoggedUser;
import mk.trkalo.dnp.dnpshop.model.user.User;
import mk.trkalo.dnp.dnpshop.model.payloads.request.UpdateClient;

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
    List<User> findFirst8();

    User save(NewOrderClientDto newOrderClientDto);

    User updateClient(Long clientId, UpdateClient updateClient);

    User addClientAddress(Long userId, AddressesDto addressesDto);
}
