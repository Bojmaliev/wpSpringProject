package mk.trkalo.dnp.dnpshop.ports.rest;

import mk.trkalo.dnp.dnpshop.dto.ProductVariantDto;
import mk.trkalo.dnp.dnpshop.model.product.Product;
import mk.trkalo.dnp.dnpshop.service.ProductService;
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

    @Autowired
    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> findAll(){
        return productService.findAllProducts();
    }

    @GetMapping("/{id}")
    public Product findProductById(@PathVariable Long id){
        return productService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product saveProduct(@RequestBody Product p){
        return productService.saveProduct(p);
    }

    @DeleteMapping("/{index}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable("index") Long index) {
        productService.deleteById(index);

    }
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping("/{index}")
    public Product updateProduct(@RequestBody Product student, @PathVariable Long index){
        return productService.updateById(student, index);
    }

    //product variants
    @PostMapping("/{productId}/variants")
    @ResponseStatus(HttpStatus.CREATED)
    public Product saveProductVariant(@PathVariable Long productId, @RequestBody ProductVariantDto p){
        return productService.addProductVariant(productId, p);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping("/{productId}/variants/{variantId}")
    public Product updateProductVariant(@PathVariable Long productId, @RequestBody ProductVariantDto productVariantDto, @PathVariable Long variantId){
        return productService.updateProductVariant(productId, variantId, productVariantDto);
    }

    @DeleteMapping("/{productId}/variants/{variantId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteProductVariant(@PathVariable Long productId, @PathVariable Long variantId) {
        productService.deleteProductVariant(productId, variantId);

    }



}
