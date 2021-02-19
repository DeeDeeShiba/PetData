package sheridan.romeroad.petdata.data;

import sheridan.romeroad.petdata.model.PetForm;

import java.util.List;

public interface PetDataService {

    void insertPetForm(PetForm form);

    List<PetForm> getAllPetForms();

    void deleteAllPetForms();

    void deletePetForm(int id);

    PetForm getPetForm(int id);

    void updatePetForm(PetForm form);
}
