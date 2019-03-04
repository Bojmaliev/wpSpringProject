package mk.trkalo.dnp.dnpshop.service.impl;

import mk.trkalo.dnp.dnpshop.model.Size;
import mk.trkalo.dnp.dnpshop.repository.SizeRepository;
import mk.trkalo.dnp.dnpshop.service.SizeService;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class SizeServiceImpl implements SizeService {
    private final SizeRepository sizeRepository;

    public SizeServiceImpl(SizeRepository sizeRepository) {
        this.sizeRepository = sizeRepository;
    }


    @Override
    public List<Size> findAll() {
        return sizeRepository.findAll();
    }

    @Override
    public Size save(Size s) {
        return sizeRepository.save(s);
    }

    @Override
    public void deleteAll() {
        sizeRepository.deleteAll();
    }
}
