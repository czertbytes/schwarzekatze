package de.czertbytes.schwarzekatze.api.domain;

import de.czertbytes.schwarzekatze.core.domain.pet.Animal;
import de.czertbytes.schwarzekatze.core.domain.pet.Breed;
import de.czertbytes.schwarzekatze.core.domain.pet.Gender;

import java.util.List;

public class Pet {

    private Long id;
    private Animal animal;
    private Breed breed;
    private Gender gender;
    private String name;
    private Integer age;
    private String description;
    private Action action;
    private List<Action> actions;
    private User user;
    private Shelter shelter;

    public Pet() {
    }

    public Pet(Long id, Animal animal, Breed breed, Gender gender, String name, Integer age, String description, Action action) {
        this.id = id;
        this.animal = animal;
        this.breed = breed;
        this.gender = gender;
        this.name = name;
        this.age = age;
        this.description = description;
        this.action = action;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Breed getBreed() {
        return breed;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Shelter getShelter() {
        return shelter;
    }

    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
    }
}
