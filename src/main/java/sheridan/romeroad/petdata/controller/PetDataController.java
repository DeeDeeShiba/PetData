package sheridan.romeroad.petdata.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import sheridan.romeroad.petdata.data.PetDataService;
import sheridan.romeroad.petdata.model.PetForm;

@Controller
public class PetDataController {

    private final Logger logger = LoggerFactory.getLogger(PetDataController.class);

    private static final String[] programs = {
            "--- Select Your Pet Type ---",
            "Dog", "Cat", "Rabbit"};

    private final PetDataService petDataService;


    public PetDataController(PetDataService petDataService) {
        this.petDataService = petDataService;
    }

    @RequestMapping(value = {"/", "/Index"})
    public String index(){
        logger.trace("index() is called");
        return "Index";
    }

    @RequestMapping("/AddPet")
    public ModelAndView addPet(){
        logger.trace("addPet()");
        ModelAndView modelAndView  =
                new ModelAndView("AddPet",
                        "form", new PetForm());
        modelAndView.addObject("programs", programs);
        return modelAndView;
    }

    @RequestMapping("/InsertPet")
    public String insertPet(
            @Validated @ModelAttribute("form") PetForm form,BindingResult bindingResult, Model model){
        logger.trace("InsertPet() is called");
        if(bindingResult.hasErrors()){
            model.addAttribute("programs", programs);
            return"AddPet";
        }
        else{
            logger.trace("User Input correct");
            petDataService.insertPetForm(form);
            return "redirect:ConfirmInsert/" +form.getId();
        }
    }
    @RequestMapping("/ConfirmInsert/{id}")
    public String confirmInsert(@PathVariable(name = "id") String strId, Model model){
        logger.trace("confirmInsert() is called");
        try {
            int id = Integer.parseInt(strId);
            logger.trace("looking for the data in the database");
            PetForm form = petDataService.getPetForm(id);
            if (form == null) {
                logger.trace("no data for this id=" + id);
                return "DataNotFound";
            } else {
                logger.trace("showing the data");
                model.addAttribute("pet", form);
                return "ConfirmInsert";
            }
        } catch (NumberFormatException e) {
            logger.trace("the id in not an integer");
            return "DataNotFound";
        }
    }


}
