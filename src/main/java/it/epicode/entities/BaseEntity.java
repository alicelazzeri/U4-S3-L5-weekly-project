package it.epicode.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.util.Date;
import java.util.Objects;

@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;

    private Date insertedAt = new Date();

    public BaseEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getInsertedAt() {
        return insertedAt;
    }

    public void setInsertedAt(Date insertedAt) {
        this.insertedAt = insertedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntity that)) return false;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
