package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * LoginPage: Page Object class representing the login screen of the application.
 *
 * ✅ Responsibilities:
 * - Locate login-related elements using @FindBy
 * - Provide reusable methods to interact with login fields and button
 * - Follows Page Object Model design pattern
 */
public class LoginPage extends BasePage {

    /**
     * Constructor: Initializes the WebDriver and passes it to the BasePage for shared setup.
     *
     * @param driver WebDriver instance passed from test class
     */
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // ================= Page Elements =================

    /**
     * Email input field on the login page
     */
    @FindBy(xpath = "//input[@id=\"input-email\"]")
    WebElement txtEmailAddress;

    /**
     * Password input field on the login page
     */
    @FindBy(xpath = "//input[@id=\"input-password\"]")
    WebElement txtPassword;

    /**
     * Login button on the login form
     */
    @FindBy(xpath = "//input[@class=\"btn btn-primary\"]")
    WebElement btnLogin;

    // ================= Page Actions / Functional Methods =================

    /**
     * Enters the provided email into the email input field.
     *
     * @param mail Email address to input
     */
    public void setMail(String mail) {
        txtEmailAddress.sendKeys(mail);
    }

    /**
     * Enters the provided password into the password input field.
     *
     * @param pwd Password string to input
     */
    public void setPassword(String pwd) {
        txtPassword.sendKeys(pwd);
    }

    /**
     * Clicks the Login button to submit the form.
     */
    public void clickLogin() {
        btnLogin.click();
    }
}
