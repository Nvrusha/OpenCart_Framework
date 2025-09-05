package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

import java.time.Duration;

/**
 * TC002_LoginTest:
 * This test verifies if a user is able to successfully log in using valid credentials.
 *
 * ✅ Steps:
 * - Navigate to login page via homepage
 * - Enter valid email & password from config.properties
 * - Submit the login form
 * - Verify redirection to My Account page
 */
public class TC002_LoginTest extends BaseClass {

    @Test(groups = {"Sanity","Master"})
    public void verifyLogin() {

        logger.info("=== Starting TC002: Account Login Test ===");

        try {
            // Initialize Home Page and navigate to login page
            HomePage hp = new HomePage(driver);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='My Account']")));
            hp.clickMyAccount();
            hp.clickLogin();
            logger.info("Navigated to Login Page.");

            // Fill in login credentials
            LoginPage lp = new LoginPage(driver);
            lp.setMail(p.getProperty("email"));
            lp.setPassword(p.getProperty("password"));
            lp.clickLogin();
            logger.info("Login form submitted with valid credentials.");

            // Check if login is successful
            MyAccountPage map = new MyAccountPage(driver);
            boolean targetPage = map.isMyAccountPageExists();

            // Assert if login was successful
            Assert.assertTrue(targetPage, "❌ Login failed - My Account page not visible.");
            logger.info("✅ Login test passed - My Account page is visible.");

        } catch (Exception e) {
            logger.error("❌ Exception occurred during login test: " + e.getMessage(), e);
            Assert.fail("Test failed due to an unexpected error.");
        }

        logger.info("=== TC002: Account Login Test Completed ===");
    }
}
