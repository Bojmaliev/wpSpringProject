package mk.trkalo.dnp.dnpshop;

import mk.trkalo.dnp.dnpshop.model.Product;
import mk.trkalo.dnp.dnpshop.model.ProductVariant;
import mk.trkalo.dnp.dnpshop.model.Size;
import mk.trkalo.dnp.dnpshop.model.Type;
import mk.trkalo.dnp.dnpshop.service.ProductService;
import mk.trkalo.dnp.dnpshop.service.ProductVarientService;
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
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductVarientService productVarientService;
    //method invoked during the startup
    @PostConstruct
    public void loadData() {
        Size s = sizeService.save(new Size("Тегло 0.720ml"));
        Size s1 = sizeService.save(new Size("Тегло 0.370ml"));
        sizeService.save(new Size("Шише 1 L"));

        typeService.save(new Type("Благ"));
        Type t = typeService.save(new Type("Лут"));
        Type t1 = typeService.save(new Type("Малку лут"));
        typeService.save(new Type("Џем"));
        typeService.save(new Type("Слатко"));

        Product p = productService.saveProduct(new Product("Ајвар", "многу вкусен"));
        productVarientService.save(new ProductVariant(p, s,t, 200, true));
        productVarientService.save(new ProductVariant(p, s1,t, 100, true));
        productVarientService.save(new ProductVariant(p, s1,t1, 100,true));
        Product p1 = productService.saveProduct(new Product("Лутеница", "многу вкусна исто така"));
        productVarientService.save(new ProductVariant(p1, s1,t1, 70, true));
    }

    //method invoked during the shutdown
    @PreDestroy
    public void removeData() {
        productService.deleteAllProducts();
        sizeService.deleteAll();
        typeService.deleteAll();
    }
}