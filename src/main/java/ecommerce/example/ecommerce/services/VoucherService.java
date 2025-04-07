package ecommerce.example.ecommerce.services;

import ecommerce.example.ecommerce.dtos.VoucherDTO;
import ecommerce.example.ecommerce.responses.VoucherResponse;

import java.util.List;

public interface VoucherService {

    List<VoucherResponse> getVouchersByProductId(Long productId);

    VoucherResponse createVoucher(Long shopId, VoucherDTO voucherDTO);

    void deleteVoucher(Long voucherId, Long shopId);

//    void updateVoucher(VoucherUpdatingDTO voucherUpdatingDTO);

}
