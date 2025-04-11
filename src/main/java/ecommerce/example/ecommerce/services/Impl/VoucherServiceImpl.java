package ecommerce.example.ecommerce.services.Impl;

import ecommerce.example.ecommerce.Repo.ShopRepo;
import ecommerce.example.ecommerce.Repo.VoucherRepo;
import ecommerce.example.ecommerce.dtos.VoucherDTO;
import ecommerce.example.ecommerce.dtos.VoucherUpdatingDTO;
import ecommerce.example.ecommerce.models.Shop;
import ecommerce.example.ecommerce.models.Voucher;
import ecommerce.example.ecommerce.responses.VoucherResponse;
import ecommerce.example.ecommerce.services.VoucherService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class VoucherServiceImpl implements VoucherService {

    @Autowired
    private ShopRepo shopRepo;

    @Autowired
    private VoucherRepo voucherRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<VoucherResponse> getVouchersByShopId(Long shopId) {

        List<Voucher> vouchers = voucherRepo.
                findValidVouchersByShopId(shopId, LocalDateTime.now());

        if (!vouchers.isEmpty())
            return vouchers.stream()
                    .map(voucher -> mapper.map(voucher, VoucherResponse.class))
                    .toList();

        return null;

    }

    @Override
    @Transactional
    public VoucherResponse createVoucher(Long shopId, VoucherDTO voucherDTO) {

        Shop shop = shopRepo.findById(shopId).orElseThrow(
                () ->  new RuntimeException("Shop does not found")
        );

        if (voucherDTO.getStartDate().isAfter(voucherDTO.getEndDate()))
            throw new RuntimeException("Invalid date");

        if (voucherDTO.getDiscountValue() > 100 || voucherDTO.getDiscountValue() < 0) {
            throw new RuntimeException("Discount percent need greater than 0 and less than 100");
        }

        Voucher voucher = Voucher.builder()
                .startDate(voucherDTO.getStartDate())
                .endDate(voucherDTO.getEndDate())
                .description(voucherDTO.getDescription())
                .minimumOrderValue(voucherDTO.getMinimumOrderValue())
                .discountPercent(voucherDTO.getDiscountValue())
                .code(generateCode())
                .shop(shop)
                .build();

        voucherRepo.save(voucher);

        return mapper.map(voucher, VoucherResponse.class);

    }

    private int generateCode() {
        long timestamp = System.currentTimeMillis() % 1000000; // Take last 6 digits
        int randomNum = new Random().nextInt(10); // Add a random digit
        int uniqueNumber = (int) ((timestamp + randomNum) % 900000) + 100000;
        return uniqueNumber;
    }

    @Override
    @Transactional
    public void deleteVoucher(Long voucherId, Long shopId) {

        Voucher voucher = voucherRepo.findById(voucherId).orElseThrow(
                () -> new RuntimeException("Voucher does not found")
        );

        if (!voucher.getShop().getId().equals(shopId))
            throw new RuntimeException("Failed to delete voucher");

        voucherRepo.delete(voucher);

    }

    @Override
    public VoucherResponse updateVoucher(VoucherUpdatingDTO voucherUpdatingDTO) {

        Voucher voucher = voucherRepo.findById(voucherUpdatingDTO.getVoucherId())
                .orElseThrow(() ->  new RuntimeException("Voucher does not found"));

        if (voucher.getShop().getId() != (voucherUpdatingDTO.getShopId()))
            throw new RuntimeException("Failed to update voucher");

        if (voucherUpdatingDTO.getStartDate().isAfter(voucherUpdatingDTO.getEndDate()))
            throw new RuntimeException("Invalid date");

        voucher.setDescription(voucherUpdatingDTO.getDescription());
        voucher.setEndDate(voucherUpdatingDTO.getEndDate());
        voucher.setStartDate(voucherUpdatingDTO.getStartDate());
        voucher.setDiscountPercent(voucherUpdatingDTO.getDiscountValue());
        voucher.setMinimumOrderValue(voucherUpdatingDTO.getMinimumOrderValue());

        voucherRepo.save(voucher);

        return mapper.map(voucher, VoucherResponse.class);
    }


}
