package sheridan.romeroad.petdata.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class PetForm implements Serializable {

    @NotBlank
    @Size(max = 30)
    @Pattern(regexp = "[A-Za-z]*")
    @NotBlank
    @Pattern(regexp = "(Dog|Cat|Rabbit)?")

    private String petName = "";

    private String petGender = "";

    private String petType = "";

    private boolean petVaccination = false;

    private int id = 0;

    public PetForm(){}

    //ID
    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id = id;
    }
    //Pet Name
    public String getPetName(){
        return petName;
    }
    public void setPetName(String petName){
        this.petName = petName;
    }
    //Pet Type
    public String getPetType(){
        return petType;
    }
    public void setPetType(String petType){
        this.petType = petType;
    }
    //Pet Gender
    public String getPetGender(){
        return petGender;
    }
    public void setPetGender(String petGender){
        this.petGender = petGender;
    }
    //Pet Vaccination
    public boolean isPetVaccinated(){
        return petVaccination;
    }
    public void setPetVaccination(boolean petVaccination){
        this.petVaccination = petVaccination;
    }

}
