package services;

import java.util.List;

import Entities.CartEntity;
import jakarta.jws.WebService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

@WebService(endpointInterface = "services.CartRepo")

public class CartRepoImpl implements CartRepo {
    private static CartRepoImpl cartRepoImpl;
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("rest");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    public static CartRepoImpl getInstance() {
        if (cartRepoImpl == null) {
            cartRepoImpl = new CartRepoImpl();

        }
        return cartRepoImpl;
    }

    @Override
    public List<CartEntity> getUserCartByUserId(int userId) {
        Query query = entityManager.createQuery("" +
                "select  c from CartEntity c join c.customer u where  u.id=:user_id", CartEntity.class);
        query.setParameter("user_id", userId);
        List<CartEntity> cartEntityList = (List<CartEntity>) query.getResultList();
        System.out.println(cartEntityList);
        return cartEntityList;
    }

    

    @Override
    public CartEntity updateCart(CartEntity cartEntity, int userId) {
        EntityManager em = entityManagerFactory.createEntityManager();
        CartEntity updatedCart = getUserCartByUserId(userId).get(0);
        updatedCart.setProduct(cartEntity.getProduct());
        updatedCart.setQuantity(cartEntity.getQuantity());
        em.getTransaction().begin();
        em.merge(updatedCart);
        em.getTransaction().commit();
        return updatedCart;
    }


}
