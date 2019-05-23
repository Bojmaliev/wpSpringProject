package mk.trkalo.dnp.dnpshop.web.rest;
import mk.trkalo.dnp.dnpshop.dto.NewOrderClientDto;
import mk.trkalo.dnp.dnpshop.model.User;
import mk.trkalo.dnp.dnpshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/clients", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientResource {
    final private UserService userService;

    @Autowired
    public ClientResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id){
        return userService.findById(id);
    }

    @GetMapping("/search/{query}")
    public List<User> searchByWhatEver(@PathVariable String query){
        return userService.findByNameLikeOrPhoneNumbers(query);
    }
    @PostMapping
    public User newOrderClient(@RequestBody NewOrderClientDto newOrderClientDto ){
        return userService.save(newOrderClientDto);


    }
}
