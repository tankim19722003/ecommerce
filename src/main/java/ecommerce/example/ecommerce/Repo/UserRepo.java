package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = :account OR u.phoneNumber = :account OR u.account= :account")
    Optional<User> findUserByEmailOrPhoneNumberOrAccount(@Param("account") String account);

    Optional<User> findByPhoneNumber(String phoneNumber);

    Boolean existsByAccount(String account);

    Boolean existsByEmail(String email);

    Boolean existsByPhoneNumber(String phoneNumber);
}
