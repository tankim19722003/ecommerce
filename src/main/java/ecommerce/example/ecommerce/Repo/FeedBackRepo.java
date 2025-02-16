package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedBackRepo extends JpaRepository<Feedback, Long> {
}
