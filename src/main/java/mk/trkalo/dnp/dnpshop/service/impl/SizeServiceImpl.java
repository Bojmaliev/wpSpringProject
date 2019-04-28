package mk.trkalo.dnp.dnpshop.service.impl;

import mk.trkalo.dnp.dnpshop.model.Size;
import mk.trkalo.dnp.dnpshop.repository.SizeRepository;
import mk.trkalo.dnp.dnpshop.service.SizeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeServiceImpl implements SizeService {
    private final SizeRepository sizeRepository;

    public SizeServiceImpl(SizeRepository sizeRepository) {
        this.sizeRepository = sizeRepository;
    }


    @Override
    public Size findById(Long id) {
        return sizeRepository.findById(id).orElseThrow(()-> new RuntimeException("Оваа големина не постои."));
    }

    @Override
    public List<Size> findAll() {
        return sizeRepository.findAll();
    }

    @Override
    public Size save(Size s) {
       if(sizeRepository.existsByName(s.name))throw new RuntimeException("Големина со такво име веќе постои.");
        return sizeRepository.save(s);
    }

    @Override
    public void deleteAll() {
        sizeRepository.deleteAll();
    }
}
