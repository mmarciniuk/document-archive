package pl.mm.documentArchive.daoRepository.integrationTest;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;
import pl.mm.documentArchive.daoConfig.JpaDefaultConfiguration;
import pl.mm.documentArchive.testHelpers.baseTest.AbstractTestNGSpringContextTestsDocumentArchive;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = {JpaDefaultConfiguration.class})
@DataJpaTest
@Test(timeOut = 60000)
public abstract class BaseDaoRepositoryTest extends AbstractTestNGSpringContextTestsDocumentArchive {

}
