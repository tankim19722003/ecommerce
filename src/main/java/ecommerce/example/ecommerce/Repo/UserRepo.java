package ecommerce.example.ecommerce.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ecommerce.example.ecommerce.models.User;

public interface UserRepo extends JpaRepository<User, Long> {
}
