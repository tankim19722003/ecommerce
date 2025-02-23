package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.Village;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VillageRepo extends JpaRepository<Village, Long> {

    @Query("select v from Village v  where v.district.id = :districtId")
    List<Village> findAllVillagesByDistrictId(long districtId);
}
