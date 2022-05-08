package services;

import java.util.List;

import Entities.AdminEntity;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService 
public interface AdminRepo {
 
    @WebMethod
    List<AdminEntity> getAllAdmins();
}
