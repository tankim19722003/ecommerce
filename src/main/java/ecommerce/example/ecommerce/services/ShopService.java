package ecommerce.example.ecommerce.services;

import ecommerce.example.ecommerce.dtos.ShopDTO;
import ecommerce.example.ecommerce.responses.ShopResponse;

public interface ShopService {

    ShopResponse registerShop(ShopDTO shopDTO, long userId);

    ShopResponse getShopInfo(long userId);


    ShopResponse updateShopInfo(ShopDTO shopDTO, long userId);

}
