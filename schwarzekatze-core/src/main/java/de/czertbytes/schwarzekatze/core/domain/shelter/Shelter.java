package de.czertbytes.schwarzekatze.core.domain.shelter;

import de.czertbytes.schwarzekatze.core.domain.State;
import de.czertbytes.schwarzekatze.core.domain.user.User;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name = "shelter")
public class Shelter {

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
    private String name;

    @Column
    private String homepage;

    @Column(nullable = false)
    private String description;

    @OneToOne
    private User contactPerson;

    public Shelter() {
    }

    public Shelter(String name, String description, User contactPerson) {
        this.name = name;
        this.description = description;
        this.contactPerson = contactPerson;
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
        DateTime dateTime = DateTime.now();
        this.created = dateTime;
        this.modified = dateTime;
        activate();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(User contactPerson) {
        this.contactPerson = contactPerson;
    }
}
