package de.czertbytes.schwarzekatze.core.domain.action;

import de.czertbytes.schwarzekatze.core.domain.State;
import de.czertbytes.schwarzekatze.core.domain.pet.Pet;
import de.czertbytes.schwarzekatze.core.domain.user.User;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name = "action")
public class Action {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @org.hibernate.annotations.Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime created;

    @Column
    @org.hibernate.annotations.Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime modified;

    @Column
    @Enumerated(value = EnumType.STRING)
    private State state;

    @OneToOne
    private Pet pet;

    @Enumerated(value = EnumType.STRING)
    @Column(unique = true)
    private Type type;

    @Column
    @org.hibernate.annotations.Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime dateTime;

    @Column(length = 12)
    private String geohash;

    @Column
    private String description;

    @OneToOne
    private User user;

    public Action() {
    }

    public Action(Pet pet, Type type, DateTime dateTime, String description) {
        this.pet = pet;
        this.type = type;
        this.dateTime = dateTime;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
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

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getGeohash() {
        return geohash;
    }

    public void setGeohash(String geohash) {
        this.geohash = geohash;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
