package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepo extends JpaRepository<Wallet, Long> {

    Optional<Wallet> findByUserId(Long userId);
}
