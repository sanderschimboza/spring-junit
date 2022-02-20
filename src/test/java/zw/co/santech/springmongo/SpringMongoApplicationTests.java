package zw.co.santech.springmongo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import zw.co.santech.springmongo.requests.CustomerServiceTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({CustomerServiceTest.class})
@SpringBootTest
class SpringMongoApplicationTests {

}
