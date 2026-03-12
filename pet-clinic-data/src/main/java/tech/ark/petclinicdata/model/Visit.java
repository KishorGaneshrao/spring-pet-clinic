package tech.ark.petclinicdata.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Table(name = "visits")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Visit extends BaseEntity {

    @Column(name = "visit_date", columnDefinition = "DATE")
    private LocalDate date = LocalDate.now();

    @Column(name = "description")
    @NotEmpty
    private String description;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;
}
