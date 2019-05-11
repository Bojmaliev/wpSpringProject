package mk.trkalo.dnp.dnpshop.service.impl;

import mk.trkalo.dnp.dnpshop.model.User;
import mk.trkalo.dnp.dnpshop.repository.UserRepository;
import mk.trkalo.dnp.dnpshop.service.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
        Pageable page = PageRequest.of(0, 7);
        return userRepository.findWhereNameOrPhoneNumberLike(query, page);
    }
}
