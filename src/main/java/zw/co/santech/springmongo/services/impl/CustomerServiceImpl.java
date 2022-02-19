package zw.co.santech.springmongo.services.impl;

import com.mongodb.client.result.DeleteResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import zw.co.santech.springmongo.models.Customer;
import zw.co.santech.springmongo.repositories.CustomerQueryDao;
import zw.co.santech.springmongo.services.CustomerService;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerQueryDao queryDao;

    @Override
    public boolean save(Customer customer) {
        return this.queryDao.save(customer);
    }

    @Override
    public List<Customer> findAll() {
        return this.queryDao.findAll();
    }

    @Override
    public Customer findOne(String id) {
        return this.queryDao.findOne(id);
    }

    @Override
    public List<Customer> findByLastNameLike(String lastName) {
        return this.queryDao.findByLastNameLike(lastName);
    }

    @Override
    public Customer findByEmail(String email) {
        return this.queryDao.findByEmail(email);
    }

    @Override
    public DeleteResult deleteOne(String id) {
        return this.queryDao.deleteOne(id);
    }

    @Override
    public void deleteAll() {
        this.queryDao.deleteAll();
    }
}
