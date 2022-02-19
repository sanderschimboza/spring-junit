package zw.co.santech.springmongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import zw.co.santech.springmongo.models.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}
