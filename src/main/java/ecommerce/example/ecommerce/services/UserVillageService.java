package ecommerce.example.ecommerce.services;

import ecommerce.example.ecommerce.dtos.UserVillageDTO;
import ecommerce.example.ecommerce.responses.*;

import java.util.List;

public interface UserVillageService {
    UserAddressResponse addUserAddress(UserVillageDTO userVillageDTO);
    UserAddressResponse updateUserAddress(UserVillageDTO userVillageDTO, long addressId);
    UserAddressListResponse getAllUserAddresses(long userId);
    void deleteUserAddress(long addressId);

    List<ProvinceResponse> getAllProvinces();
    List<DistrictResponse> getDistrictByProvinceId(long provinceId);
    List<VillageResponse> getVillageByDistrictId(long districtId);
}
