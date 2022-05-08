package services;

import jakarta.jws.WebService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.List;
import Entities.OrderEntity;

@WebService(endpointInterface = "services.OrderRepo")

public class OrderRepoImpl implements OrderRepo {

    private static OrderRepoImpl OrderRepoImpl;

    public static OrderRepoImpl getInstance() {
        if (OrderRepoImpl == null) {
            OrderRepoImpl = new OrderRepoImpl();

        }
        return OrderRepoImpl;
    }

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("rest");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public List<OrderEntity> findAllByUserId(int userId) {

        TypedQuery<OrderEntity> query = entityManager.createNamedQuery("findAllByUserId", OrderEntity.class);
        query.setParameter("user_id", userId);
        return query.getResultList();
    }

    @Override
    public List<OrderEntity> getAllUserOrder(String email) {

        Query queryTotal = entityManager
                .createQuery("Select p from OrderEntity p where p.email=  " + "'" + email + "'");
        List<OrderEntity> orderEntityList = queryTotal.getResultList();
        return orderEntityList;
    }

    @Override
    public OrderEntity findOrderById(int id) {
        TypedQuery<OrderEntity> query = entityManager.createQuery("SELECT c FROM OrderEntity c where  c.id = :id",
                OrderEntity.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public OrderEntity updateOrder(OrderEntity order) {

        int id = order.getId();
        OrderEntity updateOrder = findOrderById(id);
        updateOrder.setDescription(order.getDescription());
        updateOrder.setEmail(order.getEmail());
        updateOrder.setPhoneNumber(order.getPhoneNumber());
        updateOrder.setTotalPrice(order.getTotalPrice());
        entityManager.getTransaction().begin();
        OrderEntity userUpdated = entityManager.merge(updateOrder);
        entityManager.getTransaction().commit();
        return userUpdated;

    }

    @Override
    public boolean deleteOrder(int id) {
        entityManager.getTransaction().begin();
        Query query = entityManager
                .createQuery("DELETE FROM OrderEntity c WHERE c.id like :id");
        query.setParameter("id", id);
        if (query.executeUpdate() == 1) {
            entityManager.getTransaction().commit();
            return true;
        } else {
            return false;
        }

    }

    @Override
    public OrderEntity saveOrder(OrderEntity order) {
        entityManager.getTransaction().begin();
        entityManager.persist(order);
        entityManager.getTransaction().commit();
        return order;
    }

}
