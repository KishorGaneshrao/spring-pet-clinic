package tech.ark.petclinicweb.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tech.ark.petclinicdata.map.OwnerServiceMap;
import tech.ark.petclinicdata.map.VetServiceMap;
import tech.ark.petclinicdata.model.Owner;
import tech.ark.petclinicdata.model.Vet;
import tech.ark.petclinicdata.services.OwnerService;
import tech.ark.petclinicdata.services.VetService;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader() {
        ownerService = new OwnerServiceMap();
        vetService = new VetServiceMap();
    }

    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");

        Owner owner2 = new Owner();
        owner1.setId(2L);
        owner1.setFirstName("Fiona");
        owner1.setLastName("Glenanne");

        System.out.println("Loading owners...");
        ownerService.save(owner1);
        ownerService.save(owner2);

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");

        Vet vet2 = new Vet();
        vet1.setId(2L);
        vet1.setFirstName("Jessie");
        vet1.setLastName("Porter");

        System.out.println("Loading Vets...");
        vetService.save(vet1);
        vetService.save(vet2);
    }
}
