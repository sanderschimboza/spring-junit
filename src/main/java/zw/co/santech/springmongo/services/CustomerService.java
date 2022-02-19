package zw.co.santech.springmongo.services;

import com.mongodb.client.result.DeleteResult;
import zw.co.santech.springmongo.models.Customer;

import java.util.List;

public interface CustomerService {

    boolean save(Customer customer);

    List<Customer> findAll();

    Customer findOne(String id);

    List<Customer> findByLastNameLike(String lastName);

    Customer findByEmail(String email);

    DeleteResult deleteOne(String id);

    void deleteAll();
}
