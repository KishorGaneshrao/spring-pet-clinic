package tech.ark.petclinicdata.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "owners")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Owner extends Person {

    @Column(name = "address")
    @NotEmpty
    private String address;

    @Column(name = "city")
    @NotEmpty
    private String city;

    @Column(name = "telephone")
    @NotEmpty
    @Digits(fraction = 0, integer = 10)
    private String telephone;

    @OneToMany(cascade = CascadeType.ALL,  mappedBy = "owner", fetch = FetchType.EAGER)
    private Set<Pet> pets = new HashSet<>();

    public void addPet(Pet pet) {
        if(pets == null) {
            pets = new HashSet<>();
        }
        pets.add(pet);
        pet.setOwner(this);
    }

    public Pet getPet(String name) {
        return pets.stream()
                .filter(pet -> pet.getName().equalsIgnoreCase(name))
                .findAny()
                .orElse(null);
    }

    public Pet getPet(int id) {
        return pets.stream()
                .filter(pet -> pet.getId() != null && pet.getId().equals(id))
                .findAny()
                .orElse(null);
    }

    public void addVisit(Integer petId, Visit visit) {
        Pet pet = getPet(petId);
        if (pet != null) {
            pet.addVisit(visit);
        }
    }

}
