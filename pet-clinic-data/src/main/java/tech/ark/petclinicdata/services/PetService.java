package tech.ark.petclinicdata.services;

import tech.ark.petclinicdata.model.Pet;

import java.util.Set;

public interface PetService {

    Pet findById(Long id);

    Pet save(Pet pet);

    Set<Pet> findAll();

}
