package tech.ark.petclinic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "speciality")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Speciality extends NamedEntity {
    @Column(name = "description")
    @NotEmpty
    private String description;
}
