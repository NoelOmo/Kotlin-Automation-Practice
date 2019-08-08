package ke.co.noel.pages

import ke.co.noel.utils.Constants
import ke.co.noel.utils.WebDriverUtils
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

class LoginForm (_webDriver: WebDriver){

    private var webDriver: WebDriver = _webDriver

    fun getLoginForm(): WebElement {
        return WebDriverUtils.waitForElement(webDriver, By.id("login_form"), Constants.WEB_DRIVER_WAIT)
    }

    fun getLoginEmailField(): WebElement {
        return WebDriverUtils.waitForElement(webDriver, By.id("email"), Constants.WEB_DRIVER_WAIT)
    }

    fun getLoginPasswordField(): WebElement {
        return WebDriverUtils.waitForElement(webDriver, By.id("passwd"), Constants.WEB_DRIVER_WAIT)
    }

    fun getLoginButton(): WebElement {
        return WebDriverUtils.waitForElement(webDriver, By.id("SubmitLogin"), Constants.WEB_DRIVER_WAIT)
    }

    fun setEmail(email: String) {
        WebDriverUtils.sendKeys(this.getLoginEmailField(), email)
    }

    fun setPassword(password: String) {
        WebDriverUtils.sendKeys(this.getLoginPasswordField(), password)
    }

    fun getGenericSuccessLoginMessage(): WebElement {
        return WebDriverUtils.waitForElement(webDriver, By.xpath("//p[contains(text(), \"Welcome to your account.\")]"), Constants.WEB_DRIVER_WAIT)
    }

    fun getGenericLoginErrors(type: String): WebElement? {
        when(type) {
            Constants.EMAIL_REQUIRED_ERROR -> {
                return WebDriverUtils.waitForElement(webDriver, By.xpath("//li[contains(text(), \"An email address required.\")]"), Constants.WEB_DRIVER_WAIT)
            }
            Constants.PASSWORD_REQUIRED_ERROR -> {
                return WebDriverUtils.waitForElement(webDriver, By.xpath("//li[contains(text(), \"Password is required.\")]"), Constants.WEB_DRIVER_WAIT)
            }
            Constants.AUTHENTICATION_FAILED_ERROR -> {
                return WebDriverUtils.waitForElement(webDriver, By.xpath("//li[contains(text(), \"Authentication failed.\")]"), Constants.WEB_DRIVER_WAIT)
            }
            Constants.EMAIL_FIELD_FORM_ERROR -> {
                return WebDriverUtils.waitForElement(webDriver, By.xpath("//div[@class=\"form-group form-error\"]//input[@id=\"email\"]"), Constants.WEB_DRIVER_WAIT)
            }
            Constants.EMAIL_FIELD_NO_ERROR -> {
                return WebDriverUtils.waitForElement(webDriver, By.xpath("//div[@class=\"form-group form-ok\"]//input[@id=\"email\"]"), Constants.WEB_DRIVER_WAIT)
        }
            else -> return null
        }
    }
}