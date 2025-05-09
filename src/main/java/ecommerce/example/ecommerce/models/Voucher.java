package ecommerce.example.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import ecommerce.example.ecommerce.responses.VoucherResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "vouchers")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private Integer code;

    @Column(name = "description")
    private String description;

    @Column(name = "discount_percent", nullable = false)
    private Integer discountPercent;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "minimum_order_value", nullable = false)
    private Integer minimumOrderValue;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    @JsonBackReference
    private Shop shop;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "voucher")
    private List<Order> orders;

    public VoucherResponse toVoucherResponse(){
        VoucherResponse voucherResponse = new VoucherResponse();
        voucherResponse.setId(id);
        voucherResponse.setDescription(description);
        voucherResponse.setCode(code);
        voucherResponse.setDiscountPercent(discountPercent);
        voucherResponse.setMinimumOrderValue(minimumOrderValue);
        voucherResponse.setEndDate(endDate);
        voucherResponse.setStartDate(startDate);

        return voucherResponse;
    }
}
