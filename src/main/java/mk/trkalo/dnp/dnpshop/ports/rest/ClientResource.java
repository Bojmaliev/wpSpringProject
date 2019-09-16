package mk.trkalo.dnp.dnpshop.ports.rest;
import mk.trkalo.dnp.dnpshop.dto.NewOrderClientDto;
import mk.trkalo.dnp.dnpshop.dto.address.AddressDto;
import mk.trkalo.dnp.dnpshop.dto.address.AddressesDto;
import mk.trkalo.dnp.dnpshop.model.User;
import mk.trkalo.dnp.dnpshop.model.payloads.request.UpdateClient;
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
    @GetMapping("/search")
    public List<User> getFirst8(){
        return userService.findFirst8();
    }
    @PostMapping
    public User newOrderClient(@RequestBody NewOrderClientDto newOrderClientDto ){
        return userService.save(newOrderClientDto);
    }

    @PostMapping("/{userId}/new_address")
    public User clientAddAddress(@PathVariable Long userId, @RequestBody AddressesDto addressesDto){
        return userService.addClientAddress(userId, addressesDto);
    }

    @PatchMapping("/{clientId}")
    public User updateClient(@PathVariable Long clientId, @RequestBody UpdateClient updateClient){
        return userService.updateClient(clientId, updateClient);
    }
}
