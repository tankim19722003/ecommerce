package ecommerce.example.ecommerce.controllers;

import ecommerce.example.ecommerce.dtos.UserVillageDTO;
import ecommerce.example.ecommerce.responses.*;
import ecommerce.example.ecommerce.services.Impl.OwnerService;
import ecommerce.example.ecommerce.services.UserVillageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/user_village")
public class UserVillageController {

    @Autowired
    private UserVillageService userVillageService;

    @Autowired
    private OwnerService ownerService;

    @PostMapping("/add_user_address")
    public ResponseEntity<?> addUserAddress (
            @RequestBody UserVillageDTO userVillageDTO
    ) {
        try {

            ownerService.checkValidUser(userVillageDTO.getUserId());

            UserAddressResponse userAddressResponse = userVillageService
                    .addUserAddress(userVillageDTO);

            return ResponseEntity.ok().body(userAddressResponse);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    EResponse.builder()
                            .name("Error")
                            .message(e.getMessage())
                            .build()
            );
        }
    }

    @PutMapping("/update_user_address/{addressId}")
    public ResponseEntity<?> updateUserAddress(
            @RequestBody UserVillageDTO userVillageDTO,
            @PathVariable("addressId") long addressId
    ) {
        try {
            ownerService.checkValidUser(userVillageDTO.getUserId());

            UserAddressResponse userAddressResponse =  userVillageService
                    .updateUserAddress(userVillageDTO, addressId);

            return ResponseEntity.ok().body(userAddressResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    EResponse.builder()
                            .name("Error")
                            .message(e.getMessage())
                            .build()
            );
        }
    }

    @GetMapping("/get_all_address/{userId}")
    public ResponseEntity<?> getUserAllAddresses(
            @PathVariable("userId") long userId
    ) {
        try {
            ownerService.checkValidUser(userId);
            UserAddressListResponse userAddressListResponse =  userVillageService.getAllUserAddresses(userId);
            return ResponseEntity.ok().body(userAddressListResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build()
            );
        }

    }

    @GetMapping("/get_all_provinces")
    public ResponseEntity<?> getAllProvinces() {
        List<ProvinceResponse> provinceResponseList = userVillageService.getAllProvinces();
        return ResponseEntity.ok(provinceResponseList);
    }

    @GetMapping("/get_all_districts/{provinceId}")
    public ResponseEntity<?> getAllDistrictByProvinceId(
        @PathVariable("provinceId") long provinceId
    ) {
        List<DistrictResponse> districtResponses = userVillageService.getDistrictByProvinceId(provinceId);

        return ResponseEntity.ok(districtResponses);
    }

    @GetMapping("/get_all_villages/{districtId}")
    public ResponseEntity<?> getAllVillageByDistrictId(
            @PathVariable("districtId") long districtId
    ) {

        List<VillageResponse> villageResponses = userVillageService
                .getVillageByDistrictId(districtId);

        return ResponseEntity.ok(villageResponses);

    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteUserAddress(
        @RequestParam("userAddressId") long userAddressId,
        @RequestParam("userId") long userId
    ) {
        try {
            ownerService.checkValidUser(userId);
            userVillageService.deleteUserAddress(userAddressId);
            return ResponseEntity.ok("");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build()
            );
        }
    }
}
