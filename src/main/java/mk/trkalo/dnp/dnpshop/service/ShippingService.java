package mk.trkalo.dnp.dnpshop.service;

import mk.trkalo.dnp.dnpshop.model.order.ShippingMethod;

import java.util.List;

public interface ShippingService {
    List<ShippingMethod> findAll();
    void deleteAll();
    ShippingMethod save(ShippingMethod save);

    ShippingMethod getDefaultShippingMethod();

    ShippingMethod findById(Long shippingId);

    List<ShippingMethod> findAllActive();
}
