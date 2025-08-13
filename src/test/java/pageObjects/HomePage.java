// This class belongs to the pageObjects package
package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * HomePage:
 * Represents the Home Page of the OpenCart application.
 * Contains elements for navigation (My Account, Register, Login) and search functionality.
 * Extends BasePage to reuse WebDriver initialization and PageFactory element setup.
 */
public class HomePage extends BasePage {

    // Constructor: Accepts WebDriver instance from test class and passes it to BasePage
    public HomePage(WebDriver driver) {
        super(driver);
    }

    // ----------- Page Elements (Located using XPath or name) -----------

    // "My Account" link on the top menu
    @FindBy(xpath = "//a[@title=\"My Account\"]")
    WebElement lnkMyAccount;

    // "Register" link inside the My Account dropdown
    @FindBy(xpath = "//a[text()='Register']")
    WebElement lnkRegister;

    // "Login" link inside the My Account dropdown
    @FindBy(xpath = "//a[text()='Login']")
    WebElement lnkLogin;

    // Search text box on the home page (where product name is entered)
    @FindBy(name = "search")
    WebElement txtSearchBox;

    // Search button (magnifying glass icon) next to the search text box
    @FindBy(xpath = "//button[@class='btn btn-default btn-lg']")
    WebElement btnSearch;


    // ----------- Page Actions (Reusable Methods) -----------

    /**
     * Clicks on the "My Account" link to open the dropdown menu.
     */
    public void clickMyAccount() {
        lnkMyAccount.click();
    }

    /**
     * Clicks on the "Register" link in the My Account dropdown.
     */
    public void clickRegister() {
        lnkRegister.click();
    }

    /**
     * Clicks on the "Login" link in the My Account dropdown.
     */
    public void clickLogin() {
        lnkLogin.click();
    }

    /**
     * Enters the product name into the search text box.
     * @param productName Name of the product to search for
     */
    public void enterSearchText(String productName) {
        txtSearchBox.clear(); // Clear any existing text
        txtSearchBox.sendKeys(productName); // Type the product name
    }

    /**
     * Clicks on the search button to perform the product search.
     */
    public void clickSearchButton() {
        btnSearch.click();
    }
}
