package mk.trkalo.dnp.dnpshop.service.impl;

import mk.trkalo.dnp.dnpshop.model.CoordinateAddress;
import mk.trkalo.dnp.dnpshop.model.ObjectAddress;
import mk.trkalo.dnp.dnpshop.model.StreetAddress;
import mk.trkalo.dnp.dnpshop.repository.AddressRepository;
import mk.trkalo.dnp.dnpshop.service.AddressService;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository<CoordinateAddress> coordinateAddressRepository;
    private final AddressRepository<ObjectAddress> objectAddressRepository;
    private final AddressRepository<StreetAddress> streetAddressRepository;

    public AddressServiceImpl(AddressRepository coordinateAddressRepository, AddressRepository<ObjectAddress> objectAddressRepository, AddressRepository<StreetAddress> streetAddressRepository) {
        this.coordinateAddressRepository = coordinateAddressRepository;
        this.objectAddressRepository = objectAddressRepository;
        this.streetAddressRepository = streetAddressRepository;
    }

    @Override
    public CoordinateAddress save(CoordinateAddress address) {
        return coordinateAddressRepository.save(address);
    }

    @Override
    public StreetAddress save(StreetAddress address) {
        return streetAddressRepository.save(address);
    }

    @Override
    public ObjectAddress save(ObjectAddress address) {
        return objectAddressRepository.save(address);
    }

    @Override
    public void deleteAll() {
        coordinateAddressRepository.deleteAll();
        objectAddressRepository.deleteAll();
        streetAddressRepository.deleteAll();
    }

}
