package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {


    @Query("SELECT u FROM User u WHERE u.phoneNumber = :account OR u.account= :account OR u.email = :account")
    Optional<User> findUserByPhoneNumberOrAccountOrEmail(@Param("account") String account);

    Optional<User> findByPhoneNumber(String phoneNumber);

    Boolean existsByAccount(String account);
    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.account = :account AND u.id <> :id")
    boolean existsByAccountAndDifferentUserId(@Param("account") String email, @Param("id") Long id);


    Boolean existsByEmail(String email);
    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.email = :email AND u.id <> :id")
    boolean existsByEmailAndDifferentUserId(@Param("email") String email, @Param("id") Long id);


    Boolean existsByPhoneNumber(String phoneNumber);

    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.phoneNumber = :phoneNumber AND u.id <> :id")
    boolean existsByPhoneNumberAndDifferentUserId(@Param("phoneNumber") String phoneNumber, @Param("id") Long id);

    Optional<User> findByAccount(String account);

}
