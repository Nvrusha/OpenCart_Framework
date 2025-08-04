package testBase;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.time.Duration;

/**
 * BaseClass: Contains setup, teardown, and reusable utility methods.
 * This base class will be extended by all test classes to avoid code duplication.
 *
 * ✅ Includes:
 * - WebDriver initialization and cleanup
 * - Random data generation methods
 * - Log4j2 logging setup
 */
public class BaseClass {

    // Global WebDriver instance for child test classes
    public WebDriver driver;

    // Logger instance (Log4j2) for logging across all test classes
    public Logger logger;

    /**
     * Setup method that runs once before all test methods in the test class.
     * - Initializes Chrome browser
     * - Navigates to the base application URL
     * - Applies default timeouts and configurations
     * - Starts logging session
     */
    @BeforeClass
    @Parameters({"OS", "browser"})
    public void setUp(String os, String br) {
        // Initialize logger using Log4j2
        logger = LogManager.getLogger(this.getClass());

        // Launch browser
        switch(br.toLowerCase()){
            case "chrome": driver = new ChromeDriver();
                           logger.info("Chrome browser launched.");
                           break;
            case "edge": driver = new EdgeDriver();
                           logger.info("Edge browser launched.");
                           break;
            case "firefox": driver = new FirefoxDriver();
                logger.info("Firefox browser launched.");
                break;
            default:
                System.out.println("Invalid browser name...");
                return;
        }


        // Clear cookies for a clean session
        driver.manage().deleteAllCookies();

        // Apply implicit wait for all elements
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        logger.info("Implicit wait of 10 seconds applied.");

        // Navigate to application home page
        driver.get("https://tutorialsninja.com/demo/index.php?route=common/home");
        logger.info("Navigated to application URL.");

        // Maximize the window
        driver.manage().window().maximize();
        logger.info("Browser window maximized.");
    }

    /**
     * Teardown method that runs after all tests in the class finish.
     * - Closes browser
     * - Ends WebDriver session
     * - Logs completion
     */
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Close all browser windows and safely quit driver
            logger.info("Browser closed and WebDriver session ended.");
        }
    }

    // ====================== Utility Methods ======================

    /**
     * Generates a random 5-letter alphabetic string (for names, etc.)
     */
    public String randomString() {
        return RandomStringUtils.randomAlphabetic(5);
    }

    /**
     * Generates a 10-digit numeric string.
     * Although it's numeric in nature, returned as a String to preserve leading zeroes.
     * Useful for mobile numbers or user IDs.
     */
    public String randomNumber() {
        return RandomStringUtils.randomNumeric(10);
    }

    /**
     * Generates a secure random alphanumeric password.
     * Combines 5 letters + 3 digits.
     */
    public String randomAlphaNumeric() {
        return RandomStringUtils.randomAlphabetic(5) + RandomStringUtils.randomNumeric(3);
    }
}
