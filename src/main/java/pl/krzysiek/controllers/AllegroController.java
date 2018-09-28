package pl.krzysiek.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.krzysiek.domain.AllegroResponseToken;
import pl.krzysiek.services.AllegroServices;

import java.io.IOException;

@Controller
public class AllegroController {

    @Autowired
    private AllegroServices allegroServices;

}
