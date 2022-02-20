package zw.co.santech.springmongo.repositories;

import com.mongodb.client.result.DeleteResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import zw.co.santech.springmongo.models.Customer;

import java.util.List;

/**
 * @author Sanders
 *
 */

@Repository
@AllArgsConstructor
@Slf4j
public class CustomerQueryDao {

    private final MongoTemplate mongoTemplate;

    public boolean save(Customer customer) {
        log.info("Inside save method of query dao class");

        Customer cust = findOne(customer.getId());

        if (cust == null) {
            this.mongoTemplate.save(customer);
            return true;
        }
        return false;
    }

    public List<Customer> findAll() {
        return this.mongoTemplate.findAll(Customer.class);
    }

    public Customer findOne(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return this.mongoTemplate.findOne(query, Customer.class);
    }

    public List<Customer> findByLastNameLike(String lastName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("lastName").regex(lastName, "i"));
        return this.mongoTemplate.find(query, Customer.class);
    }

    public Customer findByEmail(String email) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        return this.mongoTemplate.findOne(query, Customer.class);
    }

    public DeleteResult deleteOne(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return this.mongoTemplate.remove(query, Customer.class);
    }

    public void deleteAll() {
        this.mongoTemplate.dropCollection(Customer.class);
    }
}
