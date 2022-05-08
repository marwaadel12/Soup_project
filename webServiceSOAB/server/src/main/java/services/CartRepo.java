package services;

import java.util.List;

import Entities.CartEntity;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService

public interface CartRepo {
    @WebMethod
    List<CartEntity> getUserCartByUserId(int userId);

    @WebMethod
    CartEntity updateCart(CartEntity cartEntity, int userId);



}
