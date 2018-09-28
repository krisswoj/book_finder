package pl.krzysiek.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.krzysiek.domain.AllegroResponseToken;

@Repository
public interface AllegroTokenRepository extends CrudRepository<AllegroResponseToken, Integer> {

    AllegroResponseToken findTopByOrderByIdDesc();

}
