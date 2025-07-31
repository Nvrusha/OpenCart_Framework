package testCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

/**
 * BaseClass: Common setup, teardown, and utility methods shared across test classes.
 * Follows DRY (Don't Repeat Yourself) principle for cleaner, reusable code.
 */
public class BaseClass {

    public WebDriver driver;

    /**
     * Initializes the Chrome WebDriver and opens the application URL.
     * Runs once before all test methods in the extending class.
     */
    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();                                  // Launch Chrome browser
        driver.manage().deleteAllCookies();                           // Clear any saved cookies
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Implicit wait for web elements
        driver.get("https://tutorialsninja.com/demo/index.php?route=common/home"); // App URL
        driver.manage().window().maximize();                          // Maximize the browser window
    }

    /**
     * Closes the browser session after test execution.
     * Ensures resource cleanup and prevents memory leaks.
     */
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Properly closes browser and ends WebDriver session
        }
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
