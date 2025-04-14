package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartItemRepo extends JpaRepository<CartItem, Long> {

    @Query("select ci from CartItem ci where ci.user.id = :userId ORDER BY ci.createdAt")
    List<CartItem> findByUserId(Long userId);

    @Query("SELECT count(c) " +
            "FROM CartItem c " +
            "WHERE c.user.id = :userId " +
            "AND c.product.id = :productId " +
            "AND c.productCategory.id = :productCategoryId " +
            "AND c.subProductCategory.id = :subCategoryId")
    int isProductExistingInCart(
            @Param("userId") Long userId,
            @Param("productId") Long productId,
            @Param("productCategoryId") Long productCategoryId,
            @Param("subCategoryId") Long subCategoryId
    );
}
