package de.czertbytes.schwarzekatze.core.domain.user;

import de.czertbytes.schwarzekatze.core.domain.State;
import de.czertbytes.schwarzekatze.core.domain.shelter.Shelter;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name = "`user`")
public class User {

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

    @Column(nullable = false, length = 128)
    private String username;

    private String email;

    @OneToOne
    private Shelter shelter;

    public User() {
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Shelter getShelter() {
        return shelter;
    }

    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
    }
}
