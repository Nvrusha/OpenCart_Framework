package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

/**
 * Test Case: TC001_AccountRegistrationTest
 * Description: Validates user registration on TutorialsNinja demo site using random input data.
 * Framework Layer: Test Layer (uses Page Object Model + TestNG + Selenium)
 */
public class TC001_AccountRegistrationTest extends BaseClass {

    /**
     * Test: Automates account registration flow and verifies the confirmation message.
     * Uses BaseClass for setup and data generation. Follows POM design pattern.
     */
    @Test
    public void verifyAccountRegistration() {

        // Step 1: Navigate to Registration Page via Home Page
        HomePage hp = new HomePage(driver);
        hp.clickMyAccount();         // Click on "My Account"
        hp.clickRegister();          // Select "Register"

        // Step 2: Fill in the Registration Form with randomly generated data
        AccountRegistrationPage regPage = new AccountRegistrationPage(driver);
        regPage.setFirstName(randomString().toUpperCase());            // Random First Name
        regPage.setLastName(randomString().toUpperCase());             // Random Last Name
        regPage.setEmail(randomString() + "@gmail.com");               // Random Email
        regPage.setTelephone(randomNumber());                          // Random Phone Number

        String password = randomAlphaNumeric();                        // Random Password
        regPage.setPassword(password);
        regPage.setConfirmPassword(password);                          // Match Password

        regPage.setPrivacyPolicy();                                     // Accept Terms
        regPage.clickContinue();                                        // Submit Registration

        // Step 3: Validate confirmation message
        String confirmMsg = regPage.getConfirmationMsg();
        Assert.assertEquals(confirmMsg, "Your Account Has Been Created!"); // Final assertion
    }
}
