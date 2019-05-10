package mk.trkalo.dnp.dnpshop.service.impl;

import mk.trkalo.dnp.dnpshop.model.Address;
import mk.trkalo.dnp.dnpshop.model.ObjectAddress;
import mk.trkalo.dnp.dnpshop.model.StreetAddress;
import mk.trkalo.dnp.dnpshop.repository.AddressRepository;
import mk.trkalo.dnp.dnpshop.service.AddressService;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository<Address> addressRepository;
    private final AddressRepository<ObjectAddress> objectAddressRepository;
    private final AddressRepository<StreetAddress> streetAddressRepository;

    public AddressServiceImpl(AddressRepository<Address> addressRepository, AddressRepository<ObjectAddress> objectAddressRepository, AddressRepository<StreetAddress> streetAddressRepository) {
        this.addressRepository = addressRepository;
        this.objectAddressRepository = objectAddressRepository;
        this.streetAddressRepository = streetAddressRepository;
    }


    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public StreetAddress save(StreetAddress streetAddress) {
        return streetAddressRepository.save(streetAddress);
    }

    @Override
    public ObjectAddress save(ObjectAddress objectAddress) {
        return objectAddressRepository.save(objectAddress);
    }

    @Override
    public void deleteAll() {
        objectAddressRepository.deleteAll();
        streetAddressRepository.deleteAll();
        addressRepository.deleteAll();
    }

}
