package ecommerce.example.ecommerce.services;

import ecommerce.example.ecommerce.dtos.ShopDTO;

public interface ShopService {

    void registerShop(ShopDTO shopDTO);

    void getShopInfo(ShopDTO shopDTO, Long shopId);

    void updateShopInfo(ShopDTO shopDTO, long shopId);

}
