package tech.ark.petclinicdata.services;

import tech.ark.petclinicdata.model.Owner;

import java.util.Set;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);
}
