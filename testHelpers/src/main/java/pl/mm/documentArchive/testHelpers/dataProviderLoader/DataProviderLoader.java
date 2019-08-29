package pl.mm.documentArchive.testHelpers.dataProviderLoader;

public interface DataProviderLoader<T> {

	Object[][] loadTestData() throws Exception;

	T mapInputData(Object obj) throws Exception;

}
