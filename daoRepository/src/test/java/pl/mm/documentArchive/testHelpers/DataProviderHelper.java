package pl.mm.documentArchive.testHelpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Mariusz Marciniuk
 */
@SuppressWarnings("unused")
public class DataProviderHelper {

	/**
	 * This method converting List of elements into two-dimensional array of
	 * Objects.
	 * 
	 * @param elements List of elements
	 * @return Two-dimensional array of Objects
	 */
	public static <T> Object[][] convertToObject(List<T> elements) {
		if (elements != null) {
			Object[][] result = new Object[elements.size()][1];
			for (int i = 0; i < elements.size(); i++) {
				result[i][0] = new Object();
				result[i][0] = elements.get(i);
			}
			return result;
		} else {
			return new Object[0][];
		}
	}

	/**
	 * This method converting Set of elements into two-dimensional array of Objects.
	 * 
	 * @param elements Set of elements
	 * @return Two-dimensional array of Objects
	 */
	public static <T> Object[][] convertToObject(Set<T> elements) {
		List<T> helper = null;
		if (elements != null) {
			helper = new ArrayList<>(elements);
		}
		return DataProviderHelper.convertToObject(helper);
	}

}
