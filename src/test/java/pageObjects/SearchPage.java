package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchPage extends BasePage {

    public SearchPage(WebDriver driver){
        super(driver);
    }

    // ----------- Page Elements (Located using XPath) -----------

    @FindBy(css = "div.product-thumb h4 a")
    List<WebElement> productList;

    // ---------- Page Actions ----------

    public boolean isProductInResults(String productName){
        for(WebElement product : productList){
            if (product.getText().equalsIgnoreCase(productName)){
                return true;
            }
        }
        return false;
    }


}
