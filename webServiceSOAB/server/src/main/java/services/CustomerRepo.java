package services;

import java.util.List;

import Entities.CustomerEntity;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public interface CustomerRepo {
    @WebMethod
    CustomerEntity saveUser(CustomerEntity user);

    @WebMethod
    CustomerEntity updateUser(CustomerEntity user);

    @WebMethod
    CustomerEntity findUserById(int id);

    @WebMethod
    boolean deleteUser(int id);

    @WebMethod
    List<CustomerEntity> findAllUsers();

    @WebMethod
    int getCountOfUsers();
    
}
