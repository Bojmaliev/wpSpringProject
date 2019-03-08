package mk.trkalo.dnp.dnpshop.service;

import mk.trkalo.dnp.dnpshop.model.Size;

import java.util.List;

public interface SizeService {
    Size findById(int id);
    List<Size> findAll();
    Size save(Size s);
    void deleteAll();
}
