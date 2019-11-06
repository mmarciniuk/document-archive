package pl.mm.documentArchive.service.integrationTest;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;
import pl.mm.documentArchive.serviceConfig.ServiceDefaultConfiguration;
import pl.mm.documentArchive.testHelpers.baseTest.AbstractTestNGSpringContextTestsDocumentArchive;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = {ServiceDefaultConfiguration.class})
@DataJpaTest
@Test(timeOut = 60000)
public abstract class BaseServiceTest extends AbstractTestNGSpringContextTestsDocumentArchive {
}
