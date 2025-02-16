package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.UserBanned;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBannedRepo extends JpaRepository<UserBanned, Long>{
}
