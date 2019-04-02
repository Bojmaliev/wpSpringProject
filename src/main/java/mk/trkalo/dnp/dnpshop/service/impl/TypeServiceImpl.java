package mk.trkalo.dnp.dnpshop.service.impl;

import mk.trkalo.dnp.dnpshop.model.Type;
import mk.trkalo.dnp.dnpshop.repository.TypeRepository;
import mk.trkalo.dnp.dnpshop.service.TypeService;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    private final TypeRepository typeRepository;

    public TypeServiceImpl(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }


    @Override
    public Type findById(Long id) {
        return typeRepository.findById(id).orElseThrow(()-> new RuntimeException("Овој тип не постои."));
    }

    @Override
    public List<Type> findAll() {
        return typeRepository.findAll();
    }

    @Override
    public Type save(Type s) {
        if(typeRepository.existsByName(s.getName()))throw new RuntimeException("Тип со такво име веќе постои.");
        return typeRepository.save(s);
    }

    @Override
    public void deleteAll() {
        typeRepository.deleteAll();
    }
}
