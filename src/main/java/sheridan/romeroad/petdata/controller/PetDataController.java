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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sheridan.romeroad.petdata.data.PetDataService;
import sheridan.romeroad.petdata.model.PetForm;

import java.util.List;

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
    @RequestMapping("/ListPet")
    public ModelAndView listPet() {
        logger.trace("listPet() is called");
        List<PetForm> list = petDataService.getAllPetForms();
        return new ModelAndView("ListPet",
                "pet", list);
    }
    @RequestMapping("/DeleteAll")
    public String deleteAll(){
        logger.trace("deleteAll() is called");
        petDataService.deleteAllPetForms();
        return "redirect:ListPet";
    }
    @RequestMapping("PetDetails/{id}")
    public String petDetails(@PathVariable String id, Model model){
        logger.trace("petDetails() is called");
        try {
            PetForm form = petDataService.getPetForm(Integer.parseInt(id));
            if (form != null) {
                model.addAttribute("pet", form);
                return "PetDetails";
            } else {
                logger.trace("no data for this id=" + id);
                return "DataNotFound";
            }
        } catch (NumberFormatException e) {
            logger.trace("the id is missing or not an integer");
            return "DataNotFound";
        }
    }

    @RequestMapping("/DeletePet")
    public String deletePet(@RequestParam String id, Model model) {
        logger.trace("deletePet() is called");
        try {
            PetForm form = petDataService.getPetForm(Integer.parseInt(id));
            if (form != null) {
                model.addAttribute("pet", form);
                return "DeletePet";
            } else {
                return "redirect:ListPet";
            }
        } catch (NumberFormatException e) {
            return "redirect:ListPet";
        }
    }
    @RequestMapping("/RemovePet")
    public String removePet(@RequestParam String id) {
        logger.trace("removePet() is called");
        try {
            petDataService.deletePetForm(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            logger.trace("the id is missing or not an integer");
        }
        return "redirect:ListPet";
    }
    @RequestMapping("/EditPet")
    public String editPet(@RequestParam String id, Model model) {
        logger.trace("editPet() is called");
        try {
            PetForm form = petDataService.getPetForm(Integer.parseInt(id));
            if (form != null) {
                model.addAttribute("form", form);
                model.addAttribute("programs", programs);
                return "EditPet";
            } else {
                logger.trace("no data for this id=" + id);
                return "redirect:ListPet";
            }
        } catch (NumberFormatException e) {
            logger.trace("the id is missing or not an integer");
            return "redirect:ListPet";
        }
    }
    @RequestMapping("/UpdatePet")
    public String updatePet(
            @Validated @ModelAttribute("form") PetForm form,
            BindingResult bindingResult,
            Model model) {
        logger.trace("updatePet() is called");
        // checking for the input validation errors
        if (bindingResult.hasErrors()) {
            logger.trace("input validation errors");
            //model.addAttribute("form", form);
            model.addAttribute("programs", programs);
            return "EditPet";
        } else {
            logger.trace("the user inputs are correct");
            petDataService.updatePetForm(form);
            logger.debug("id = " + form.getId());
            return "redirect:PetDetails/" + form.getId();
        }
    }
}
