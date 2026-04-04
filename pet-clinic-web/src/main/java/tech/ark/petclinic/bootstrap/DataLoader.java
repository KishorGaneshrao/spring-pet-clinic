package tech.ark.petclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tech.ark.petclinic.model.*;
import tech.ark.petclinic.services.OwnerService;
import tech.ark.petclinic.services.PetTypeService;
import tech.ark.petclinic.services.SpecialityService;
import tech.ark.petclinic.services.VetService;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;

    public DataLoader(
            OwnerService ownerService,
            VetService vetService,
            PetTypeService petTypeService,
            SpecialityService specialityService
    ) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
    }

    @Override
    public void run(String... args) throws Exception {
        int petCount = petTypeService.findAll().size();
        if(petCount == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        radiology.setDescription("Surgery");
        Speciality savedSurgery = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        radiology.setDescription("Dentistry");
        Speciality savedDentistry = specialityService.save(dentistry);

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
        vet1.addSpecialty(savedRadiology);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vet2.addSpecialty(surgery);
        vetService.save(vet2);

        System.out.println("Loading Vets...");
    }
}
