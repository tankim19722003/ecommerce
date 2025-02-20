package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.UserVillage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserVillageRepo extends JpaRepository<UserVillage, Long> {

    @Query("select ud from UserVillage ud where ud.user.id = :userId")
    List<UserVillage> getAllUserAddress(@Param("userId") long userId);
}
