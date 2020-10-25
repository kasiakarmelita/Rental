package pl.Karmelita.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;


@Entity
@Table (name = "car")
public class Car {

    @Id
    @Column(name = "reg_number")
    private String regNumber;

    @Column
    private String model;

    @Column
    private String mark;

    @Column
    private String colour;

    @Column (name = "car_condition")
    @Enumerated(EnumType.STRING)
    private CarCondition carCondition;

    @Column (name="price_per_day_of_rent")
    private BigDecimal pricePerDayOfRent;

    public Car() {
    }

    public Car(String regNumber, String model, String mark, String colour, CarCondition carCondition, BigDecimal pricePerDayOfRent) {
        this.regNumber = regNumber;
        this.model = model;
        this.mark = mark;
        this.colour = colour;
        this.carCondition = carCondition;
        this.pricePerDayOfRent = pricePerDayOfRent;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public CarCondition getCarCondition() {
        return carCondition;
    }

    public void setCarCondition(CarCondition carCondition) {
        this.carCondition = carCondition;
    }

    public BigDecimal getPricePerDayOfRent() {
        return pricePerDayOfRent;
    }

    public void setPricePerDayOfRent(BigDecimal pricePerDayOfRent) {
        this.pricePerDayOfRent = pricePerDayOfRent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(regNumber, car.regNumber) &&
                Objects.equals(model, car.model) &&
                Objects.equals(mark, car.mark) &&
                Objects.equals(colour, car.colour) &&
                carCondition == car.carCondition &&
                Objects.equals(pricePerDayOfRent, car.pricePerDayOfRent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regNumber, model, mark, colour, carCondition, pricePerDayOfRent);
    }

    @Override
    public String toString() {
        return "Car{" +
                "regNumber='" + regNumber + '\'' +
                ", model='" + model + '\'' +
                ", mark='" + mark + '\'' +
                ", colour='" + colour + '\'' +
                ", carCondition=" + carCondition +
                ", pricePerDayOfRent=" + pricePerDayOfRent +
                '}';
    }
}

