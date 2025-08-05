package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * MyAccountPage: Page Object representing the "My Account" landing page after successful login or registration.
 *
 * ✅ Responsibilities:
 * - Identify key UI elements on the My Account page
 * - Provide validation methods to confirm user has reached this page
 * - Follows Page Object Model for maintainable test automation
 */
public class MyAccountPage extends BasePage {

    /**
     * Constructor: Accepts WebDriver from test class and initializes it via BasePage.
     *
     * @param driver WebDriver instance
     */
    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    // ================= Page Elements =================

    /**
     * Heading element displayed on the My Account page
     */
    @FindBy(xpath = "//h2[text()='My Account']")
    WebElement msgHeading;

    // ================= Functional Methods =================

    /**
     * Checks if the "My Account" heading is displayed on the page.
     * This can be used to verify successful login or registration.
     *
     * @return true if heading is visible, false otherwise
     */
    public boolean isMyAccountPageExists() {
        try {
            return (msgHeading.isDisplayed());
        } catch (Exception e) {
            return false; // Handles scenarios where element is not present or accessible
        }
    }
}
