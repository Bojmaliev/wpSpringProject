package mk.trkalo.dnp.dnpshop.service.impl;
import mk.trkalo.dnp.dnpshop.dto.address.AddressDto;
import mk.trkalo.dnp.dnpshop.dto.address.StreetDto;
import mk.trkalo.dnp.dnpshop.model.address.Address;
import mk.trkalo.dnp.dnpshop.model.address.City;
import mk.trkalo.dnp.dnpshop.model.address.ObjectAddress;
import mk.trkalo.dnp.dnpshop.model.address.StreetAddress;
import mk.trkalo.dnp.dnpshop.repository.AddressRepository;
import mk.trkalo.dnp.dnpshop.service.AddressService;
import mk.trkalo.dnp.dnpshop.service.CityService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository<Address> addressRepository;
    private final AddressRepository<ObjectAddress> objectAddressRepository;
    private final AddressRepository<StreetAddress> streetAddressRepository;
    private final CityService cityService;

    public AddressServiceImpl(AddressRepository<Address> addressRepository, AddressRepository<ObjectAddress> objectAddressRepository, AddressRepository<StreetAddress> streetAddressRepository, CityService cityService) {
        this.addressRepository = addressRepository;
        this.objectAddressRepository = objectAddressRepository;
        this.streetAddressRepository = streetAddressRepository;
        this.cityService = cityService;
    }


//    @Override
//    public Address save(Address address) {
//        return addressRepository.save(address);
//    }
//
//    @Override
//    public StreetAddress save(StreetAddress streetAddress) {
//        return streetAddressRepository.save(streetAddress);
//    }
//
//    @Override
//    public ObjectAddress save(ObjectAddress objectAddress) {
//        return objectAddressRepository.save(objectAddress);
//    }

    @Override
    public void deleteAll() {
        objectAddressRepository.deleteAll();
        streetAddressRepository.deleteAll();
        addressRepository.deleteAll();
    }

    @Override
    public List<Address> saveAddresses(AddressDto address, StreetDto street, String object) {

        City c = cityService.findById(address.cityId);
        List<Address> addressList = new ArrayList<>();
        if(street.name != null && object != null && street.name.length() > 5 && object.length() > 2){
            addressList.add(new StreetAddress(c, address.municipality, address.description, street.name, street.number, street.additional));
            addressList.add(new ObjectAddress(c, address.municipality, address.description, object));
        }else if(street.name != null  && street.name.length() > 5){
            addressList.add(new StreetAddress(c, address.municipality, address.description, street.name, street.number, street.additional));
        }else if(object != null && object.length() > 2){
            addressList.add(new ObjectAddress(c, address.municipality, address.description, object));
        }else{
            addressList.add(new Address(c, address.municipality, address.description));
        }

       return addressList;

    }

    @Override
    public Address findById(Long addressId) {
        return addressRepository.findById(addressId).orElseThrow(()-> new Error("Адресата не постои."));
    }

}
