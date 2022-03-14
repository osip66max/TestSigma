package com.example.testsigma.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
public class TimePrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int time;

    private int price;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "car_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    private Car car;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "parking_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    private Parking parking;

    public TimePrice() {
    }

    public TimePrice(int time, int price, Car car, Parking parking) {
        this.time = time;
        this.price = price;
        this.car = car;
        this.parking = parking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TimePrice timePrice = (TimePrice) o;
        return id != null && Objects.equals(id, timePrice.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
