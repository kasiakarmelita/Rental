package pl.Karmelita;

import pl.Karmelita.dao.CarDao;
import pl.Karmelita.dao.CustomerDao;
import pl.Karmelita.dao.RentalDao;
import pl.Karmelita.model.Car;
import pl.Karmelita.model.CarCondition;
import pl.Karmelita.utils.ScannerUtils;

import java.math.BigDecimal;

public class App {
    public static void main(String[] args) {

        CustomerDao customerDao = new CustomerDao();
        CarDao carDao = new CarDao();
        RentalDao rentalDao = new RentalDao();


        int command = ScannerUtils.getIntFromScanner("Podaj numer operacji, którą chesz wykonać:\n" +
                "1 - dodanie nowego samochodu\n" +
                "2 - usunięcie danego samochodu\n" +
                "3 - aktualizacja stanu samochodu \n" +
                "4 - dodanie nowego klienta\n" +
                "5 - usunięcie klienta\n" +
                "6 - dodanie nowego wypożyczenia\n" +
                "7 - aktualizacja terminu zwrotu\n" +
                "8 - zwrot auta\n" +
                "9 - wyświetlenie trwajacych wypożyczeń\n" +
                "0 - wyświetlenie trajacych wypożyczeń dla danego klienta");

        String regNumber;
        switch (command) {
            case 1:
                regNumber = ScannerUtils.getStringFromScanner("Podaj numer rejestracyjny samochodu");
                String mark = ScannerUtils.getStringFromScanner("Podaj markę samochodu");
                String model = ScannerUtils.getStringFromScanner("Podaj model samochodu");
                String colour = ScannerUtils.getStringFromScanner("Podaj kolor samochodu");
                int condition = ScannerUtils.getIntFromScanner("Podaj stan samochodu: 0 - uszkodzony, 1 - sprawny");
                BigDecimal pricePerDayOfRent = ScannerUtils.getBigDecimalFromScanner("Podaj cenę za dzień wypożyczenia");
                CarCondition carCondition;

                if (condition == 0) {
                    carCondition = CarCondition.DAMAGED;
                } else {
                    carCondition = CarCondition.EFFICIENT;
                }


                Car car1 = new Car(regNumber,
                        mark,
                        model,
                        colour,
                        carCondition,
                        pricePerDayOfRent);

                carDao.add(car1);

                break;
            case 2:
                regNumber = ScannerUtils.getStringFromScanner("Podaj numer rejestracyjny samochodu, który chcesz usunąć");
                String verification = ScannerUtils.getStringFromScanner("Czy chcesz usunąć samochód o numerze rejestacyjnym: " +
                        regNumber + "? T - tak, N - nie");

                if (verification.compareTo("T") == 0) {
                    carDao.removeCarByRegNumber(regNumber);

                } else {
                    System.out.println("Operacja anulowana");
                }
                break;
            case 3:
//                regNumber = ScannerUtils.getStringFromScanner("Podaj numer rejestracyjny samochodu, który stan chcesz zaktualizować");
//                boolean isDamaged = (ScannerUtils.getBooleanFromScanner("1 - samochód sprawny, 0 - samochód uszkodzony"));
//
//                carDao.updateCarCondition(regNumber,isDamaged);
                break;
            case 4:

                break;
            case 5:

                break;
            case 6:

                break;
            case 7:

                break;
            case 8:

                break;
            case 9:

                break;
            case 0:

                break;

            default:
                System.out.println("Podano błędny numer operacji");

        }


//        Customer customer1 = new Customer("Jan", "Kowalski");
//        customerDao.add(customer1);
//
//
//        Customer customer2 = new Customer("Jerzy", "Nowak");
//        customerDao.add(customer2);
//
//        System.out.println(customerDao.findById(2));
//
//
//
//
//        Car car1 = new Car("DW3GU69",
//                "Toyota",
//                "Avensis",
//                "black",
//                CarCondition.EFFICIENT,
//                new BigDecimal(150));
//
//        carDao.add(car1);
//
//        Car car2 = new Car("DW3GU67",
//                "Toyota",
//                "Corolla",
//                "blue",
//                CarCondition.DAMAGED,
//                new BigDecimal(150));
//
//        carDao.add(car2);

//
//
//
//        Rental rental1 = new Rental(LocalDateTime.of(LocalDate.of(2020, 10, 15), LocalTime.of(00, 01)),
//                3,
//                customerDao.findById(1),
//                carDao.findCarByRegNumber("DW3GU67")
//                );
//
//        rentalDao.add(rental1);
//        rentalDao.updateDateofReturn(1,LocalDateTime.of(LocalDate.of(2020, 10, 17), LocalTime.of(21, 22)) );
//
//        Rental rental2 = new Rental(LocalDateTime.of(LocalDate.of(2020, 10, 15), LocalTime.of(00, 01)),
//                3,
//                customerDao.findById(2),
//                carDao.findCarByRegNumber("DW3GU67")
//        );
//
//        rentalDao.add(rental2);
////       rentalDao.returnCar(1,LocalDateTime.of(LocalDate.of(2020, 10, 17), LocalTime.of(21, 22)),false);
//
//        System.out.println(rentalDao.findAciveRentalByCarRegNumber("DW3GU69"));
////        System.out.println(rentalDao.findAciveRentalByCarRegNumber("DW3GU69"));
//
////        System.out.println(rentalDao.findAciveRental());
//
//        System.out.println(rentalDao.findActiveRentalByCustomerId(1));


    }
}
