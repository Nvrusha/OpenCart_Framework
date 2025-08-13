package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage {

    public SearchPage(WebDriver driver){
        super(driver);
    }

    // ----------- Page Elements (Located using XPath) -----------

    @FindBy(name = "search")
    WebElement txtSearchBox;

    @FindBy(xpath = "//button[@class='btn btn-default btn-lg']")
    WebElement btnSearch;

    // ---------- Page Actions ----------

    public void enterSearchText(String productName){
        txtSearchBox.clear();
        txtSearchBox.sendKeys(productName);
    }

    public void clickSearchButton(){
        btnSearch.click();
    }
}
