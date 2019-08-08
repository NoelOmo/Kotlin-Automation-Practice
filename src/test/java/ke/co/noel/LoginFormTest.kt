package ke.co.noel

import ke.co.noel.pages.LoginForm
import ke.co.noel.utils.Constants
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.testng.Assert
import org.testng.annotations.AfterClass
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test

class LoginFormTest {

    private lateinit var webDriver: WebDriver
    private lateinit var loginForm: LoginForm

    @BeforeClass
    fun setup() {
        System.setProperty("webdriver.chrome.driver", Constants.WEB_DRIVER_LOCATION)
        webDriver = ChromeDriver()
        loginForm = LoginForm(webDriver)
        webDriver.manage().window().maximize()
        webDriver.get(Constants.BASE_URL)
    }

    @Test(priority = 1)
    fun verifyLoginFormIsDisplayed() {
        Assert.assertTrue(loginForm.getLoginForm().isDisplayed)
        Assert.assertTrue(loginForm.getLoginEmailField().isDisplayed)
        Assert.assertTrue(loginForm.getLoginPasswordField().isDisplayed)
        Assert.assertTrue(loginForm.getLoginButton().isEnabled)
    }

    @Test(priority = 2)
    fun emailFieldValidation() {
        loginForm.setEmail("")
        loginForm.getLoginPasswordField().click()
        Assert.assertTrue(loginForm.getGenericLoginErrors(Constants.EMAIL_FIELD_FORM_ERROR)!!.isDisplayed)

        loginForm.setEmail("user")
        loginForm.getLoginPasswordField().click()
        Assert.assertTrue(loginForm.getGenericLoginErrors(Constants.EMAIL_FIELD_FORM_ERROR)!!.isDisplayed)

        loginForm.setEmail("user@testuser")
        loginForm.getLoginPasswordField().click()
        Assert.assertTrue(loginForm.getGenericLoginErrors(Constants.EMAIL_FIELD_FORM_ERROR)!!.isDisplayed)

        loginForm.setEmail("user@testuser.com")
        loginForm.getLoginPasswordField().click()
        Assert.assertTrue(loginForm.getGenericLoginErrors(Constants.EMAIL_FIELD_NO_ERROR)!!.isDisplayed)
    }

    @Test(priority = 3)
    fun loginWithInvalidCredentials() {
        loginForm.setEmail("user1@testuser.com")
        loginForm.setPassword("123Hello")
        loginForm.getLoginButton().click()
        Assert.assertTrue(loginForm.getGenericLoginErrors(Constants.AUTHENTICATION_FAILED_ERROR)!!.isDisplayed)

        loginForm.setEmail("user2@testuser.com")
        loginForm.setPassword("user2")
        loginForm.getLoginButton().click()
        Assert.assertTrue(loginForm.getGenericLoginErrors(Constants.AUTHENTICATION_FAILED_ERROR)!!.isDisplayed)
    }

    @Test(priority = 4)
    fun loginWithEmptyCredentials() {
        loginForm.setEmail("")
        loginForm.setPassword("")
        loginForm.getLoginButton().click()
        Assert.assertTrue(loginForm.getGenericLoginErrors(Constants.EMAIL_REQUIRED_ERROR)!!.isDisplayed)

        loginForm.setEmail("user1@testuser.com")
        loginForm.setPassword("")
        loginForm.getLoginButton().click()
        Assert.assertTrue(loginForm.getGenericLoginErrors(Constants.PASSWORD_REQUIRED_ERROR)!!.isDisplayed)

        loginForm.setEmail("")
        loginForm.setPassword("123Hello")
        loginForm.getLoginButton().click()
        Assert.assertTrue(loginForm.getGenericLoginErrors(Constants.EMAIL_REQUIRED_ERROR)!!.isDisplayed)
    }

    @Test(priority = 5)
    fun successfulLogin() {
        loginForm.setEmail("user@testuser.com")
        loginForm.setPassword("123Hello")
        loginForm.getLoginButton().click()
        Assert.assertTrue(loginForm.getGenericSuccessLoginMessage().isDisplayed)
    }

    @AfterClass
    fun tearDown() {
        webDriver.quit()
    }
}