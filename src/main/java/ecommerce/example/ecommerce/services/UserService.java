package ecommerce.example.ecommerce.services;

import ecommerce.example.ecommerce.dtos.UserLoginDTO;
import ecommerce.example.ecommerce.dtos.UserResgisterDTO;
import ecommerce.example.ecommerce.responses.UserLoginResponse;

public interface UserService {
    void createUser(UserResgisterDTO userResgisterDTO);

    UserLoginResponse login(UserLoginDTO userLoginDTO);
}
