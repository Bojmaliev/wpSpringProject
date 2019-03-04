package mk.trkalo.dnp.dnpshop.service;

import mk.trkalo.dnp.dnpshop.model.Type;

import java.util.List;

public interface TypeService {
    List<Type> findAll();
    Type save(Type s);
    void deleteAll();
}
