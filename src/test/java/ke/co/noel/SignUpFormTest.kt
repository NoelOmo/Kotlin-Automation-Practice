package ke.co.noel

import ke.co.noel.pages.HomePage
import ke.co.noel.pages.LoginForm
import ke.co.noel.pages.SignupForm
import ke.co.noel.utils.Constants
import ke.co.noel.utils.WebDriverUtils
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.testng.Assert
import org.testng.annotations.AfterClass
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test

class SignUpFormTest {

    private lateinit var webDriver: WebDriver
    private lateinit var signupForm: SignupForm
    private lateinit var homePage: HomePage
    private lateinit var loginForm: LoginForm

    @BeforeClass
    fun setup() {
        System.setProperty("webdriver.chrome.driver", Constants.WEB_DRIVER_LOCATION)
        webDriver = ChromeDriver()
        homePage = HomePage(webDriver)
        loginForm = LoginForm(webDriver)
        signupForm = SignupForm(webDriver)
        webDriver.manage().window().maximize()
        webDriver.get(Constants.BASE_URL)
    }

    @Test(priority = 1)
    fun openAccountRegistrationForm() {
        homePage.getSignInButton().click()
        Assert.assertTrue(signupForm.getCreateAccountForm().isDisplayed)
        Assert.assertTrue(signupForm.getCreateAccountBtn().isDisplayed)
        Assert.assertTrue(signupForm.getLoginForm().isDisplayed)
    }

    @Test(priority = 2)
    fun userEmailAddressValidation() {
        signupForm.getCreateAccountBtn().click()
        Assert.assertTrue(signupForm.getGenericErrorMessage("//li[contains(text(), \"Invalid email address.\")]").isDisplayed)

        WebDriverUtils.sendKeys(signupForm.getCreateAccountEmail(), "user")
        signupForm.getCreateAccountBtn().click()
        Assert.assertTrue(signupForm.getGenericErrorMessage("//li[contains(text(), \"Invalid email address.\")]").isDisplayed)

        WebDriverUtils.sendKeys(signupForm.getCreateAccountEmail(), "user@testuser.com")
        signupForm.getCreateAccountBtn().click()
        Assert.assertTrue(signupForm.getGenericErrorMessage("//li[contains(text(), \"An account using this email\")]").isDisplayed)

        WebDriverUtils.sendKeys(signupForm.getCreateAccountEmail(), "user3@testuser.com")
        signupForm.getCreateAccountBtn().click()
        Assert.assertTrue(signupForm.getAccountRegistrationForm().isDisplayed)
    }

    @Test(priority = 3)
    fun signupFormSectionOne() {
        WebDriverUtils.sendKeys(signupForm.getCustomerFirstName(), "Noel")
        WebDriverUtils.sendKeys(signupForm.getCustomerLastName(), "Omondi")
        WebDriverUtils.sendKeys(signupForm.getEmailField(), "user3@testuser.com")
        WebDriverUtils.sendKeys(signupForm.getPasswordField(), "user3@testuser.com")
        signupForm.getAccountRegistrationForm().click()

        Assert.assertTrue(signupForm.firstNameInputOk().isDisplayed)
        Assert.assertTrue(signupForm.lastNameInputOk().isDisplayed)
        Assert.assertTrue(signupForm.emailInputOk().isDisplayed)
        Assert.assertTrue(signupForm.passwordInputOk().isDisplayed)

        WebDriverUtils.sendKeys(signupForm.getCustomerFirstName(), "")
        WebDriverUtils.sendKeys(signupForm.getCustomerLastName(), "")
        WebDriverUtils.sendKeys(signupForm.getEmailField(), "")
        WebDriverUtils.sendKeys(signupForm.getPasswordField(), "")
        signupForm.getAccountRegistrationForm().click()

        Assert.assertTrue(signupForm.firstNameInputError().isDisplayed)
        Assert.assertTrue(signupForm.lastNameInputError().isDisplayed)
        Assert.assertTrue(signupForm.emailInputError().isDisplayed)
        Assert.assertTrue(signupForm.passwordInputError().isDisplayed)
    }

    @Test(priority = 4)
    fun submitFormWithoutRequiredFields() {
        WebDriverUtils.sendKeys(signupForm.getAliasField(), "")
        WebDriverUtils.sendKeys(signupForm.getEmailField(), "")
        signupForm.getCountryDropdown().selectByIndex(0)
        signupForm.getSubmitFormButton().click()

        Assert.assertTrue(signupForm.getPhoneNumberError().isDisplayed)
        Assert.assertTrue(signupForm.getLastNameError().isDisplayed)
        Assert.assertTrue(signupForm.getFirstNameError().isDisplayed)
        Assert.assertTrue(signupForm.getEmailRequiredError().isDisplayed)
        Assert.assertTrue(signupForm.getAddressRequiredError().isDisplayed)
        Assert.assertTrue(signupForm.getPasswordRequiredError().isDisplayed)
        Assert.assertTrue(signupForm.getCountryRequiredError().isDisplayed)
        Assert.assertTrue(signupForm.getAddressAliasRequiredError().isDisplayed)
        Assert.assertTrue(signupForm.getCityRequiredError().isDisplayed)
        Assert.assertTrue(signupForm.getCountryUnselectedError().isDisplayed)

        signupForm.getCountryDropdown().selectByIndex(1)
        signupForm.getSubmitFormButton().click()

        Assert.assertTrue(signupForm.getStateRequiredError().isDisplayed)
        Assert.assertTrue(signupForm.getPostalCodeError().isDisplayed)
    }

    @Test(priority = 5)
    fun registerAccount() {
        WebDriverUtils.sendKeys(signupForm.getCustomerFirstName(), "Noel")
        WebDriverUtils.sendKeys(signupForm.getCustomerLastName(), "Omondi")
        WebDriverUtils.sendKeys(signupForm.getEmailField(), "user10@testuser.com")
        WebDriverUtils.sendKeys(signupForm.getPasswordField(), "123Hello")

        signupForm.setDropDownValue(signupForm.getDropdown("days"), "1")
        signupForm.setDropDownValue(signupForm.getDropdown("months"), "1")
        signupForm.setDropDownValue(signupForm.getDropdown("years"), "1999")

        WebDriverUtils.sendKeys(signupForm.getAddress1Field(), "Nairobi")
        WebDriverUtils.sendKeys(signupForm.getCityField(), "Mombasa")
        signupForm.setDropDownValue(signupForm.getDropdown("id_state"), "5")
        WebDriverUtils.sendKeys(signupForm.getPostCodeField(), "11000")
        WebDriverUtils.sendKeys(signupForm.getPhoneField2(), "11222233")
        WebDriverUtils.sendKeys(signupForm.getPhoneField(), "112233")
        WebDriverUtils.sendKeys(signupForm.getAliasField(), "452A Tena")

        signupForm.getSubmitFormButton().click()

        Assert.assertTrue(loginForm.getGenericSuccessLoginMessage().isDisplayed)
    }


    @AfterClass
    fun tearDown() {
        webDriver.quit()
    }
}