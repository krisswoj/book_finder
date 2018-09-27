package pl.krzysiek.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.krzysiek.domain.AllegroResponseApi;

import java.util.List;

@Repository
public interface AllegroResponseApiRepository extends CrudRepository<AllegroResponseApi, Integer> {

}
