package zw.co.santech.springmongo.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.santech.springmongo.models.Customer;
import zw.co.santech.springmongo.services.CustomerService;

import java.util.List;

@RestController
@RequestMapping("vi/api/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> save(@RequestBody Customer customer) {
        if (this.customerService.save(customer)) {
            return ResponseEntity.status(201).body(customer);
        }else {
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Customer>> findAll() {
        return ResponseEntity.status(200).body(this.customerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> findOne(@PathVariable("id") String id) {
        Customer customer = this.customerService.findOne(id);
        if (customer != null) {
            return ResponseEntity.status(200).body(customer);
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/{lastName}")
    public ResponseEntity<List<Customer>> findByLastNameLike(@PathVariable("lastName") String lastName) {
        return ResponseEntity.status(200).body(this.customerService.findByLastNameLike(lastName));
    }

    @GetMapping("/{email}")
    public ResponseEntity<Customer> findByEmail(@PathVariable("email") String email) {
        Customer customer = this.customerService.findByEmail(email);
        if (customer != null) {
            return ResponseEntity.status(200).body(customer);
        }
        return ResponseEntity.status(404).build();
    }
}
