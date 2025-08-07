package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * MyAccountPage: Page Object representing the "My Account" landing page
 * after successful login or registration in the OpenCart application.
 *
 * ✅ Responsibilities:
 * - Identify key UI elements on the My Account page
 * - Provide validation and navigation methods to interact with this page
 * - Encapsulate logic following the Page Object Model for test maintainability
 */
public class MyAccountPage extends BasePage {

    /**
     * Constructor: Accepts WebDriver from the test class and initializes elements via BasePage.
     *
     * @param driver WebDriver instance
     */
    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    // ================= Page Elements =================

    /**
     * Heading element displayed on the My Account page
     * Used to verify that the user has successfully landed on the page.
     */
    @FindBy(xpath = "//h2[text()='My Account']")
    WebElement msgHeading;

    /**
     * Logout link available in the My Account page sidebar.
     * Clicking this should log the user out and redirect to the Logout confirmation page.
     */
    @FindBy(xpath = "//div[@class='list-group']/a[text()='Logout']")
    WebElement lnkLogout;

    // ================= Functional Methods =================

    /**
     * Checks if the "My Account" heading is displayed on the page.
     * This confirms successful login or account creation.
     *
     * @return true if heading is visible, false otherwise
     */
    public boolean isMyAccountPageExists() {
        try {
            return (msgHeading.isDisplayed());
        } catch (Exception e) {
            return false; // Handles case where element is not found or visible
        }
    }

    /**
     * Clicks the "Logout" link from the sidebar.
     * Can be used to verify logout functionality or reset session between tests.
     */
    public void clickLogout() {
        lnkLogout.click();
    }
}
