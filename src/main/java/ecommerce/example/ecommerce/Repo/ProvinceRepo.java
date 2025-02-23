package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.District;
import ecommerce.example.ecommerce.models.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProvinceRepo extends JpaRepository<Province, Long> {


}
