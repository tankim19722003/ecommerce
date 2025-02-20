package ecommerce.example.ecommerce.services;

import ecommerce.example.ecommerce.dtos.UserVillageDTO;
import ecommerce.example.ecommerce.responses.UserAddressListResponse;
import ecommerce.example.ecommerce.responses.UserAddressResponse;

public interface UserVillageService {
    UserAddressResponse addUserAddress(UserVillageDTO userVillageDTO);
    UserAddressResponse updateUserAddress(UserVillageDTO userVillageDTO, long addressId);
    UserAddressListResponse getAllUserAddresses(long userId);
}
