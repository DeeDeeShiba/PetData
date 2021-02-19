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
        pet
    }

}
