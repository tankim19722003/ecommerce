package ecommerce.example.ecommerce.controllers;

import ecommerce.example.ecommerce.services.ShopCodeService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop_code")
public class ShopCodeController {

    @Autowired
    private ShopCodeService shopCodeService;

    @GetMapping("/{shopId}")
    public void getShopCodeConConfirmation(
            @PathVariable("shopId") Long shopId
    ) throws MessagingException {
        shopCodeService.createAndSendCode(shopId);
    }

}
