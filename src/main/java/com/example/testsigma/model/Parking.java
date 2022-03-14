package com.example.testsigma.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name = "parking")
public class Parking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int number;

    private boolean occupancy;

    public Parking() {
    }

    public Parking(int number, boolean occupancy) {
        this.number = number;
        this.occupancy = occupancy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Parking parking = (Parking) o;
        return id != null && Objects.equals(id, parking.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
