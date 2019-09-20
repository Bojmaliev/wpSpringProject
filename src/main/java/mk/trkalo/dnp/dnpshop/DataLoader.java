package mk.trkalo.dnp.dnpshop;

import mk.trkalo.dnp.dnpshop.model.*;
import mk.trkalo.dnp.dnpshop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

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
/*


        shippingService.save(new ShippingMethod("Карго", true));
        shippingService.save(new ShippingMethod("Подигнување", false));
        shippingService.save(new ShippingMethod("Достава Скопје 21.02", true));
        //add new
        Size s = sizeService.save(new Size("Големо", "0.720ml"));
        Size s1 = sizeService.save(new Size("Мало", "0.370ml"));
        sizeService.save(new Size("Шише1", "1L"));

        typeService.save(new Type("Благ"));
        Type t = typeService.save(new Type("Лут"));
        Type t1 = typeService.save(new Type("Малку лут"));
        Type dzem = typeService.save(new Type("Џемdd"));
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

        String [] phoneNumbers = {"078989478", "078377500", "078333333", "078377400", "077736656", "075336859", "078748743", "077777770", "034222456"};
        String [] phoneNumbers1 = {"078119478", "071177500", "071133333", "071177400", "077116656", "075336119", "078741143", "077771170", "034221156"};
        String [] names = {"Martin Bojmaliev", "Marjan Turturov", "Petar Boshkovski", "Petar", "Martin", "Marjan", "Gjorge Bojmaliev", "Kristijan Bojmaliev", "Meri Bojmalieva", "Ela stamkova"};
        User u1 = new User("Martin");
        for(String name : names) {
            Set<String> phones = new TreeSet<>();
            u1 = new User(name);
            u1.addPhoneNumber(getRandom(phoneNumbers));
            u1 = userService.save(u1);

            if (System.currentTimeMillis() % 2 == 0) userService.addPhoneNumber(u1.id, getRandom(phoneNumbers1));
        }

        City city = cityService.save(new City("Гевгелија"));
        City city1 = cityService.save(new City("Скопје"));
        City city2 = cityService.save(new City("Струмица"));
        City city3 = cityService.save(new City("Неготино"));
        Address a1 = new Address(city, 23.5555,44.4444 );
        u1.addAddress(a1);
        u1.addAddress(new ObjectAddress(city1, "Aerodrom", "", "Majka tereza"));
        userService.save(u1);*/

    }
    public static String getRandom(String[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }
    //method invoked during the shutdown
}