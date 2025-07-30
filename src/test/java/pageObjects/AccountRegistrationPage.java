// This class belongs to the 'pageObjects' package — all page classes are grouped here
package pageObjects;

// Selenium WebDriver for browser control
import org.openqa.selenium.WebDriver;

// WebElement represents elements on the web page
import org.openqa.selenium.WebElement;

// @FindBy is used for locating page elements using XPath
import org.openqa.selenium.support.FindBy;

// This class models the Account Registration page using the Page Object Model (POM) pattern.
// It extends BasePage, which handles common WebDriver setup and element initialization.
public class AccountRegistrationPage extends BasePage {

    // Constructor that receives the WebDriver from test classes and passes it to BasePage
    public AccountRegistrationPage(WebDriver driver) {
        super(driver);
    }

    // ----------- Page Elements (Located using XPath) -----------

    // Input field for First Name
    @FindBy(xpath = "//input[@id=\"input-firstname\"]")
    WebElement txtfirstNm;

    // Input field for Last Name
    @FindBy(xpath = "//input[@id=\"input-lastname\"]")
    WebElement txtlastNm;

    // Input field for Email
    @FindBy(xpath = "//input[@id=\"input-email\"]")
    WebElement txtemail;

    // Input field for Telephone Number
    @FindBy(xpath = "//input[@id=\"input-telephone\"]")
    WebElement txtTelephone;

    // Input field for Password
    @FindBy(xpath = "//input[@id=\"input-password\"]")
    WebElement txtPassword;

    // Input field for Confirm Password
    @FindBy(xpath = "//input[@id=\"input-confirm\"]")
    WebElement txtConfirmPassword;

    // Checkbox to agree to the Privacy Policy
    @FindBy(xpath = "//input[@name=\"agree\"]")
    WebElement checkPolicy;

    // "Continue" button to submit the registration form
    @FindBy(xpath = "//input[@class=\"btn btn-primary\"]")
    WebElement btnContinue;

    // Confirmation message displayed after successful registration
    @FindBy(xpath = "//h1[text() =\"Your Account Has Been Created!\"]")
    WebElement msgConfirmation;

    // ----------- Page Actions (Reusable Methods) -----------

    // Sets the First Name value in the form
    public void setFirstName(String fName) {
        txtfirstNm.sendKeys(fName);
    }

    // Sets the Last Name value in the form
    public void setLastName(String lName) {
        txtlastNm.sendKeys(lName);
    }

    // Sets the Email address in the form
    public void setEmail(String email) {
        txtemail.sendKeys(email);
    }

    // Sets the Telephone number in the form
    public void setTelephone(String tel) {
        txtTelephone.sendKeys(tel);
    }

    // Sets the Password in the form
    public void setPassword(String pwd) {
        txtPassword.sendKeys(pwd);
    }

    // Sets the Confirm Password value in the form
    public void setConfirmPassword(String pwd) {
        txtConfirmPassword.sendKeys(pwd);
    }

    // Clicks the checkbox to agree to the Privacy Policy
    public void setPrivacyPolicy() {
        checkPolicy.click();
    }

    // Clicks the Continue button to submit the registration form
    public void clickContinue() {
        btnContinue.click();
    }

    // Returns the confirmation message after successful registration
    // Wrapped in try-catch to avoid test crash if element is not found
    public String getConfirmationMsg() {
        try {
            return msgConfirmation.getText(); // Expected: "Your Account Has Been Created!"
        } catch (Exception e) {
            return e.getMessage(); // Useful for debugging when test fails
        }
    }
}
