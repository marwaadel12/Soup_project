package services;

import jakarta.jws.WebService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import java.util.List;

import Entities.AdminEntity;

@WebService(endpointInterface = "services.AdminRepo")

public class AdminRepoImpl implements AdminRepo {
    private static AdminRepoImpl adminRepoImpl;

    public static AdminRepoImpl getInstance() {
        if (adminRepoImpl == null) {
            adminRepoImpl = new AdminRepoImpl();

        }
        return adminRepoImpl;
    }

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("rest");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public List<AdminEntity> getAllAdmins() {
        Query query = entityManager.createQuery("select u from AdminEntity u ", AdminEntity.class);
        List<AdminEntity> adminEntityList = (List<AdminEntity>) query.getResultList();
        return adminEntityList;
    }

}
