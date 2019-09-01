package pl.mm.documentArchive.service;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import pl.mm.documentArchive.service.config.ServiceTestConfig;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = {ServiceTestConfig.class})
@DataJpaTest
@Test(timeOut = 60000)
public abstract class BaseServiceTest extends AbstractTestNGSpringContextTests {


}
