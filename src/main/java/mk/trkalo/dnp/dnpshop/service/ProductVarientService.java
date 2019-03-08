package mk.trkalo.dnp.dnpshop.service;

import mk.trkalo.dnp.dnpshop.dto.ProductVariantDto;
import mk.trkalo.dnp.dnpshop.model.ProductVariant;

public interface ProductVarientService {
    ProductVariant saveProductVariant(ProductVariant p);
    ProductVariant saveProductVariant(int product, ProductVariantDto p);
}
