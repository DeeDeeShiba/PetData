package sheridan.romeroad.petdata.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class PetForm implements Serializable {

    @NotBlank
    @Size(max = 30)
    @Pattern(regexp = "[A-Za-z]*")
    private String petName = "";

    @NotBlank
    @Pattern(regexp = "(Dog|Cat|Rabbit)?")
    private String petType = "";

    private boolean petGender = false;

    private boolean petVaccination = false;

    public PetForm(){

    }

}
