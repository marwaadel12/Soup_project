package services;

import java.util.List;
import jakarta.jws.WebService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import Entities.CategoryEntity;
import Entities.ProductEntity;

@WebService(endpointInterface = "services.CategoryRepo")

public class CategoryRepoImpl implements CategoryRepo {

    private static CategoryRepoImpl categoryRepoImpl;

    public static CategoryRepoImpl getInstance() {
        if (categoryRepoImpl == null) {
            categoryRepoImpl = new CategoryRepoImpl();

        }
        return categoryRepoImpl;
    }

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("rest");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public CategoryEntity saveCategory(CategoryEntity category) {
        entityManager.getTransaction().begin();
        entityManager.persist(category);
        entityManager.getTransaction().commit();
        return category;
    }

    @Override
    public boolean deleteCategory(int id) {
        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("DELETE FROM CategoryEntity c WHERE c.id like :id").setParameter("id",id);

            if(query.executeUpdate()==1){
                entityManager.getTransaction().commit();;
                return true;
            }


        return false;

}

    @Override
    public CategoryEntity findCategoryById(int id) {
        return entityManager.find(CategoryEntity.class, id);
    }

    @Override
    public List<CategoryEntity> findAllCategory() {
        List<CategoryEntity> resultList = (List<CategoryEntity>) entityManager.createQuery("SELECT c FROM CategoryEntity c ").getResultList();
        return resultList;
    }

    @Override
    public CategoryEntity updateCategory(CategoryEntity entity) {
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public List<ProductEntity> getAllProductsByCatgeoryId(int id) {
        Query query = entityManager.createQuery("SELECT p FROM ProductEntity p join p.category c where c.categoryId=:id ").setParameter("id",id);
        return query.getResultList();
    }

    @Override
    public int getCountOfCategory() {
        Query queryTotal = entityManager.createQuery("Select count(p) from p");
        long countResult = (long) queryTotal.getSingleResult();
        return (int) countResult;
    }

}
