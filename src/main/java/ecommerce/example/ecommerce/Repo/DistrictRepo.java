package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DistrictRepo extends JpaRepository<District, Long> {
    @Query("select d from District d where d.province.id = :id")
    List<District> findAllDistrictsByProvinceId(long id);
}
