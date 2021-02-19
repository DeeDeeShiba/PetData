package sheridan.romeroad.petdata.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pet")
public class PetEntity implements Serializable{
    @Column(name="Id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "pet_name")
    private String petName = "";

    @Column(name = "pet_type")
    private String petType = "";

    @Column(name = "pet_gender")
    private String petGender = "";

    @Column(name = "pet_vaccination")
    private Boolean petVaccination = false;

    public PetEntity(){};

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

