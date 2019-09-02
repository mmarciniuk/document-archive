package pl.mm.documentArchive.daoRepository;

import org.slf4j.Logger;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;
import pl.mm.documentArchive.daoRepository.config.JpaTestConfigurationBase;
import pl.mm.documentArchive.testHelpers.baseTest.BaseTest;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = {JpaTestConfigurationBase.class})
@DataJpaTest
@Test(timeOut = 60000)
public abstract class BaseDaoRepositoryTest extends BaseTest {

	public BaseDaoRepositoryTest(Logger logger) {
		super(logger);
	}
}
