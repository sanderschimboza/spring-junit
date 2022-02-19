package zw.co.santech.springmongo.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.santech.springmongo.models.Customer;
import zw.co.santech.springmongo.services.CustomerService;

@RequestMapping("/vi/api/erase")
@AllArgsConstructor
@RestController
public class ResourcesController {

    private final CustomerService customerService;

    @DeleteMapping
    public ResponseEntity<Object> deleteAll() {
        this.customerService.deleteAll();
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> deleteOne(@PathVariable("id") String id) {
        this.customerService.deleteOne(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
