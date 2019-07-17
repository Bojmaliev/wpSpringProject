package mk.trkalo.dnp.dnpshop.service;

import mk.trkalo.dnp.dnpshop.model.ShippingMethod;
import mk.trkalo.dnp.dnpshop.repository.ShippingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ShippingService {
    List<ShippingMethod> findAll();
    void deleteAll();
    ShippingMethod save(ShippingMethod save);

    ShippingMethod getDefaultShippingMethod();

    ShippingMethod findById(Long shippingId);

    List<ShippingMethod> findAllActive();
}
