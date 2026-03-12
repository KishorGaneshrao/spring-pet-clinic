package tech.ark.petclinicdata.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "pet_types")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class PetType extends NamedEntity {
}
