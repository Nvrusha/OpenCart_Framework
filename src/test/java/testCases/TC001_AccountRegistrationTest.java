package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

/**
 * Test Case: TC001_AccountRegistrationTest
 * Description: Verifies successful user account registration on the TutorialsNinja demo website
 * using randomly generated input data and validating the confirmation message.
 */
public class TC001_AccountRegistrationTest extends BaseClass {

    @Test
    public void verifyAccountRegistration() {

        logger.info("=== Starting TC001: Account Registration Test ===");

        // =======================
        // Step 1: Open Registration Page
        // =======================

        logger.info("Navigating to Registration Page...");
        HomePage hp = new HomePage(driver);
        hp.clickMyAccount();     // Opens the dropdown for login/register options
        logger.debug("Clicked 'My Account' on homepage");

        hp.clickRegister();      // Click on "Register" to open the registration form
        logger.debug("Clicked 'Register' to open registration form");

        // =======================
        // Step 2: Fill the Registration Form with Random Data
        // =======================

        AccountRegistrationPage regPage = new AccountRegistrationPage(driver);
        logger.info("Filling in registration form with random data...");

        String firstName = randomString().toUpperCase();
        String lastName = randomString().toUpperCase();
        String email = randomString() + "@gmail.com";
        String phone = randomNumber();
        String password = randomAlphaNumeric();

        logger.debug("Generated Test Data → FirstName: " + firstName + ", LastName: " + lastName +
                ", Email: " + email + ", Phone: " + phone + ", Password: " + password);

        regPage.setFirstName(firstName);
        regPage.setLastName(lastName);
        regPage.setEmail(email);
        regPage.setTelephone(phone);
        regPage.setPassword(password);
        regPage.setConfirmPassword(password);

        // =======================
        // Step 3: Submit the Form
        // =======================

        regPage.setPrivacyPolicy();
        logger.debug("Checked the privacy policy box");

        regPage.clickContinue();
        logger.info("Clicked 'Continue' to submit registration form");

        // =======================
        // Step 4: Validate Confirmation Message
        // =======================

        String confirmMsg = regPage.getConfirmationMsg();
        logger.info("Confirmation message received: " + confirmMsg);

        try {
            Assert.assertEquals(confirmMsg, "You Account Has Been Created!");
            logger.info("✅ Account registration test passed");
        } catch (AssertionError e) {
            logger.error("❌ Test failed — Expected confirmation not received", e);
            throw e; // rethrow so TestNG can mark test as failed
        }

        logger.info("=== TC001: Account Registration Test Completed ===");
    }
}
