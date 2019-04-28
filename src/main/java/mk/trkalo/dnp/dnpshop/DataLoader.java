package mk.trkalo.dnp.dnpshop;

import mk.trkalo.dnp.dnpshop.model.*;
import mk.trkalo.dnp.dnpshop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;

@Component
public class DataLoader {

    @Autowired
    private SizeService sizeService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private CityService cityService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private AddressService addressService;
    //method invoked during the startup
    @PostConstruct
    public void loadData() {
        //first delete
        orderService.deleteAll();
        productService.deleteAllProducts();
        sizeService.deleteAll();
        typeService.deleteAll();
        addressService.deleteAll();
        userService.deleteAll();
        cityService.deleteAll();




        //add new
        Size s = sizeService.save(new Size("Тегло 0.720ml"));
        Size s1 = sizeService.save(new Size("Тегло 0.370ml"));
        sizeService.save(new Size("Шише 1 L"));

        typeService.save(new Type("Благ"));
        Type t = typeService.save(new Type("Лут"));
        Type t1 = typeService.save(new Type("Малку лут"));
        typeService.save(new Type("Џем"));
        typeService.save(new Type("Слатко"));


        Product p = new Product("Ајвар", "многу вкусен");
        p.addProductVariant(new ProductVariant(s,t, 200, true));
        p.addProductVariant(new ProductVariant(s1,t, 100, true));
        p.addProductVariant(new ProductVariant(s1,t1, 100,true));
        p = productService.saveProduct(p);


        Product p1 = new Product("Лутеница", "многу вкусна исто така");
        p1.addProductVariant(new ProductVariant(s1,t1, 70, true));
        p1 = productService.saveProduct(p1);

        User u1 = new User();
        u1.email = "mbojmaliev@gmail.com";
        u1.name = "Martin Bojmaliev";
        u1.addPhoneNumber("078989478");
        u1 = userService.save(u1);
        userService.addPhoneNumber(u1.id, "077777777");

        City city = cityService.save(new City("Gevgelija"));
        CoordinateAddress a1 = new CoordinateAddress(u1, city, 23.5555,44.4444 );
        ObjectAddress a2 = new ObjectAddress(u1,city, "studentski dom");
        StreetAddress a3 = new StreetAddress(u1,city, "Vamchp petrusevski", "55", "");
        addressService.save(a2);
        addressService.save(a3);
        addressService.save(a1);

        Order o = orderService.createEmptyOrder(u1);

        orderService.connectOrderWithUser(o.id, u1.id);

    }

    //method invoked during the shutdown
}