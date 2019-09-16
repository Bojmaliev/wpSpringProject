package mk.trkalo.dnp.dnpshop.service.impl;

import mk.trkalo.dnp.dnpshop.dto.NewOrderClientDto;
import mk.trkalo.dnp.dnpshop.dto.address.AddressesDto;
import mk.trkalo.dnp.dnpshop.model.LoggedUser;
import mk.trkalo.dnp.dnpshop.model.Role;
import mk.trkalo.dnp.dnpshop.model.User;
import mk.trkalo.dnp.dnpshop.model.payloads.request.UpdateClient;
import mk.trkalo.dnp.dnpshop.repository.UserRepository;
import mk.trkalo.dnp.dnpshop.service.AddressService;
import mk.trkalo.dnp.dnpshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AddressService addressService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, AddressService addressService) {
        this.userRepository = userRepository;
        this.addressService = addressService;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(()-> new RuntimeException("Корисникот не постои."));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
    @Override
    public LoggedUser save(LoggedUser user) {
        user.password=passwordEncoder.encode(user.password);
        return userRepository.save(user);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void addPhoneNumber(Long userId, String phoneNumber) {
        User user = findById(userId);

        if(hasPhoneNumber(userId, phoneNumber))throw new RuntimeException("Корисникот веќе го има тој број");

        user.addPhoneNumber(phoneNumber);
        userRepository.saveAndFlush(user);
    }

    @Override
    public boolean hasPhoneNumber(Long userId, String phoneNumber) {
        User user = findById(userId);
        return user.phoneNumbers.contains(phoneNumber);
    }

    @Override
    public List<User> findByNameLikeOrPhoneNumbers(String query) {
        Pageable page = PageRequest.of(0, 8);
        return userRepository.findWhereNameOrPhoneNumberLike(query, page);
    }

    @Override
    public List<User> findFirst8() {
        Pageable page = PageRequest.of(0, 8);
        return userRepository.findAll(page).getContent();
    }


    @Override
    public User save(NewOrderClientDto client) {

        User user = new User(client.name, client.phoneNumbers, addressService.saveAddresses(client.address, client.street, client.object));
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User updateClient(Long clientId, UpdateClient updateClient) {
        User user = findById(clientId);
        user.name = updateClient.name;
        user.phoneNumbers.clear();
        user.phoneNumbers.addAll(updateClient.phoneNumbers);
        return userRepository.saveAndFlush(user);

    }

    @Override
    public User addClientAddress(Long userId, AddressesDto dto) {
        User user = findById(userId);
        addressService.saveAddresses(dto.address, dto.street, dto.object).forEach(user::addAddress);
        return userRepository.saveAndFlush(user);
    }
}
