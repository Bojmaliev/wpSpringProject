package mk.trkalo.dnp.dnpshop;

import mk.trkalo.dnp.dnpshop.model.Size;
import mk.trkalo.dnp.dnpshop.model.Type;
import mk.trkalo.dnp.dnpshop.service.SizeService;
import mk.trkalo.dnp.dnpshop.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class DataLoader {

    @Autowired
    private SizeService sizeService;
    @Autowired
    private TypeService typeService;
    //method invoked during the startup
    @PostConstruct
    public void loadData() {
        sizeService.save(new Size("Тегло 0.720ml"));
        sizeService.save(new Size("Тегло 0.370ml"));
        sizeService.save(new Size("Шише 1 L"));

        typeService.save(new Type("Благ"));
        typeService.save(new Type("Лут"));
        typeService.save(new Type("Малку лут"));
        typeService.save(new Type("Џем"));
        typeService.save(new Type("Слатко"));
    }

    //method invoked during the shutdown
    @PreDestroy
    public void removeData() {
        sizeService.deleteAll();
        typeService.deleteAll();
    }
}