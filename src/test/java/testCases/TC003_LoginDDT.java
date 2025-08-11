package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {

    /**
     * Data Driven Test (DDT) for verifying login functionality.
     * Reads multiple sets of credentials from Excel (via DataProviders).
     * Each row in the Excel sheet is executed as a separate test iteration.
     *
     * @param email - Login email from Excel
     * @param pwd   - Login password from Excel
     * @param exp   - Expected result ("Valid" or "Invalid") from Excel
     */
    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class)
    public void verify_LoginDDT(String email, String pwd, String exp) {

        logger.info("=== Starting TC003: Account Login DDT Test ===");

        try {
            // STEP 1: Navigate to Login Page
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.clickLogin();
            logger.info("Navigated to Login Page.");

            // STEP 2: Enter Login Credentials
            LoginPage lp = new LoginPage(driver);
            lp.setMail(email);
            lp.setPassword(pwd);
            lp.clickLogin();
            logger.info("Login form submitted with credentials: " + email + " / " + pwd);

            // STEP 3: Check if My Account page is displayed after login
            MyAccountPage map = new MyAccountPage(driver);
            boolean targetPage = map.isMyAccountPageExists();

            // STEP 4: Validation based on expected result from Excel
            if (exp.equalsIgnoreCase("Valid")) { // Expected: Login should succeed
                if (targetPage) {
                    map.clickLogout(); // Logout after success to reset state
                    Assert.assertTrue(true); // Pass the test
                    logger.info("✅ Login successful for valid credentials.");
                } else {
                    Assert.assertTrue(false); // Fail the test
                    logger.error("❌ Login failed for valid credentials.");
                }
            }

            if (exp.equalsIgnoreCase("Invalid")) { // Expected: Login should fail
                if (targetPage) {
                    map.clickLogout(); // Logout if unexpectedly logged in
                    Assert.assertTrue(false); // Fail the test
                    logger.error("❌ Login succeeded for invalid credentials.");
                } else {
                    Assert.assertTrue(true); // Pass the test
                    logger.info("✅ Login failed as expected for invalid credentials.");
                }
            }

        } catch (Exception e) {
            // Log error and mark test as failed
            logger.error("❌ Exception occurred during login test: " + e.getMessage(), e);
            Assert.fail("Test failed due to an unexpected error.");
        }

        logger.info("=== TC003: Account Login DDT Test Completed ===");
    }
}
