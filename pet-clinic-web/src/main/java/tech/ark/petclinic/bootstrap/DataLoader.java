package tech.ark.petclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tech.ark.petclinic.model.Owner;
import tech.ark.petclinic.model.Pet;
import tech.ark.petclinic.model.PetType;
import tech.ark.petclinic.model.Vet;
import tech.ark.petclinic.services.OwnerService;
import tech.ark.petclinic.services.PetTypeService;
import tech.ark.petclinic.services.VetService;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        petTypeService.save(cat);

        System.out.println("Loading petTypes...");

        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("221B Baker Street");
        owner1.setCity("London");
        owner1.setTelephone("5454867544");

        Pet mikesPet = new Pet();
        mikesPet.setType(dog);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setName("Rosco");
        owner1.getPets().add(mikesPet);
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("221B Baker Street");
        owner2.setCity("London");
        owner2.setTelephone("5454867544");

        Pet fionasPet = new Pet();
        fionasPet.setType(cat);
        fionasPet.setOwner(owner2);
        fionasPet.setBirthDate(LocalDate.now());
        fionasPet.setName("Billy");
        owner2.getPets().add(fionasPet);
        ownerService.save(owner2);

        System.out.println("Loading owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vetService.save(vet2);

        System.out.println("Loading Vets...");
    }
}
