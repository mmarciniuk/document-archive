package pl.mm.documentArchive.daoRepository.dataProvider;


import org.testng.annotations.DataProvider;
import pl.mm.documentArchive.testHelpers.dataProviderLoader.DataProviderLoaderFromXlsRole;

public class RoleTestDataProvider {

	public static final String ROLES_DATA_PROVIDER_NAME = "rolesDataProvider";

	@DataProvider(name = ROLES_DATA_PROVIDER_NAME, parallel = true)
	public static Object[][] rolesDataProvider() throws Exception {
		DataProviderLoaderFromXlsRole loaderFromXlsRole = new DataProviderLoaderFromXlsRole("/testData/testData.xlsx", "rolesList");
		return loaderFromXlsRole.loadTestData();
	}

}
