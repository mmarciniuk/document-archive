package pl.mm.documentArchive.testHelpers.dataProviderLoader;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.AbstractResource;
import org.springframework.core.io.ClassPathResource;
import pl.mm.documentArchive.testHelpers.DataProviderHelper;

import java.util.ArrayList;
import java.util.List;

public abstract class DataProviderLoaderFromXls<T> implements DataProviderLoader<T> {
	private String classPathToXls;
	private String sheetName;

	public DataProviderLoaderFromXls(String classPathToXls, String sheetName) {
		this.classPathToXls = classPathToXls;
		this.sheetName = sheetName;
	}

	@Override
	public Object[][] loadTestData() throws Exception {
		AbstractResource resource = new ClassPathResource(classPathToXls);
		XSSFWorkbook workbook = new XSSFWorkbook(resource.getInputStream());
		XSSFSheet sheet = workbook.getSheet(sheetName);

		List<T> dataList = new ArrayList<>();
		for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
			XSSFRow row = sheet.getRow(i);

			T element = mapInputData(row);

			dataList.add(element);
		}
		return DataProviderHelper.convertToObject(dataList);
	}

	@Override
	public abstract T mapInputData(Object obj) throws Exception;

}
