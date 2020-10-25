package pl.Karmelita.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table
public class Rental {

    @Id
    @Column
    @GeneratedValue(generator = "rentalSeq")
    @GenericGenerator(name = "rentalSeq", strategy = "increment")
    private int id;

    @Column (name = "date_of_rent")
    private LocalDateTime dateOfRent;

    @Column (name = "date_of_return")
    private LocalDateTime dateOfReturn;


    @ManyToOne (targetEntity = Customer.class)
    private Customer customer;


    @ManyToOne (targetEntity = Car.class)
    private Car car;

    @Column (name="rental_cost")
    private BigDecimal rentFee;

    @Column (name = "date_of_real_return")
    private LocalDateTime dateOfRealReturn;

    @Transient
    private long numberOfdaysOfRent;

    public Rental() {
    }

    public Rental(LocalDateTime dateOfRent, long numberOfdaysOfRent, Customer customer, Car car) {
        this.dateOfRent = dateOfRent;
        this.dateOfReturn = dateOfRent.plusDays(numberOfdaysOfRent);
        this.customer = customer;
        this.car = car;
        this.dateOfRealReturn = null;
        this.rentFee=BigDecimal.ZERO;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDateOfRent() {
        return dateOfRent;
    }

    public void setDateOfRent(LocalDateTime dateOfRent) {
        this.dateOfRent = dateOfRent;
    }

    public LocalDateTime getDateOfReturn() {
        return dateOfReturn;
    }

    public void setDateOfReturn(LocalDateTime dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public BigDecimal getRentFee() {
        return rentFee;
    }

    public void setRentFee(BigDecimal rentFee) {
        this.rentFee = rentFee;
    }

    public LocalDateTime getDateOfRealReturn() {
        return dateOfRealReturn;
    }

    public void setDateOfRealReturn(LocalDateTime dateOfRealReturn) {
        this.dateOfRealReturn = dateOfRealReturn;
    }

    public long getNumberOfdaysOfRent() {
        return numberOfdaysOfRent;
    }

    public void setNumberOfdaysOfRent(long numberOfdaysOfRent) {
        this.numberOfdaysOfRent = numberOfdaysOfRent;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "id=" + id +
                ", dateOfRent=" + dateOfRent +
                ", dateOfReturn=" + dateOfReturn +
                ", customer=" + customer +
                ", car=" + car +
                ", rentFee=" + rentFee +
                ", dateOfRealReturn=" + dateOfRealReturn +
                ", numberOfdaysOfRent=" + numberOfdaysOfRent +
                '}';
    }
}
