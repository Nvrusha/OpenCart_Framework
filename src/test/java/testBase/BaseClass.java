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
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

/**
 * BaseClass: Contains setup, teardown, and reusable utility methods.
 * This class supports cross-browser execution and is extended by all test classes.
 *
 * ✅ Includes:
 * - Cross-browser WebDriver initialization based on TestNG parameters
 * - Global logger setup with Log4j2
 * - Random test data generation methods
 */
public class BaseClass {

    public WebDriver driver;           // Shared WebDriver instance
    public Logger logger;             // Log4j2 logger instance
    public Properties p;              // Properties object to store key-value pairs loaded from config file

    /**
     * Initializes browser and test environment before test class execution.
     *
     * @param os       Operating system passed from testng.xml (currently unused but useful for grid/docker)
     * @param br       Browser name (e.g., "chrome", "firefox", "edge")
     */
    @BeforeClass
    @Parameters({"OS", "browser"})
    public void setUp(String os, String br) throws IOException {

        // ========================== Load Config Properties ==========================

        // Load the configuration file from the relative path in the project structure.
        // This file contains key-value pairs like base URL, credentials, timeouts, etc.
        FileReader file = new FileReader("./src/test/resources/config.properties");

        // Create a new Properties object
        p = new Properties();

        // Load properties from the file into memory
        p.load(file);

        // ===========================================================================

        // Initialize logger
        logger = LogManager.getLogger(this.getClass());

        // Launch browser based on parameter
        switch (br.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                logger.info("Chrome browser launched.");
                break;
            case "firefox":
                driver = new FirefoxDriver();
                logger.info("Firefox browser launched.");
                break;
            case "edge":
                driver = new EdgeDriver();
                logger.info("Edge browser launched.");
                break;
            default:
                logger.error("Invalid browser name: " + br);
                throw new IllegalArgumentException("Unsupported browser: " + br);
        }

        // Clear all cookies for clean state
        driver.manage().deleteAllCookies();

        // Implicit wait for element availability
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        logger.info("Implicit wait of 10 seconds applied.");

        // Launch application using value from config.properties
        // Reads the URL value associated with key "appURL"
        driver.get(p.getProperty("appURL"));
        logger.info("Navigated to application URL.");

        // Maximize window
        driver.manage().window().maximize();
        logger.info("Browser window maximized.");
    }

    /**
     * tearDown(): Executes after all test methods in the class have run.
     *
     * Purpose:
     * - Ensures the WebDriver session is properly closed to release system resources.
     * - Handles both successful and failed setup scenarios gracefully.
     */
    @AfterClass
    public void tearDown() {

        // Check if the WebDriver was successfully initialized
        if (driver != null) {
            driver.quit(); // Close all browser windows and end WebDriver session
            logger.info("Browser closed and WebDriver session ended.");
        } else {
            // If setup failed (e.g., due to invalid browser), WebDriver remains null
            logger.warn("TearDown skipped: WebDriver not initialized due to setup failure.");
        }
    }

    // =================== Utility Methods for Random Data ===================

    /**
     * Generates a 5-character random alphabetic string.
     */
    public String randomString() {
        return RandomStringUtils.randomAlphabetic(5);
    }

    /**
     * Generates a 10-digit random numeric string.
     * Useful for test data like phone numbers.
     */
    public String randomNumber() {
        return RandomStringUtils.randomNumeric(10);
    }

    /**
     * Generates a random alphanumeric string (5 letters + 3 digits).
     * Typically used for generating secure test passwords.
     */
    public String randomAlphaNumeric() {
        return RandomStringUtils.randomAlphabetic(5) + RandomStringUtils.randomNumeric(3);
    }
}
