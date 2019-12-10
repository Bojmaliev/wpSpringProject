package mk.trkalo.dnp.dnpshop.service;

import mk.trkalo.dnp.dnpshop.model.product.Size;

import java.util.List;

public interface SizeService {
    Size findById(Long id);
    List<Size> findAll();
    Size save(Size s);
    void deleteAll();
}
