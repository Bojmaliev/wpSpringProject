package mk.trkalo.dnp.dnpshop.web;

import mk.trkalo.dnp.dnpshop.dto.ProductVariantDto;
import mk.trkalo.dnp.dnpshop.model.Product;
import mk.trkalo.dnp.dnpshop.model.ProductVariant;
import mk.trkalo.dnp.dnpshop.service.ProductService;
import mk.trkalo.dnp.dnpshop.service.ProductVarientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductResource {
    private final ProductService productService;
    private final ProductVarientService productVarientService;

    @Autowired
    public ProductResource(ProductService productService, ProductVarientService productVarientService) {
        this.productService = productService;
        this.productVarientService = productVarientService;
    }

    @GetMapping
    public List<Product> findAll(){
        return productService.findAllProducts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product saveProduct(@RequestBody Product p){
        return productService.saveProduct(p);
    }

    @DeleteMapping("/{index}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable("index") int index) {
        productService.deleteById(index);

    }
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping("/{index}")
    public Product updateProduct(@RequestBody Product student, @PathVariable int index){
        return productService.updateById(student, index);
    }
    @PostMapping("/{id}/variants")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductVariant saveProductVariant(@PathVariable int id, @RequestBody ProductVariantDto p){
        return productVarientService.save(id, p);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping("/variants/{index}")
    public ProductVariant updateProductVariant(@RequestBody ProductVariantDto productVariantDto, @PathVariable int index){
        return productVarientService.updateById(index, productVariantDto);
    }
    @DeleteMapping("/variants/{index}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteProductVariant(@PathVariable("index") int index) {
        productVarientService.deleteById(index);

    }
}
