package dataProvider;

import org.testng.annotations.DataProvider;

public class CustomDataProvider {

	// Data provider for User Login
	@DataProvider(name = "loginDetails")
	public static Object[][] getData() {
		Object[][] arr = ExcelReader.getDataFromSheet("Login");
		return arr;
	}

	@DataProvider(name = "Usercreation")
	public static Object[][] getData11() {
		Object[][] arr = ExcelReader.getDataFromSheet("userdetails");
		return arr;
	}

	@DataProvider(name = "checkuser")
	public static Object[][] getData112() {
		Object[][] arr = ExcelReader.getDataFromSheet("checkuser");
		return arr;
	}

	// ✅ Data provider for Admin page search values
	@DataProvider(name = "AdminSearch")
	public static Object[][] getAdminData() {
		Object[][] arr = ExcelReader.getDataFromSheet("AdminSearch");
		return arr;
	}



}
