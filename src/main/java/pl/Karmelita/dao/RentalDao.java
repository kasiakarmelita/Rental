package pl.Karmelita.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.Karmelita.database.SessionProvider;
import pl.Karmelita.model.Car;
import pl.Karmelita.model.Rental;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class RentalDao extends AbstractDao<Rental> {

    public RentalDao() {
        super(Rental.class);
    }

    @Override
    public void add(Rental record) {
        Session session = SessionProvider.getSession();
        Transaction transaction = session.beginTransaction();
        String carRegNumber = record.getCar().getRegNumber();
        RentalDao rentalDao = new RentalDao();
        if (rentalDao.CarIsNotRented(carRegNumber)) {
            session.save(record);
            transaction.commit();
        } else {
            System.out.println("Samochód o numerze rejestracyjnym: " + carRegNumber + " jest już wypożyczony");
        }

        session.close();
    }


    public void returnCar(int id, LocalDateTime dateOfRealReturn, boolean isDamaged) {
        Session session = SessionProvider.getSession();
        Transaction transaction = session.beginTransaction();
        RentalDao rentalDao = new RentalDao();
        Rental rental = rentalDao.findById(id);
        CarDao carDao = new CarDao();
        Car car = carDao.findCarByRegNumber(rental.getCar().getRegNumber());

        rental.setDateOfRealReturn(dateOfRealReturn);
        System.out.println("Samochód o numerze rejestracyjnym " + car.getRegNumber() + " został zwrócony.");
        System.out.println("Planowany okres wynajmu wynosił: "
                + BigDecimal.valueOf(Duration.between(rental.getDateOfRent(), rental.getDateOfReturn())
                .toDays()).add((BigDecimal.ONE)));

        BigDecimal rentFee = RentFee(isDamaged, rental, dateOfRealReturn);
        rental.setRentFee(rentFee);

        System.out.println("Opłata za wynajem łącznie wynosi: " + rentFee + ".");


        session.update(rental);
        transaction.commit();
        session.close();
    }

    public void updateDateofReturn(int id, LocalDateTime newDateofReturn) {
        Session session = SessionProvider.getSession();
        Transaction transaction = session.beginTransaction();
        RentalDao rentalDao = new RentalDao();
        Rental rental = rentalDao.findById(id);
        rental.setDateOfReturn(newDateofReturn);
        session.update(rental);
        transaction.commit();
        session.close();
    }

    public List<Rental> findRentalByCarRegNumber(String regNumber) {
        Session session = SessionProvider.getSession();
        List<Rental> list = session.createQuery("from Rental where car.regNumber=:regNumber", Rental.class)
                .setParameter("regNumber", regNumber)
                .list();
        session.close();
        return list;

    }

    public List<Rental> findActiveRentalByCustomerId(int id) {
        Session session = SessionProvider.getSession();
        List<Rental> list = session.createQuery("from Rental where customer.id=:id and dateOfRealReturn is null", Rental.class)
                .setParameter("id", id)
                .list();
        session.close();
        return list;

    }

    public List<Rental> findAciveRental() {
        Session session = SessionProvider.getSession();
        List<Rental> list = session.createQuery("from Rental where dateOfRealReturn is null", Rental.class)
                .list();

        session.close();
        return list;

    }


    public Rental findAciveRentalByCarRegNumber(String regNumber) {
        Session session = SessionProvider.getSession();
        Rental rent = session.createQuery("from Rental where car.regNumber=:regNumber and dateOfRealReturn is null", Rental.class)
                .setParameter("regNumber", regNumber).getSingleResult();


        session.close();
        return rent;

    }

    public boolean CarIsNotRented(String regNumber) {
        Session session = SessionProvider.getSession();
        List<Rental> list = session.createQuery("from Rental where car.regNumber=:regNumber and dateOfRealReturn is null", Rental.class)
                .setParameter("regNumber", regNumber)
                .list();
        session.close();
        return list.isEmpty();

    }


    private BigDecimal RentFee(boolean isDamaged, Rental rental, LocalDateTime dateOfRealReturn) {
        LocalDateTime dateOfReturn = rental.getDateOfReturn();

        CarDao carDao = new CarDao();
        Car car = carDao.findCarByRegNumber(rental.getCar().getRegNumber());

        BigDecimal rentFee = new BigDecimal((Duration.between(rental.getDateOfRent(), rental.getDateOfReturn())
                .toDays())).add(BigDecimal.ONE)
                .multiply(car.getPricePerDayOfRent());

        long daysAfterDateOfReturn = Duration.between(dateOfReturn, dateOfRealReturn).toDays();

        if (dateOfReturn.isBefore(dateOfRealReturn)) {
            BigDecimal additionalFeeForReturnAfterReturnDate = car.getPricePerDayOfRent()
                    .multiply(new BigDecimal(2))
                    .multiply(new BigDecimal((1 + ((daysAfterDateOfReturn)))));

            System.out.println("Spóźnienie w oddaniu samochodu: " +
                    (Duration.between(rental.getDateOfReturn(), rental.getDateOfRealReturn()).toDays() + 1)
                    + ". Dodatkowa opłata wynosi: "
                    + additionalFeeForReturnAfterReturnDate);

            rentFee = rentFee.add(additionalFeeForReturnAfterReturnDate);


        } else {
            System.out.println("Samochód zwrócony w terminie.");
        }

        if (isDamaged) {
            BigDecimal additionalFeeForReturnDamegedCar = car.getPricePerDayOfRent()
                    .multiply(BigDecimal.valueOf(10));
            rentFee = rentFee.add(additionalFeeForReturnDamegedCar);
            System.out.println("Oddano uszkodzony samochód. Dodatkowa opłata wynosi: " + additionalFeeForReturnDamegedCar);

        } else {
            System.out.println("Samochód zwrócony nieuszkodzony.");
        }
        return rentFee;
    }


}
