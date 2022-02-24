package zw.co.santech.springmongo.requests;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;
import zw.co.santech.springmongo.models.Address;
import zw.co.santech.springmongo.models.Customer;
import zw.co.santech.springmongo.models.Gender;
import zw.co.santech.springmongo.utils.Order;
import zw.co.santech.springmongo.utils.OrderedTestRunner;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
@RunWith(OrderedTestRunner.class)
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
public class CustomerServiceTest {

    @ClassRule
    public static final SpringClassRule springClassRule = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    /**
     * @throws Exception It tests add Customer
     */
    @Test
    @Order(1)
    public void addCustomersTest() throws Exception {

        /**Create Customer with id 101**/

        Address address = new Address("Zim", 200L, "Harare");
        Customer customer = new Customer("101", "Nono", "Sanders", "nono@santech.co.uk", "0782898667", Gender.FEMALE, address, Arrays.asList("Amazula 2L", "Ice 1KG", "Ice Cream 5L"), BigDecimal.TEN, "2022-02-20");
        String json = new ObjectMapper().writeValueAsString(customer);

        mockMvc.perform
                (post("/v1/api/customers")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json)).andDo(print())
                .andExpect(status().isCreated());

        /**Create Customer with id 102**/

        address = new Address("Zim", 200L, "Harare");
        customer = new Customer("102", "Tarie", "Sanders", "tarie@santech.co.uk", "0774627559", Gender.FEMALE, address, Arrays.asList("Ciroc 1L", "Ice 1KG", "Ice Cream 5L"), BigDecimal.valueOf(35), "2022-02-22");
        json = new ObjectMapper().writeValueAsString(customer);

        mockMvc.perform
                (post("/v1/api/customers")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json)).andDo(print())
                .andExpect(status().isCreated())
              //  .andExpect(MockMvcResultMatchers.content().json(json))
                .andDo(document("{methodName}",
                        preprocessRequest(prettyPrint())));
    }

    /**
     * @throws Exception It tests creating already existing customer
     */
    @Test
    @Order(2)
    public void addAlreadyExistingCustomerTest() throws Exception {

        Address address = new Address("Zim", 200L, "Harare");
        Customer customer = new Customer("101", "Kimberly", "Simpson", "kim@santech.co.uk", "0774627559", Gender.FEMALE, address, Arrays.asList("Ciroc 1L", "Ice 1KG", "Ice Cream 5L"), BigDecimal.TEN, "2022-02-19");
        String jsonData = new ObjectMapper().writeValueAsString(customer);

        mockMvc.perform
                (post("/v1/api/customers")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(jsonData)).andDo(print())
                .andExpect(status().isBadRequest())
                .andDo(document("{methodName}",
                        preprocessRequest(prettyPrint())));
    }

    /**
     * @throws Exception It tests find customer by id
     */
    @Test
    @Order(3)
    public void findCustomerByIdTest() throws Exception {
        mockMvc.perform(get("/v1/api/customers/101")).andDo(print())
                .andExpect(status().isOk())
                .andDo(document("{methodName}",
                        preprocessRequest(prettyPrint())));
    }

    /**
     * @throws Exception It tests find customer by Non-existing id
     */
    @Test
    @Order(4)
    public void findCustomerByNonExistingIdTest() throws Exception {
        mockMvc.perform(get("/v1/api/customers/105")).andDo(print())
                .andExpect(status().isNotFound())
                .andDo(document("{methodName}",
                        preprocessRequest(prettyPrint())));;
    }

    /**
     * @throws Exception It tests find customer name like
     */
    @Test
    @Order(5)
    public void findByLastNameLikeTest() throws Exception {
        mockMvc.perform(get("/v1/api/customers/users/Sand")).andDo(print())
                .andExpect(status().isOk())
                .andDo(document("{methodName}",
                        preprocessRequest(prettyPrint())));;
    }

    /**
     * @throws Exception It tests find all customers
     */
    @Test
    @Order(6)
    public void findAllCustomerTest() throws Exception {
        mockMvc.perform(get("/v1/api/customers")).andDo(print())
                .andExpect(status().isOk())
                .andDo(document("{methodName}",
                        preprocessRequest(prettyPrint())));;
    }
}
