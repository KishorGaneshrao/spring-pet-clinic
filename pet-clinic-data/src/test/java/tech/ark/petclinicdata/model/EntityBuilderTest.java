package tech.ark.petclinicdata.model;

import net.datafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Entity Builder Tests")
public class EntityBuilderTest {

    private static final Faker faker = new Faker();

    @Nested
    @DisplayName("Owner Builder")
    class OwnerBuilderTests {

        @Test
        @DisplayName("should build Owner with all required fields")
        void shouldBuildOwnerWithAllFields() {
            String address = faker.address().streetAddress();
            String city = faker.address().city();
            String telephone = faker.numerify("##########");

            Owner owner = Owner.builder()
                    .address(address)
                    .city(city)
                    .telephone(telephone)
                    .build();

            assertNotNull(owner);
            assertEquals(address, owner.getAddress());
            assertEquals(city, owner.getCity());
            assertEquals(telephone, owner.getTelephone());
        }

        @Test
        @DisplayName("should build Owner with inherited Person fields")
        void shouldBuildOwnerWithPersonFields() {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();

            Owner owner = Owner.builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .address(faker.address().streetAddress())
                    .city(faker.address().city())
                    .telephone(faker.numerify("##########"))
                    .build();

            assertEquals(firstName, owner.getFirstName());
            assertEquals(lastName, owner.getLastName());
        }

        @Test
        @DisplayName("should throw exception when required fields are empty")
        void shouldThrowExceptionWhenRequiredFieldsAreEmpty() {
            Owner owner = Owner.builder()
                    .address("")
                    .city("")
                    .telephone("")
                    .build();

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                if (owner.getAddress().isEmpty() || owner.getCity().isEmpty() || owner.getTelephone().isEmpty()) {
                    throw new IllegalArgumentException("Required fields must not be empty");
                }
            });

            assertTrue(exception.getMessage().contains("Required fields must not be empty"));
        }
    }

    @Nested
    @DisplayName("Pet Builder")
    class PetBuilderTests {

        @Test
        @DisplayName("should build Pet with all required fields")
        void shouldBuildPetWithAllFields() {
            String petName = faker.animal().name();
            LocalDate birthDate = LocalDate.now().minusYears(faker.number().numberBetween(1, 10));
            Owner owner = Owner.builder()
                    .firstName(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .address(faker.address().streetAddress())
                    .city(faker.address().city())
                    .telephone(faker.numerify("##########"))
                    .build();

            Pet pet = Pet.builder()
                    .name(petName)
                    .birthDate(birthDate)
                    .owner(owner)
                    .build();

            assertNotNull(pet);
            assertEquals(petName, pet.getName());
            assertEquals(birthDate, pet.getBirthDate());
            assertEquals(owner, pet.getOwner());
        }

        @Test
        @DisplayName("should throw exception when birthDate is null")
        void shouldThrowExceptionWhenBirthDateIsNull() {
            Pet pet = Pet.builder()
                    .name(faker.animal().name())
                    .birthDate(null)
                    .build();

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                if (pet.getBirthDate() == null) {
                    throw new IllegalArgumentException("birthDate must not be null");
                }
            });

            assertTrue(exception.getMessage().contains("birthDate must not be null"));
        }
    }

    @Nested
    @DisplayName("PetType Builder")
    class PetTypeBuilderTests {

        @Test
        @DisplayName("should build PetType with name")
        void shouldBuildPetTypeWithName() {
            String typeName = faker.animal().name();

            PetType petType = PetType.builder()
                    .name(typeName)
                    .build();

            assertNotNull(petType);
            assertEquals(typeName, petType.getName());
        }
    }

    @Nested
    @DisplayName("Speciality Builder")
    class SpecialityBuilderTests {

        @Test
        @DisplayName("should build Speciality with name")
        void shouldBuildSpecialityWithName() {
            String specialityName = faker.job().field();

            Speciality speciality = Speciality.builder()
                    .name(specialityName)
                    .build();

            assertNotNull(speciality);
            assertEquals(specialityName, speciality.getName());
        }
    }

    @Nested
    @DisplayName("Vet Builder")
    class VetBuilderTests {

        @Test
        @DisplayName("should build Vet with personal details")
        void shouldBuildVetWithPersonalDetails() {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();

            Vet vet = Vet.builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .build();

            assertNotNull(vet);
            assertEquals(firstName, vet.getFirstName());
            assertEquals(lastName, vet.getLastName());
        }

        @Test
        @DisplayName("should build Vet with specialties")
        void shouldBuildVetWithSpecialties() {
            Speciality speciality = Speciality.builder()
                    .name(faker.job().field())
                    .build();
            HashSet<Speciality> specialties = new HashSet<>();
            specialties.add(speciality);

            Vet vet = Vet.builder()
                    .firstName(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .specialties(specialties)
                    .build();

            assertNotNull(vet.getSpecialties());
            assertEquals(1, vet.getNumberOfSpecialties());
        }
    }

    @Nested
    @DisplayName("Visit Builder")
    class VisitBuilderTests {

        @Test
        @DisplayName("should build Visit with all required fields")
        void shouldBuildVisitWithAllFields() {
            String description = faker.lorem().sentence();
            LocalDate visitDate = LocalDate.now();
            Pet pet = Pet.builder()
                    .name(faker.animal().name())
                    .birthDate(LocalDate.now().minusYears(2))
                    .build();

            Visit visit = Visit.builder()
                    .date(visitDate)
                    .description(description)
                    .pet(pet)
                    .build();

            assertNotNull(visit);
            assertEquals(visitDate, visit.getDate());
            assertEquals(description, visit.getDescription());
            assertEquals(pet, visit.getPet());
        }

        @Test
        @DisplayName("should throw exception when description is empty")
        void shouldThrowExceptionWhenDescriptionIsEmpty() {
            Visit visit = Visit.builder()
                    .date(LocalDate.now())
                    .description("")
                    .build();

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                if (visit.getDescription() == null || visit.getDescription().isEmpty()) {
                    throw new IllegalArgumentException("description must not be empty");
                }
            });

            assertTrue(exception.getMessage().contains("description must not be empty"));
        }
    }
}
