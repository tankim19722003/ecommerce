package ecommerce.example.ecommerce.services;

import jakarta.mail.MessagingException;

public interface ShopCodeService {

    void createAndSendCode(Long userId) throws MessagingException;

    String getCodeByShopId(Long shopId);

}
