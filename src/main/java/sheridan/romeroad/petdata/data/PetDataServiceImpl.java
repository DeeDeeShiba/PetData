package sheridan.romeroad.petdata.data;

import org.springframework.stereotype.Service;
import sheridan.romeroad.petdata.model.PetEntity;
import sheridan.romeroad.petdata.model.PetForm;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public abstract class PetDataServiceImpl implements PetDataService{
    private final PetDataRepo petDataRepo;

    protected PetDataServiceImpl(PetDataRepo petDataRepo) {
        this.petDataRepo = petDataRepo;
    }

    private static void copyFormToEntity(PetForm form, PetEntity pet){
        //pet.getId()(form.getId());
        pet.setPetName(form.getPetName());
        pet.setPetType(form.getPetType());
        pet.setPetGender(form.getPetGender());
        pet.setPetVaccination(form.isPetVaccinated());
    }

    private static void copyEntityToForm(PetEntity pet, PetForm form){
        form.setId(pet.getId());
        form.setPetName(pet.getPetName());
        form.setPetType(pet.getPetType());
        form.setPetGender(pet.getPetGender());
        form.setPetVaccination(pet.isPetVaccinated());
    }

    public void insertPetForm(PetForm form){
        PetEntity pet = new PetEntity();
        copyFormToEntity(form, pet);
        pet = petDataRepo.save(pet);
        form.setId(pet.getId());
    }

    public List<PetForm> getAllPetForms(){
        List<PetForm> formList = new ArrayList<>();
        List<PetEntity> petList = petDataRepo.findAll();
        for(PetEntity pet: petList){
            PetForm form = new PetForm();
            copyEntityToForm(pet, form);
            formList.add(form);
        }
        return formList;
    }
    public void deleteAllPetForms(){
        petDataRepo.deleteAll();
    }
    public void deletePetForm(int id){
        petDataRepo.deleteById(id);
    }
    public PetForm getPetForm(int id){
        Optional<PetEntity> result = petDataRepo.findById(id);
        if(result.isPresent()){
            PetForm form = new PetForm();
            PetEntity pet = result.get();
            copyEntityToForm(pet, form);
            return form;
        }
        return null;
    }

    public void updatePetForm(PetForm form){
        Optional<PetEntity> result = petDataRepo.findById(form.getId());
        if(result.isPresent()){
            PetEntity pet = result.get();
            copyFormToEntity(form,pet);
            petDataRepo.save(pet);
            petDataRepo.flush();
        }
    }
}
