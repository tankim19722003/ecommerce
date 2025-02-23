package ecommerce.example.ecommerce.services;

import ecommerce.example.ecommerce.dtos.*;
import ecommerce.example.ecommerce.responses.IUserResponse;
import ecommerce.example.ecommerce.responses.UserLoginResponse;
import ecommerce.example.ecommerce.responses.UserResponse;
import ecommerce.example.ecommerce.responses.UserUpdatedResponse;

public interface UserService {
    void createUser(UserResgisterDTO userResgisterDTO);

    UserLoginResponse login(UserLoginDTO userLoginDTO);

    UserResponse getUserInfo(String token);

    IUserResponse updateUserInfo(UserInfoUpdating userInfoUpdating, long userId);

}
