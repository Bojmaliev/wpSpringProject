package mk.trkalo.dnp.dnpshop.service;

import mk.trkalo.dnp.dnpshop.dto.ProductVariantDto;
import mk.trkalo.dnp.dnpshop.model.ProductVariant;

public interface ProductVarientService {
    ProductVariant findById(int id);
    ProductVariant save(ProductVariant p);
    ProductVariant save(int product, ProductVariantDto p);
    ProductVariant updateById(int id, ProductVariantDto p);

    void deleteById(int index);
}
