package ecommerce.example.ecommerce.services;

import ecommerce.example.ecommerce.dtos.VoucherDTO;
import ecommerce.example.ecommerce.dtos.VoucherUpdatingDTO;
import ecommerce.example.ecommerce.responses.Test.TestVoucher;
import ecommerce.example.ecommerce.responses.VoucherResponse;

import java.util.List;

public interface VoucherService {

    List<VoucherResponse> getVouchersByShopId(Long shopId);

    VoucherResponse createVoucher(Long shopId, VoucherDTO voucherDTO);

    void deleteVoucher(Long voucherId, Long shopId);

    VoucherResponse updateVoucher(VoucherUpdatingDTO voucherUpdatingDTO);

    List<TestVoucher> getAllVouchers();

}
