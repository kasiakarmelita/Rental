package pl.Karmelita.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.Karmelita.database.SessionProvider;
import pl.Karmelita.model.Car;
import pl.Karmelita.model.CarCondition;


public class CarDao extends AbstractDao<Car> {
    public CarDao() {
        super(Car.class);
    }

    public Car findCarByRegNumber(String regNumber) {
        Session session = SessionProvider.getSession();
        Transaction transaction = session.beginTransaction();

        Car car = session.find(Car.class, regNumber);
        {
            transaction.commit();
            session.close();
            return car;
        }


    }

    public void removeCarByRegNumber(String regNumber) {
        Session session = SessionProvider.getSession();
        Transaction transaction = session.beginTransaction();
        CarDao carDao = new CarDao();
        Car car = carDao.findCarByRegNumber(regNumber);
        if (car != null) {
            session.remove(car);
            System.out.println("Rekord został usunięty");
            transaction.commit();

        } else {
            System.out.println("Nie istnieje samochód o numerze rejestracyjnym: " + regNumber);
        }

        session.close();

    }

    public void updateCarCondition(String regNumber, boolean isDamaged) {
        Session session = SessionProvider.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            CarDao carDao = new CarDao();
            Car car = carDao.findCarByRegNumber(regNumber);
            if (isDamaged) {
                car.setCarCondition(CarCondition.DAMAGED);
            } else {
                car.setCarCondition(CarCondition.EFFICIENT);
            }
            session.update(car);
        } catch (IllegalArgumentException e) {
            System.err.println("Nie istnieje samochód o numerze rejestracyjnym: " + regNumber);
        }

        transaction.commit();
        session.close();
    }



}





