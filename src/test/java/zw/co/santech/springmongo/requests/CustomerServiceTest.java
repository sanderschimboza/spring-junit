package zw.co.santech.springmongo.requests;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import zw.co.santech.springmongo.models.Address;
import zw.co.santech.springmongo.models.Customer;
import zw.co.santech.springmongo.models.Gender;
import zw.co.santech.springmongo.utils.Order;
import zw.co.santech.springmongo.utils.OrderedTestRunner;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
@RunWith(OrderedTestRunner.class)
public class CustomerServiceTest {

    @ClassRule
    public static final SpringClassRule springClassRule = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void addCustomersTest() throws Exception {

        /**Create Customer with id 101**/

        Address address = new Address("Zim", 200L, "Harare");
        Customer customer = new Customer("101", "Noreen", "Sanders", "nono@santech.co.uk", "0782898667", Gender.FEMALE, address, Arrays.asList("Amazula 2L", "Ice 1KG", "Ice Cream 5L"), BigDecimal.TEN, "2022-02-20");
        String json = new ObjectMapper().writeValueAsString(customer);

        mockMvc.perform
                (post("/vi/api/customers")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                .andExpect(status().isCreated());


        /**Create Customer with id 102**/

        address = new Address("SA", 670L, "Harare");
        customer = new Customer("102", "Kimberly", "Simpson", "kim@santech.co.uk", "0774627559", Gender.FEMALE, address, Arrays.asList("Ciroc 1L", "Ice 1KG", "Ice Cream 5L"), BigDecimal.TEN, "2022-02-19");
        json = new ObjectMapper().writeValueAsString(customer);

        mockMvc.perform
                (post("/vi/api/customers")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                .andExpect(status().isCreated());

    }

    /**
     * @throws Exception It tests creating already existing customer
     */
    @Test
    @Order(2)
    public void addAlreadyExistingCustomerTest() throws Exception {

        /**Create Customer with id 102**/

        Address address = new Address("Zim", 200L, "Harare");
        Customer customer = new Customer("102", "Kimberly", "Simpson", "kim@santech.co.uk", "0774627559", Gender.FEMALE, address, Arrays.asList("Ciroc 1L", "Ice 1KG", "Ice Cream 5L"), BigDecimal.TEN, "2022-02-19");
        String json = new ObjectMapper().writeValueAsString(customer);

        mockMvc.perform
                (post("/vi/api/customers")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(3)
    public void findCustomerById() throws Exception {
        mockMvc.perform(get("/vi/api/customers/101"))
                .andExpect(status().isOk());
    }


    @Test
    @Order(4)
    public void findCustomerByNonExistingId() throws Exception {
        mockMvc.perform(get("/vi/api/customers/105"))
                .andExpect(status().isNotFound());
    }
}
