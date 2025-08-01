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

            // =======================
            // Step 1: Open Registration Page
            // =======================

            // Navigate to the home page and click on "My Account"
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();     // Opens the dropdown for login/register options

            // Click on "Register" to open the registration form
            hp.clickRegister();

            // =======================
            // Step 2: Fill the Registration Form with Random Data
            // =======================

            // Create object for the registration page
            AccountRegistrationPage regPage = new AccountRegistrationPage(driver);

            // Enter first name (randomly generated and converted to uppercase)
            regPage.setFirstName(randomString().toUpperCase());

            // Enter last name (randomly generated and converted to uppercase)
            regPage.setLastName(randomString().toUpperCase());

            // Enter unique email address (random string + @gmail.com)
            regPage.setEmail(randomString() + "@gmail.com");

            // Enter a random 10-digit phone number
            regPage.setTelephone(randomNumber());

            // Generate and enter a secure random password
            String password = randomAlphaNumeric();
            regPage.setPassword(password);
            regPage.setConfirmPassword(password); // Confirm the same password

            // =======================
            // Step 3: Submit the Form
            // =======================

            // Accept the privacy policy (checkbox)
            regPage.setPrivacyPolicy();

            // Click the "Continue" button to register
            regPage.clickContinue();

            // =======================
            // Step 4: Validate Confirmation Message
            // =======================

            // Fetch the message displayed after successful registration
            String confirmMsg = regPage.getConfirmationMsg();

            // Validate that the success message matches the expected text
            Assert.assertEquals(confirmMsg, "Your Account Has Been Created!");

            // If assertion passes, test is marked as successful
        }
    }
