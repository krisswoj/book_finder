package pl.krzysiek.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.krzysiek.domain.AllegroToken;

@Repository
public interface AllegroTokenRepository extends CrudRepository<AllegroToken, Integer> {

    AllegroToken findTopByOrderByIdDesc();

}
