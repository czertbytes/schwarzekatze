package de.czertbytes.schwarzekatze.api.domain.parameters;

import de.czertbytes.schwarzekatze.core.domain.State;
import de.czertbytes.schwarzekatze.core.domain.action.Type;
import de.czertbytes.schwarzekatze.core.domain.pet.Animal;
import de.czertbytes.schwarzekatze.core.domain.pet.Breed;
import de.czertbytes.schwarzekatze.core.domain.pet.Gender;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

public class PetSearchParameters {

    private List<State> state;              // a
    private List<Type> type;                // a
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private DateTime from;                  // a
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private DateTime to;                    // a
    private List<Animal> animal;            // p
    private List<Breed> breed;              // p
    private List<Gender> gender;            // p
    private String location;                // a

    public List<State> getState() {
        return state;
    }

    public void setState(List<State> state) {
        this.state = state;
    }

    public List<Type> getType() {
        return type;
    }

    public void setType(List<Type> type) {
        this.type = type;
    }

    public DateTime getFrom() {
        return from;
    }

    public void setFrom(DateTime from) {
        this.from = from;
    }

    public DateTime getTo() {
        return to;
    }

    public void setTo(DateTime to) {
        this.to = to;
    }

    public List<Animal> getAnimal() {
        return animal;
    }

    public void setAnimal(List<Animal> animal) {
        this.animal = animal;
    }

    public List<Breed> getBreed() {
        return breed;
    }

    public void setBreed(List<Breed> breed) {
        this.breed = breed;
    }

    public List<Gender> getGender() {
        return gender;
    }

    public void setGender(List<Gender> gender) {
        this.gender = gender;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
