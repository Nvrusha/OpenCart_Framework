package testCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

import java.time.Duration;

/**
 * Test Case: TC001_AccountRegistrationTest
 * Description: Validates user registration on TutorialsNinja demo site using random input data.
 * Framework Layer: Test Layer (TestNG + Selenium)
 */
public class TC001_AccountRegistrationTest {

    public WebDriver driver;

    /**
     * Setup: Initializes WebDriver, sets timeouts, and navigates to base URL.
     * This runs once before all test methods in this class.
     */
    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver(); // Launch Chrome browser
        driver.manage().deleteAllCookies(); // Clear any leftover cookies
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Set implicit wait
        driver.get("https://tutorialsninja.com/demo/index.php?route=common/home"); // Navigate to app
        driver.manage().window().maximize(); // Maximize browser window
    }

    /**
     * TearDown: Closes the browser after test execution.
     * Helps free resources and avoid memory leaks.
     */
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Close all browser windows and safely end session
        }
    }

    /**
     * Test: Automates account registration flow and verifies success message.
     * Uses Page Object Model (POM) for better structure and maintainability.
     */
    @Test
    public void verifyAccountRegistration() {

        // Navigate to Registration Page
        HomePage hp = new HomePage(driver);
        hp.clickMyAccount();
        hp.clickRegister();

        // Fill Registration Form with random data
        AccountRegistrationPage regPage = new AccountRegistrationPage(driver);
        regPage.setFirstName(randomString().toUpperCase());       // Random First Name
        regPage.setLastName(randomString().toUpperCase());        // Random Last Name
        regPage.setEmail(randomString() + "@gmail.com");          // Random Email (unique)
        regPage.setTelephone(randomNumber());                     // Random 10-digit Phone Number

        String password = randomAlphaNumeric();                   // Create random password
        regPage.setPassword(password);
        regPage.setConfirmPassword(password);                     // Match password

        regPage.setPrivacyPolicy();                               // Accept Terms
        regPage.clickContinue();                                  // Submit registration

        // Assert success confirmation
        String confirmMsg = regPage.getConfirmationMsg();
        Assert.assertEquals(confirmMsg, "Your Account Has Been Created!"); // Expected outcome
    }

    // ===== Utility Methods to Generate Random Data =====

    /**
     * Generates a random alphabetic string of length 5.
     */
    public String randomString() {
        return RandomStringUtils.randomAlphabetic(5);
    }

    /**
     * Generates a random 10-digit numeric string (simulating a phone number).
     * Although it's numeric in nature, it's returned as a String to handle leading 0s and avoid int limits.
     */
    public String randomNumber() {
        return RandomStringUtils.randomNumeric(10);
    }

    /**
     * Generates a random alphanumeric password (5 letters + 3 numbers).
     */
    public String randomAlphaNumeric() {
        return RandomStringUtils.randomAlphabetic(5) + RandomStringUtils.randomNumeric(3);
    }
}
