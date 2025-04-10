package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.UserVillageOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserVillageOrderRepo extends JpaRepository<UserVillageOrder, Long> {
}
