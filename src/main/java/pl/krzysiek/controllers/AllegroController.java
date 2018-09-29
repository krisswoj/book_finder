package pl.krzysiek.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.krzysiek.services.AllegroServices;

@Controller
public class AllegroController {

    @Autowired
    private AllegroServices allegroServices;

}
