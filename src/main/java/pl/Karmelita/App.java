package pl.Karmelita;

import pl.Karmelita.dao.CarDao;
import pl.Karmelita.dao.CustomerDao;
import pl.Karmelita.dao.RentalDao;
import pl.Karmelita.model.Car;
import pl.Karmelita.model.CarCondition;
import pl.Karmelita.model.Customer;
import pl.Karmelita.model.Rental;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class App {
    public static void main(String[] args) {

        CustomerDao customerDao = new CustomerDao();

        Customer customer1 = new Customer("Jan", "Kowalski");
        customerDao.add(customer1);


        Customer customer2 = new Customer("Jerzy", "Nowak");
        customerDao.add(customer2);

        System.out.println(customerDao.findById(2));


        CarDao carDao = new CarDao();

        Car car1 = new Car("DW3GU69",
                "Toyota",
                "Avensis",
                "black",
                CarCondition.EFFICIENT,
                new BigDecimal(150));

        carDao.add(car1);

        Car car2 = new Car("DW3GU67",
                "Toyota",
                "Avensis",
                "black",
                CarCondition.DAMAGED,
                new BigDecimal(150));

        carDao.add(car2);


        RentalDao rentalDao = new RentalDao();

        Rental rental1 = new Rental(LocalDateTime.of(LocalDate.of(2020, 10, 15), LocalTime.of(00, 01)),
                3,
                customerDao.findById(1),
                carDao.findCarByRegNumber("DW3GU69")
                );

        rentalDao.add(rental1);
        rentalDao.updateDateofReturn(1,LocalDateTime.of(LocalDate.of(2020, 10, 17), LocalTime.of(21, 22)) );

        Rental rental2 = new Rental(LocalDateTime.of(LocalDate.of(2020, 10, 15), LocalTime.of(00, 01)),
                3,
                customerDao.findById(2),
                carDao.findCarByRegNumber("DW3GU69")
        );

        rentalDao.add(rental2);
//       rentalDao.returnCar(1,LocalDateTime.of(LocalDate.of(2020, 10, 17), LocalTime.of(21, 22)),false);

//        System.out.println(rentalDao.findByCarRegNumber("DW3GU69"));
//        System.out.println(rentalDao.findAciveRentalByCarRegNumber("DW3GU69"));

        System.out.println(rentalDao.findAciveRental());



    }
}
