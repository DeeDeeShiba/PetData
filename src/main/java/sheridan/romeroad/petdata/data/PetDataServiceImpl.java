package sheridan.romeroad.petdata.data;

import org.springframework.stereotype.Service;
import sheridan.romeroad.petdata.model.PetEntity;
import sheridan.romeroad.petdata.model.PetForm;

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

}
