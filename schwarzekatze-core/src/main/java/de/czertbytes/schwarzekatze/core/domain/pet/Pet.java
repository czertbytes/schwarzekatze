package de.czertbytes.schwarzekatze.core.domain.pet;

import de.czertbytes.schwarzekatze.core.domain.State;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name = "pet")
public class Pet {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime created;

    @Column
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime modified;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private State state;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Animal animal;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Breed breed;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Column(length = 128)
    private String name;

    @Column
    private Integer age;

    @Column(nullable = false, length = 8192)
    private String description;

    public Pet() {
    }

    public Pet(Animal animal, Gender gender, String description) {
        activate();
        this.animal = animal;
        this.gender = gender;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DateTime getCreated() {
        return created;
    }

    public DateTime getModified() {
        return modified;
    }

    @PrePersist
    void prePersist() {
        activate();
        DateTime dateTime = DateTime.now();
        this.created = dateTime;
        this.modified = dateTime;
    }

    @PreUpdate
    void preUpdate() {
        this.modified = DateTime.now();
    }

    public State getState() {
        return state;
    }

    public void activate() {
        state = State.ACTIVE;
    }

    public void disable() {
        state = State.DISABLED;
    }

    public void delete() {
        state = State.DELETED;
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
}
