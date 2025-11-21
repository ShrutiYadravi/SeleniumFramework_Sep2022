package testcases;

import base.BaseClass;
import dataProvider.CustomDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MyInfoPage;

public class MyInfoValidation extends BaseClass {

    @Test(dataProvider = "loginDetails", dataProviderClass = CustomDataProvider.class)
    public void validateMyInfoDetails(String uname, String pass) throws InterruptedException {
        // Step 1: Login
        LoginPage login = new LoginPage(driver);
        login.loginToApplication(uname, pass);

        // Step 2: Go to My Info page
        MyInfoPage myInfo = new MyInfoPage(driver);
        myInfo.clickOnMyInfoMenu();
        Thread.sleep(2000); // use WebDriverWait in real projects

        // Step 3: Fetch actual values
        String fullName = myInfo.getEmployeeFullName();
        String empID = myInfo.getEmployeeID();
        String licenseNo = myInfo.getDriverLicenseNumber();
      //  String expiryDate = myInfo.getLicenseExpiryDate();
        String nationality = myInfo.getNationality();
        String maritalStatus = myInfo.getMaritalStatus();

        // Step 4: Expected values (can later come from Excel)
        String expectedFullName = "Mae";
        String expectedEmpID = "muser";
        String expectedNationality = "Mexican";
        String expectedMaritalStatus = "Single";

        // Step 5: Validations with messages
        if (fullName.equals(expectedFullName)) {
            System.out.println("✅ Full name is matching: " + fullName);
        } else {
            System.out.println("❌ Full name mismatch! Expected: " + expectedFullName + " | Actual: " + fullName);
            Assert.fail("Full name mismatch!");
        }

        if (empID.equals(expectedEmpID)) {
            System.out.println("✅ Employee ID is matching: " + empID);
        } else {
            System.out.println("❌ Employee ID mismatch! Expected: " + expectedEmpID + " | Actual: " + empID);
            Assert.fail("Employee ID mismatch!");
        }

        if (licenseNo != null && !licenseNo.isEmpty()) {
            System.out.println("✅ Driver License Number is present: " + licenseNo);
        } else {
            System.out.println("❌ Driver License Number is missing!");
            Assert.fail("License number missing!");
        }

//        if (expiryDate.matches("\\d{2}-\\d{2}-\\d{4}")) {
//            System.out.println("✅ License Expiry Date format is valid: " + expiryDate);
//        } else {
//            System.out.println("❌ License Expiry Date format invalid: " + expiryDate);
//            Assert.fail("Invalid expiry date format!");
//        }

        if (nationality.equals(expectedNationality)) {
            System.out.println("✅ Nationality is matching: " + nationality);
        } else {
            System.out.println("❌ Nationality mismatch! Expected: " + expectedNationality + " | Actual: " + nationality);
            Assert.fail("Nationality mismatch!");
        }

        if (maritalStatus.equals(expectedMaritalStatus)) {
            System.out.println("✅ Marital Status is matching: " + maritalStatus);
        } else {
            System.out.println("❌ Marital Status mismatch! Expected: " + expectedMaritalStatus + " | Actual: " + maritalStatus);
            Assert.fail("Marital status mismatch!");
        }
    }
}
