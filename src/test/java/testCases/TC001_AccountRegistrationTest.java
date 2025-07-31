package testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

import java.time.Duration;

public class TC001_AccountRegistrationTest {

    public WebDriver driver;

    @BeforeClass
    public void setUp(){

        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://tutorialsninja.com/demo/index.php?route=common/home");
        driver.manage().window().maximize();

    }

    @AfterClass
    public void tearDown(){

    }

    @Test
    public void verifyAccountRegistration(){

        HomePage hp = new HomePage(driver);
        hp.clickMyAccount();
        hp.clickRegister();

        AccountRegistrationPage regPage = new AccountRegistrationPage(driver);
        regPage.setFirstName("John");
        regPage.setLastName("Doe");
        regPage.setEmail("hgdfhsgdfmre@gmail.com");
        regPage.setTelephone("1234556789");
        regPage.setPassword("Vhghgjh$123");
        regPage.setConfirmPassword("Vhghgjh$123");

        regPage.setPrivacyPolicy();
        regPage.clickContinue();

        String confirmMsg = regPage.getConfirmationMsg();

        Assert.assertEquals(confirmMsg, "Your Account Has Been Created!");

    }
}
