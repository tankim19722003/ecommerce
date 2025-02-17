package ecommerce.example.ecommerce.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ecommerce.example.ecommerce.models.Role;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role,Long> {
    Optional<Role> findByName(String name);
}
