package pl.krzysiek.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.krzysiek.domain.AllegroResponseApi;
import pl.krzysiek.repository.AllegroResponseApiRepository;

import javax.xml.ws.Action;
import java.util.List;

@Service
public class AllegroServices {

    @Autowired
    AllegroResponseApiRepository allegroResponseApiRepository;

    public List<AllegroResponseApi> allegroResponseApis(){
        return (List<AllegroResponseApi>) allegroResponseApiRepository.findAll();
    }

    public AllegroResponseApi save(AllegroResponseApi allegroResponseApi){
        return allegroResponseApiRepository.save(allegroResponseApi);
    }

}
