package ecommerce.example.ecommerce.services.Impl;

import ecommerce.example.ecommerce.Repo.*;
import ecommerce.example.ecommerce.dtos.CloudinaryService;
import ecommerce.example.ecommerce.dtos.ShopDTO;
import ecommerce.example.ecommerce.models.*;
import ecommerce.example.ecommerce.responses.ShopResponse;
import ecommerce.example.ecommerce.services.ShopService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private VillageRepo villageRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserCodeRepo userCodeRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private ShopRepo shopRepo;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Override
    @Transactional
    public ShopResponse registerShop(ShopDTO shopDTO, long userId) {

        UserCode shopCode = userCodeRepo.findUserCode(1L, shopDTO.getEmail()).orElseThrow(
                () -> new RuntimeException("Please confirm your email!!")
        );


        if (!shopCode.getActive()) {
            throw new RuntimeException("Please confirm your email!!");
        }

        // check existing user
        User user = userRepo.findById(userId).orElseThrow(
                () -> new RuntimeException("User does not found")
        );

        // check address is
        Village village = villageRepo.findById(shopDTO.getVillageId()).orElseThrow(
                () -> new RuntimeException("Address does not found")
        );

        boolean isShopExisting = shopRepo.existsByShopName(shopDTO.getShopName());

        if (isShopExisting) throw new RuntimeException("Shop name is existing");

//        if (!isAllDigits(shopDTO.getCmnd())) throw new RuntimeException("CMND must contain only digits");
        // saving shop
        Shop shop = new Shop();
        modelMapper.map(shopDTO, shop);
        shop.setUser(user);
        shop.setVillage(village);
        Map<String, String> frontCmnd;
        Map<String, String> behindCmnd;

        try {

            // use ai to check is cccd
            frontCmnd = cloudinaryService.uploadImage(shopDTO.getFrontCccd());
            behindCmnd = cloudinaryService.uploadImage(shopDTO.getBehindCccd());
        } catch (IOException e) {
            throw new RuntimeException("Can't save avatar!!");
        }

        shop.setBehindCmndUrl(behindCmnd.get("imageUrl"));
        shop.setBehindCmndPublicId(behindCmnd.get("publicId"));

        shop.setFrontCmndUrl(frontCmnd.get("imageUrl"));
        shop.setFrontCmndPublicId(frontCmnd.get("publicId"));
        shop.setCreatedAt(LocalDate.now());
        shop.setUpdatedAt(LocalDate.now());
        shop.setTotalMoney(0);

        try {
                shopRepo.save(shop);
//            userCodeRepo.deleteById(shopCode.getId());
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Shop is existing");
        }


//        Shop savedShop = shopRepo.findByUserId(userId)
//                .orElseThrow(() -> new RuntimeException("Failed to create!!"));

        // set role shop for user

        Role role = roleRepo.findByName("shop").orElseThrow(
                () -> new RuntimeException("Role does not found")
        );

        user.addRole(role);

        // delete code
        userCodeRepo.deleteByUserId(userId);

        return convertShopToShopResponse(shop);

    }

    public static boolean isAllDigits(String str) {
        return str != null && str.matches("\\d+");
    }

    @Override
    public ShopResponse getShopInfo(long userId) {

        boolean isUserExisting = userRepo.existsById(userId);
        if (!isUserExisting) {
            throw new RuntimeException("User does not found");
        }

        Shop shop = shopRepo.findByUserId(userId).orElseThrow(() ->
                new RuntimeException("Shop does not found"));


        return convertShopToShopResponse(shop);

    }

    @Override
    public ShopResponse updateShopInfo(ShopDTO shopDTO, long userId) {

        boolean isUserExisting = userRepo.existsById(userId);
        if (!isUserExisting) {
            throw new RuntimeException("User does not found");
        }

        Shop shop = shopRepo.findByUserId(userId).orElseThrow(() ->
                new RuntimeException("Shop does not found"));


//        if (shop.getStatus().equals("REJECT")) {
//            throw new RuntimeException("Shop is rejected");
//        }
//
//        if (shop.getStatus().equals("PENDING")) {
//            throw new RuntimeException("Shop is processing");
//        }
//        modelMapper.map(shopDTO, shop);


        Village village = villageRepo.findById(shopDTO.getVillageId()).orElseThrow(
                () ->  new RuntimeException("Address does not found")
        );

        shop.setShopName(shopDTO.getShopName());
        shop.setDescription(shopDTO.getDescription());
        shop.setSpecificAddress(shopDTO.getSpecificAddress());
        shop.setVillage(village);
        shop.setPhoneNumber(shopDTO.getPhoneNumber());
        shop.setEmail(shopDTO.getEmail());

        Shop savedShop = shopRepo.save(shop);


        return convertShopToShopResponse(savedShop);

    }


    @Override
    public List<ShopResponse> getShopsStatus(String status) {

//        List<Shop> shops = shopRepo.findByStatus(status);

//        List<ShopResponse> shopResponses = shops.stream().map(shop -> convertShopToShopResponse(shop)
//        ).toList();
//
//        return shopResponses;
        return null;
    }

    public ShopResponse convertShopToShopResponse(Shop shop) {
        // convert shop to shop response
        ShopResponse shopResponse = new ShopResponse();
        modelMapper.map(shop, shopResponse);
        shopResponse.setAddressResponse(shop.getVillage().toAddressResponse());
        shopResponse.setCreatedAt(shop.getCreatedAt());
        shopResponse.setUpdatedAt(shop.getUpdatedAt());
        shopResponse.setBehindCccdPublicId(shop.getBehindCmndPublicId());
        shopResponse.setBehindCccdUrl(shop.getBehindCmndUrl());
        shopResponse.setFrontCccdPublicId(shop.getFrontCmndPublicId());
        shopResponse.setFrontCccdUrl(shop.getFrontCmndUrl());
        shopResponse.setTotalMoney(shop.getTotalMoney());

        return shopResponse;
    }
}
