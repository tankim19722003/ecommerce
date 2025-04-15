package ecommerce.example.ecommerce.services;

import ecommerce.example.ecommerce.dtos.UpdatingShippingProviderOrderStatusDTO;
import ecommerce.example.ecommerce.responses.ShippingProviderResponse;

import java.util.List;

public interface ShippingProviderSerivice {
    List<ShippingProviderResponse> getAllShippingProvider();

    void changeOrderStatusByShippingProvider(
            UpdatingShippingProviderOrderStatusDTO updatingShippingProviderOrderStatusDTO
    );
}
