package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.UserCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserCodeRepo extends JpaRepository<UserCode, Long> {
    @Query("SELECT s FROM UserCode s WHERE s.codePurpose.id = :codePurposeId and s.user.id = :userId ORDER BY s.dateStart DESC LIMIT 1")
    Optional<UserCode> findLatestByCodePurposeIdAndUserId(
            @Param("codePurposeId") Long codePurposeId,
            @Param("userId") Long userId
    );
}
