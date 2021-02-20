package sheridan.romeroad.petdata.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sheridan.romeroad.petdata.data.PetDataService;

@Controller
public class PetDataController {

    private final Logger logger = LoggerFactory.getLogger(PetDataController.class);

    private static final String[] petType = {
            "--- Select Your Pet Type ---",
            "Dog", "Cat", "Rabbit"};

    private final PetDataService petDataService;


    public PetDataController(PetDataService petDataService) {
        this.petDataService = petDataService;
    }

    //@RequestMapping(value = {"/", "/Index"})
}
