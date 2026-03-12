package tech.ark.petclinicdata.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import tech.ark.petclinicdata.util.PropertyComparator;

import java.util.*;

@Entity
@Table(name = "vets")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Vet extends Person {

    @Getter(AccessLevel.NONE)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "vet_specialties",
            joinColumns = @JoinColumn(name = "vet_id"),
            inverseJoinColumns = @JoinColumn(name = "specialty_id")
    )
    private Set<Speciality> specialties = new HashSet<>();

    @JsonIgnore
    protected Set<Speciality> getSpecialtiesInternal() {
        if (this.specialties == null) {
            this.specialties = new HashSet<>();
        }
        return this.specialties;
    }

    @JsonIgnore
    protected void setSpecialtiesInternal(Set<Speciality> specialties) {
        this.specialties = specialties;
    }

    public List<Speciality> getSpecialties() {
        List<Speciality> sortedSpecialties = new ArrayList<>(getSpecialtiesInternal());
        PropertyComparator.sort(sortedSpecialties);
        return Collections.unmodifiableList(sortedSpecialties);
    }

    public void addSpecialty(Speciality specialty) {
        getSpecialtiesInternal().add(specialty);
    }

    public void clearSpecialties() {
        getSpecialtiesInternal().clear();
    }

    public int getNumberOfSpecialties() {
        return getSpecialtiesInternal().size();
    }
}
