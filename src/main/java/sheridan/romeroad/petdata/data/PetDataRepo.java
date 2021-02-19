package sheridan.romeroad.petdata.data;

import org.springframework.data.jpa.repository.JpaRepository;
import sheridan.romeroad.petdata.model.PetEntity;

public interface PetDataRepo extends JpaRepository<PetEntity, Integer> {
}
