package ecommerce.example.ecommerce.services;

import ecommerce.example.ecommerce.dtos.UserResgisterDTO;
import ecommerce.example.ecommerce.models.User;

public interface UserService {
    void createUser(UserResgisterDTO userResgisterDTO);
}
