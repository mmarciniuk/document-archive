package pl.mm.documentArchive.daoRepository;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import pl.mm.documentArchive.daoRepository.config.JpaTestConfigurationBase;

import java.util.logging.Logger;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = {JpaTestConfigurationBase.class})
@DataJpaTest
@Test(timeOut = 60000)
public abstract class BaseTest extends AbstractTestNGSpringContextTests {

	protected Logger logger;

}
