package de.czertbytes.schwarzekatze.core.domain.picture;

import de.czertbytes.schwarzekatze.core.domain.State;
import de.czertbytes.schwarzekatze.core.domain.pet.Pet;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name = "picture")
public class Picture {

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

    @ManyToOne
    private Pet pet;

    @Column(nullable = false)
    private Boolean main;

    @Column(nullable = false)
    private String path;

    @Column(nullable = false)
    private String url;

    public Picture() {
    }

    public Picture(Pet pet, Boolean main, String path, String url) {
        this.pet = pet;
        this.main = main;
        this.path = path;
        this.url = url;
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
        DateTime dateTime = DateTime.now();
        this.created = dateTime;
        this.modified = dateTime;
        waitingForReview();
    }

    @PreUpdate
    void preUpdate() {
        this.modified = DateTime.now();
    }

    public void waitingForReview() {
        state = State.WAITING_FOR_REVIEW;
    }

    public void approve() {
        state = State.APPROVED;
    }

    public void reject() {
        state = State.REJECTED;
    }

    public Boolean getMain() {
        return main;
    }

    public void setMain(Boolean main) {
        this.main = main;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
