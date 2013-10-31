package de.czertbytes.schwarzekatze.core.domain.flyer;

import de.czertbytes.schwarzekatze.core.domain.State;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name = "flyer")
public class Flyer {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime created;

    @Column
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime modified;

    @Column
    @Enumerated(value = EnumType.STRING)
    private State state;

    public Flyer() {
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
}
