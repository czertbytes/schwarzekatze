package de.czertbytes.schwarzekatze.core.infrastructure;

import de.czertbytes.schwarzekatze.core.domain.State;
import de.czertbytes.schwarzekatze.core.domain.action.Type;
import de.czertbytes.schwarzekatze.core.domain.pet.Animal;
import de.czertbytes.schwarzekatze.core.domain.pet.Gender;
import de.czertbytes.schwarzekatze.core.domain.pet.Pet;
import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long>, JpaSpecificationExecutor<Pet>, PetRepositoryCustom {

    @Query("SELECT a.pet " +
            "FROM Action a JOIN a.user u WHERE " +
            "u.id = :userId")
    Page<Pet> findByUser(@Param("userId") Long userId, Pageable pageable);

    @Query("SELECT a.pet " +
            "FROM Action a JOIN a.pet p WHERE " +
            "a.type IN :types AND " +
            "a.state IN :states AND " +
            "p.animal IN :animals AND " +
            "a.dateTime BETWEEN :dateFrom AND :dateTo")
            //"SUBSTRING(a.location,1,5) IN :locations")
    Page<Pet> findAll(@Param("types") List<Type> types,
                      @Param("states") List<State> states,
                      @Param("dateFrom") DateTime dateFrom,
                      @Param("dateTo") DateTime dateTo,
                      @Param("animals") List<Animal> animals,
                      //@Param("locations") List<String> locations,
                      Pageable pageable);

    @Query("SELECT a.pet " +
            "FROM Action a JOIN a.pet p WHERE " +
            "a.type IN :types AND " +
            "a.state IN :states AND " +
            "a.dateTime BETWEEN :dateFrom AND :dateTo AND " +
            "p.animal IN :animals AND " +
            "p.gender IN :genders")
            //"SUBSTRING(a.location,1,5) IN :locations")
    Page<Pet> findAll(@Param("types") List<Type> types,
                      @Param("states") List<State> states,
                      @Param("dateFrom") DateTime dateFrom,
                      @Param("dateTo") DateTime dateTo,
                      @Param("animals") List<Animal> animals,
                      @Param("gender") List<Gender> genders,
                      //@Param("locations") List<String> locations,
                      Pageable pageable);
}
