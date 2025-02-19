package ecommerce.example.ecommerce.services;

import ecommerce.example.ecommerce.dtos.UserInfoUpdating;
import ecommerce.example.ecommerce.dtos.UserLoginDTO;
import ecommerce.example.ecommerce.dtos.UserResgisterDTO;
import ecommerce.example.ecommerce.responses.UserLoginResponse;
import ecommerce.example.ecommerce.responses.UserResponse;

public interface UserService {
    void createUser(UserResgisterDTO userResgisterDTO);

    UserLoginResponse login(UserLoginDTO userLoginDTO);

    UserResponse getUserInfo(String token);

    UserResponse updateUserInfo(UserInfoUpdating userInfoUpdating, long userId);
}
