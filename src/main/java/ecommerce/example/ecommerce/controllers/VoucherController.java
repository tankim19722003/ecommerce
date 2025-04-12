package ecommerce.example.ecommerce.controllers;

import ecommerce.example.ecommerce.dtos.VoucherDTO;
import ecommerce.example.ecommerce.dtos.VoucherUpdatingDTO;
import ecommerce.example.ecommerce.responses.EResponse;
import ecommerce.example.ecommerce.responses.VoucherResponse;
import ecommerce.example.ecommerce.services.Impl.OwnerService;
import ecommerce.example.ecommerce.services.Impl.VoucherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/voucher")
public class VoucherController {

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private VoucherServiceImpl voucherService;

    @PostMapping("/{shopId}")
    public ResponseEntity<?> createVoucher(
            @PathVariable("shopId") Long shopId,
            @RequestBody VoucherDTO voucherDTO
    ) {

        try {
            ownerService.checkValidShop(shopId);
            VoucherResponse voucherResponse = voucherService.createVoucher(shopId, voucherDTO);
            return ResponseEntity.ok(voucherResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build()
            );
        }
    }


    @GetMapping("/get_vouchers/{shopId}")
    public ResponseEntity<?> getVouchers(
            @PathVariable("shopId") Long shopId
    ) {

        try {
            List<VoucherResponse> voucherResponses = voucherService.getVouchersByShopId(shopId);
            return ResponseEntity.ok(voucherResponses);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build()
            );
        }
    }


    @PutMapping("")
    public ResponseEntity<?> updateVoucher(
            @RequestBody VoucherUpdatingDTO voucherUpdatingDTO
    ) {

        try {
            ownerService.checkValidShop(voucherUpdatingDTO.getShopId());
            VoucherResponse voucherResponse = voucherService.updateVoucher(voucherUpdatingDTO);
            return ResponseEntity.ok(voucherResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build()
            );
        }
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteVoucher(
            @RequestParam("shopId") Long shopId,
            @RequestParam("voucherId") Long voucherId
    ) {

        try {
            ownerService.checkValidShop(shopId);
            voucherService.deleteVoucher(voucherId, shopId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build()
            );
        }
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getAllVoucher() {
        try {

            return ResponseEntity.ok(voucherService.getAllVouchers());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build()
            );
        }
    }
}
