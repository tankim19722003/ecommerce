package ecommerce.example.ecommerce.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductDetailResponse {

    // product Basic info done
    @JsonProperty("product_basic_info")
    private ProductBasicInfoResponse productBasicInfo;

    // product category
    @JsonProperty("product_category_responses")
    private ProductCategoryResponse productCategoryResponses;

    //voucher
    @JsonProperty("voucher_responses")
    private List<VoucherResponse> voucherResponse;

    // product images
    @JsonProperty("product_images")
    private List<ImageResponse> productImages;

    // product attribute
    @JsonProperty("product_attribute_value_responses")
    private List<ProductAttributeValueResponse> productAttributeValueResponses;

    // shipping response
    @JsonProperty("product_shipping_type_responses")
    private List<ShippingTypeResponse> shippingTypeResponses;
}
