// This class belongs to the pageObjects package
package pageObjects;

// Import Selenium's WebDriver to control the browser
import org.openqa.selenium.WebDriver;

// Import WebElement so we can define clickable elements
import org.openqa.selenium.WebElement;

// Import FindBy for locating elements using XPath
import org.openqa.selenium.support.FindBy;

// HomePage class extends BasePage to reuse common WebDriver setup and element initialization
public class HomePage extends BasePage {

    // Constructor for HomePage
    // It accepts a WebDriver and passes it to the BasePage using 'super'
    public HomePage(WebDriver driver){
        super(driver);  // This initializes the driver and WebElements in the parent class
    }

    // ----------- Page Elements (Located using XPath) -----------

    // Locate the "My Account" link using its title attribute
    @FindBy(xpath = "//a[@title=\"My Account\"]")
    WebElement lnkMyAccount;

    // Locate the "Register" link using its visible text
    @FindBy(xpath = "//a[text()='Register']")
    WebElement lnkRegister;

    // Locate the "login" link using its visible text
    @FindBy(xpath = "//a[text()='Login']")
    WebElement lnkLogin;



    // ----------- Page Actions (Reusable Methods) -----------

    // Method to click on the "My Account" link
    public void clickMyAccount (){
        lnkMyAccount.click();  // Simulates user clicking on the "My Account" dropdown
    }

    // Method to click on the "Register" link
    public void clickRegister (){
        lnkRegister.click();  // Simulates user clicking on the "Register" option
    }

    // Method to click on the "Login" link
    public void clickLogin (){
        lnkLogin.click();  // Simulates user clicking on the "Register" option
    }
}
