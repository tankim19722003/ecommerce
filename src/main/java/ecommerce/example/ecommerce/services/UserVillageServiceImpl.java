package ecommerce.example.ecommerce.services;

import ecommerce.example.ecommerce.Repo.*;
import ecommerce.example.ecommerce.dtos.UserVillageDTO;
import ecommerce.example.ecommerce.models.User;
import ecommerce.example.ecommerce.models.UserVillage;
import ecommerce.example.ecommerce.models.Village;
import ecommerce.example.ecommerce.responses.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserVillageServiceImpl implements UserVillageService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private VillageRepo villageRepo;

    @Autowired
    private UserVillageRepo userVillageRepo;

    @Autowired
    private ProvinceRepo provinceRepo;

    @Autowired
    private DistrictRepo districtRepo;


    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserAddressResponse addUserAddress(UserVillageDTO userVillageDTO) {

        // check user existing
        User user = userRepo.findById(userVillageDTO.getUserId()).orElseThrow(() ->
                new UsernameNotFoundException("User does not found"));

        Village village  = villageRepo.findById(userVillageDTO.getVillageId()).orElseThrow(() ->
                new RuntimeException("Village does not found"));

        try {
            UserVillage userVillage = userVillageRepo.save(UserVillage.builder()
                    .village(village)
                    .user(user)
                    .specificVillage(userVillageDTO.getSpecificAddress())
                    .receiverName(userVillageDTO.getReceiverName())
                    .phoneNumber(userVillageDTO.getPhoneNumber())
                    .build());
            return userVillage.toUserVillageResponse();
        } catch (Exception e) {
            throw new RuntimeException("Address is existing");
        }
    }

    @Override
    public UserAddressResponse updateUserAddress(UserVillageDTO userVillageDTO, long addressId) {
        // check user existing
        Boolean isUserExisting = userRepo.existsById(userVillageDTO.getUserId());

        if (!isUserExisting ) throw  new RuntimeException("User does not found");

        Village village  = villageRepo.findById(userVillageDTO.getVillageId()).orElseThrow(() ->
                new RuntimeException("Village does not found"));

        UserVillage userVillage = userVillageRepo.findById(addressId).orElseThrow(() ->
                new RuntimeException("Old Address does not found"));

        userVillage.setVillage(village);
        userVillage.setSpecificVillage(userVillageDTO.getSpecificAddress());
        userVillage.setPhoneNumber(userVillageDTO.getPhoneNumber());
        userVillage.setReceiverName(userVillageDTO.getReceiverName());

        try {
            UserVillage userVillageSaved =  userVillageRepo.save(userVillage);
            return userVillageSaved.toUserVillageResponse();
        } catch (Exception e) {
            throw new RuntimeException("Address is existing");
        }
    }

    @Override
    public UserAddressListResponse getAllUserAddresses(long userId) {

        Boolean isUserExisting = userRepo.existsById(userId);
        if (!isUserExisting) throw new RuntimeException("User does not found");

        List<UserVillage> userVillages = userVillageRepo.getAllUserAddress(userId);

        List<AddressResponse> userAddressResponses = userVillages.stream().map(userVillage -> {
                return userVillage.toUserAddressResponse();
            }).toList();

        return UserAddressListResponse.builder()
                .userId(userId)
                .addressResponses(userAddressResponses)
                .build();

    }

    @Override
    public List<ProvinceResponse> getAllProvinces() {
        return provinceRepo.findAll().stream().map( province ->
                modelMapper.map(province, ProvinceResponse.class)
        ).toList();
    }

    @Override
    public List<DistrictResponse> getDistrictByProvinceId(long provinceId) {
        return districtRepo.findAllDistrictsByProvinceId(provinceId).stream().map(district -> {
            return modelMapper.map(district, DistrictResponse.class);
        }).toList();
    }

    @Override
    public List<VillageResponse> getVillageByDistrictId(long districtId) {

        return villageRepo.findAllVillagesByDistrictId(districtId)
                .stream()
                .map(district -> modelMapper.map(district, VillageResponse.class))
                .toList();
    }


}
