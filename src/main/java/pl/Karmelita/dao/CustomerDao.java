package pl.Karmelita.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.Karmelita.database.SessionProvider;
import pl.Karmelita.model.Customer;

public class CustomerDao extends AbstractDao<Customer> {


    public CustomerDao() {
        super(Customer.class);
    }



}
