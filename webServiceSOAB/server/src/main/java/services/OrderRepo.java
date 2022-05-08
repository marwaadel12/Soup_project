package services;

import java.util.List;

import Entities.OrderEntity;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public interface OrderRepo {
    @WebMethod
    List<OrderEntity> findAllByUserId(int userId);

    @WebMethod
    List<OrderEntity> getAllUserOrder(String email);

    @WebMethod
    boolean deleteOrder(int id);

    @WebMethod
    OrderEntity saveOrder(OrderEntity order);

    @WebMethod
    OrderEntity findOrderById(int id);

    @WebMethod
    OrderEntity updateOrder(OrderEntity order);

}
