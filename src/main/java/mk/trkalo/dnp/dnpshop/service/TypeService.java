package mk.trkalo.dnp.dnpshop.service;

import mk.trkalo.dnp.dnpshop.model.product.Type;

import java.util.List;

public interface TypeService {
    Type findById(Long id);
    List<Type> findAll();
    Type save(Type s);
    void deleteAll();
}
