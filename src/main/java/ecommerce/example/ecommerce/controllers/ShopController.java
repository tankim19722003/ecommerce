package ecommerce.example.ecommerce.controllers;

import ecommerce.example.ecommerce.dtos.ShopDTO;
import ecommerce.example.ecommerce.models.Status;
import ecommerce.example.ecommerce.responses.EResponse;
import ecommerce.example.ecommerce.responses.ShopResponse;
import ecommerce.example.ecommerce.services.Impl.OwnerService;
import ecommerce.example.ecommerce.services.ShopService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private OwnerService ownerService;


    @PostMapping(value = "/register/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> register(
            @Valid @ModelAttribute ShopDTO shopDTO,
            @PathVariable("userId") long userId,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();;
            return ResponseEntity.badRequest().body(errorMessages);
        }

        try {
            ownerService.checkValidUser(userId);
            ShopResponse shopResponse = shopService.registerShop(shopDTO, userId);
            return ResponseEntity.ok(shopResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    EResponse.builder()
                            .name("Error")
                            .message(e.getMessage())
                            .build()
            );
        }
    }

    @GetMapping("/{userId}")
    public  ResponseEntity<?> getShopInfo(
            @PathVariable("userId") long userId
    ) {
        try {
            ownerService.checkValidUser(userId);
            ShopResponse shopResponse = shopService.getShopInfo(userId);
            return ResponseEntity.ok(shopResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    EResponse.builder()
                            .name("Error")
                            .message(e.getMessage())
                            .build()
            );
        }
    }

    @PutMapping("/update_shop/{userId}")
    public ResponseEntity<?> updateShopInfo(
            @PathVariable("userId") long userId,
            @RequestBody ShopDTO shopDTO,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();;
            return ResponseEntity.badRequest().body(errorMessages);
        }

        try {
            ownerService.checkValidUser(userId);
            ShopResponse shopResponse = shopService.updateShopInfo(shopDTO, userId);
            return ResponseEntity.ok().body(shopResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    EResponse.builder()
                            .name("Error")
                            .message(e.getMessage())
                            .build()
            );
        }
    }

    @GetMapping("/get_all_pending_shops")
    public List<ShopResponse> getAllPendingShops() {
//        Status status = Status.PENDING;
//        return shopService.getShopsStatus(status.getStatus());
        return null;
    }
}
