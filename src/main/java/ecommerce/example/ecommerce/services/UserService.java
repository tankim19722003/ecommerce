package ecommerce.example.ecommerce.services;

import ecommerce.example.ecommerce.dtos.*;
import ecommerce.example.ecommerce.responses.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {
    void createUser(UserResgisterDTO userResgisterDTO);

    UserLoginResponse login(UserLoginDTO userLoginDTO);

    UserResponse getUserInfo(Long id);

    IUserResponse updateUserInfo(UserInfoUpdating userInfoUpdating, long userId);

    ImageResponse updateAvatar(MultipartFile file, Long userId) throws IOException;

    UserResponse updateEmail(Long userId, String email);
}
