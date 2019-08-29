package pl.mm.documentArchive.daoRepository.dataProvider;

import org.testng.annotations.DataProvider;
import pl.mm.documentArchive.testHelpers.dataProviderLoader.DataProviderLoaderFromXlsUser;

public class UserTestDataProvider {

	public static final String USERS_DATA_PROVIDER_NAME = "usersDataProvider";

	@DataProvider(name = USERS_DATA_PROVIDER_NAME, parallel = true)
	public static Object[][] usersDataProvider() throws Exception {
		DataProviderLoaderFromXlsUser loaderFromXlsUser = new DataProviderLoaderFromXlsUser("/testData/testData.xlsx", "usersList");
		return loaderFromXlsUser.loadTestData();
	}

}
