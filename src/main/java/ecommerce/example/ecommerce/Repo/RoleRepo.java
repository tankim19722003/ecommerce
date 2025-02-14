package ecommerce.example.ecommerce.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ecommerce.example.ecommerce.models.Role;
public interface RoleRepo extends JpaRepository<Role,Long> {
}
