package services;

import java.math.BigDecimal;
import java.util.List;
import Entities.ProductEntity;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService

public interface ProductRepo {
    @WebMethod
    List<ProductEntity> findAllProductByCategoryId(int id);

    @WebMethod
    List<ProductEntity> findProductByName(String productName);

    @WebMethod
    List<ProductEntity> findProductByPrice(double productPrice);

    @WebMethod
    List<ProductEntity> findAllProducts();

    @WebMethod
    ProductEntity saveProduct(ProductEntity product);

    @WebMethod
    ProductEntity updateProduct(ProductEntity product);

    @WebMethod
    ProductEntity findProductById(long id);

    @WebMethod
    boolean deleteProduct(long id);

    @WebMethod
    int getCountOfProducts();
}
