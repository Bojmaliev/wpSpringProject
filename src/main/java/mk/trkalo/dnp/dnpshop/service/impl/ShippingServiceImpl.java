package mk.trkalo.dnp.dnpshop.service.impl;

import mk.trkalo.dnp.dnpshop.model.order.ShippingMethod;
import mk.trkalo.dnp.dnpshop.repository.ShippingRepository;
import mk.trkalo.dnp.dnpshop.service.ShippingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShippingServiceImpl implements ShippingService {
    private final ShippingRepository shippingRepository;

    public ShippingServiceImpl(ShippingRepository shippingRepository) {
        this.shippingRepository = shippingRepository;
    }

    @Override
    public List<ShippingMethod> findAll() {
        return shippingRepository.findAll();
    }

    @Override
    public void deleteAll() {
        shippingRepository.deleteAll();
    }

    @Override
    public ShippingMethod save(ShippingMethod save) {
        return shippingRepository.save(save);
    }

    @Override
    public ShippingMethod getDefaultShippingMethod() {
        return findAllActive().get(0);
    }

    @Override
    public ShippingMethod findById(Long shippingId) {
        return shippingRepository.findById(shippingId).orElseThrow(()-> new RuntimeException("Доставувачкиот метод не постои"));
    }

    @Override
    public List<ShippingMethod> findAllActive() {
        return shippingRepository.findAllByActiveIsTrue();
    }
}
