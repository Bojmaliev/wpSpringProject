package mk.trkalo.dnp.dnpshop;

import mk.trkalo.dnp.dnpshop.model.*;
import mk.trkalo.dnp.dnpshop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

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
    @Autowired
    private ShippingService shippingService;

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
        shippingService.deleteAll();



        shippingService.save(new ShippingMethod("Карго"));
        shippingService.save(new ShippingMethod("Подигнување"));
        shippingService.save(new ShippingMethod("Достава Скопје 21.02"));
        //add new
        Size s = sizeService.save(new Size("Големо", "0.720ml"));
        Size s1 = sizeService.save(new Size("Мало", "0.370ml"));
        sizeService.save(new Size("Шише", "1L"));

        typeService.save(new Type("Благ"));
        Type t = typeService.save(new Type("Лут"));
        Type t1 = typeService.save(new Type("Малку лут"));
        Type dzem = typeService.save(new Type("Џем"));
        typeService.save(new Type("Слатко"));


        Product p = new Product("Ајвар", "многу вкусен");
        p.addProductVariant(new ProductVariant(s,t, 200, true));
        p.addProductVariant(new ProductVariant(s1,t, 100, true));
        p.addProductVariant(new ProductVariant(s1,t1, 100,true));
        p = productService.saveProduct(p);


        Product p1 = new Product("Лутеница", "многу вкусна исто така");
        p1.addProductVariant(new ProductVariant(s1,t1, 70, true));
        p1 = productService.saveProduct(p1);

        Product p2 = new Product("Малиџано", "многу вкусна исто така");
        p2.addProductVariant(new ProductVariant(s1,t1, 80, true));
        p2 = productService.saveProduct(p2);

        Product p3 = new Product("Диви смокви", "многу вкусна исто така");
        p3.addProductVariant(new ProductVariant(s1,dzem, 100, true));
        p3 = productService.saveProduct(p3);

        User u1 = u1 = new User();
        String[] emails = {"mbojmaliev@gmail.com", "marboj4@gmail.com", "marjan.turturov@gmail.com", "kristijanmanchester@gmail.com", "marjan@turturov.mk", "mbojmaliev@trkalo.mk"};
        String [] phoneNumbers = {"078989478", "078377500", "078333333", "078377400", "077736656", "075336859", "078748743", "077777770", "034222456"};
        String [] phoneNumbers1 = {"078119478", "071177500", "071133333", "071177400", "077116656", "075336119", "078741143", "077771170", "034221156"};
        String [] names = {"Martin Bojmaliev", "Marjan Turturov", "Petar Boshkovski", "Petar", "Martin", "Marjan", "Gjorge Bojmaliev", "Kristijan Bojmaliev", "Meri Bojmalieva", "Ela stamkova"};
        for(String name : names) {
            u1 = new User();
            u1.email = getRandom(emails);
            u1.name = name;
            u1.addPhoneNumber(getRandom(phoneNumbers));
            u1 = userService.save(u1);

            if (LocalDateTime.now().getNano() % 2 == 0) userService.addPhoneNumber(u1.id, getRandom(phoneNumbers1));
        }

        City city = cityService.save(new City("Гевгелија"));
        City city1 = cityService.save(new City("Скопје"));
        City city2 = cityService.save(new City("Струмица"));
        City city3 = cityService.save(new City("Неготино"));
        Address a1 = new Address(u1, city, 23.5555,44.4444 );

        ObjectAddress a2 = new ObjectAddress(u1 ,city, "studentski dom");
        a2.municipality = "Karposh";
        StreetAddress a3 = new StreetAddress(u1, city, "Moskovksa", "55", "");
        a3.municipality = "Karposh";
        addressService.save(a2);
        addressService.save(a3);
        addressService.save(a1);

        Order o = orderService.createEmptyOrder(u1);

        orderService.connectOrderWithUser(o.id, u1.id);

    }
    public static String getRandom(String[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }
    //method invoked during the shutdown
}