// Package declaration - this class is part of the pageObjects package
package pageObjects;

// Importing Selenium's WebDriver interface to interact with the browser
import org.openqa.selenium.WebDriver;

// Importing PageFactory class used for initializing web elements
import org.openqa.selenium.support.PageFactory;

// BasePage class that will be extended by all other Page Object classes
public class BasePage {

    // WebDriver instance variable to be used by child classes
    WebDriver driver;

    // Constructor of BasePage which takes a WebDriver object as argument
    public BasePage(WebDriver driver){

        // Assign the passed driver instance to this class's driver variable
        this.driver = driver;

        // Initialize all the WebElements (marked with @FindBy) in the child class
        // using Selenium PageFactory with the given WebDriver instance
        PageFactory.initElements(driver, this);
    }
}
