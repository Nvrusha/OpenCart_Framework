package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.SearchPage;
import testBase.BaseClass;

public class TC004_SearchProductTest extends BaseClass {

    @Test(groups = "Regression")
    public void verifyProductSearch() {
        logger.info("=== Starting TC004: Product Search Test ===");

        try {
            // Step 1: Go to Home Page and search for a product
            HomePage hp = new HomePage(driver);
            hp.enterSearchText("MacBook");
            hp.clickSearchButton();
            logger.info("Search submitted for product: MacBook");

            // Step 2: Verify search results
            SearchPage sp = new SearchPage(driver);
            boolean productFound = sp.isProductInResults("MacBook");
            Assert.assertTrue(productFound, "Product not found in search results");

        } catch (Exception e) {
            logger.error("❌ Exception occurred during product search: " + e.getMessage(), e);
            Assert.fail("Test failed due to unexpected error.");
        }

        logger.info("=== TC004: Product Search Test Completed ===");
    }
}
