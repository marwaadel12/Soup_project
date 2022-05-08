package services;

import java.util.List;

import Entities.CategoryEntity;
import Entities.ProductEntity;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService

public interface CategoryRepo {
    @WebMethod
    CategoryEntity saveCategory(CategoryEntity category);

    @WebMethod
    boolean deleteCategory(int id);

    @WebMethod
    CategoryEntity findCategoryById(int id);

    @WebMethod
    List<CategoryEntity> findAllCategory();

    @WebMethod
    CategoryEntity updateCategory(CategoryEntity entity);

    @WebMethod
    int getCountOfCategory();

    @WebMethod
    List<ProductEntity> getAllProductsByCatgeoryId(int id);

}
