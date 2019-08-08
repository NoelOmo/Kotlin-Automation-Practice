package ke.co.noel.pages

import ke.co.noel.utils.Constants
import ke.co.noel.utils.WebDriverUtils
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

class HomePage(_webDriver: WebDriver) {

    private var webDriver: WebDriver = _webDriver

    fun getClickableHomePageElementByXPath(xpath: String): WebElement {
        return WebDriverUtils.waitForElementToBeClickable(webDriver, By.xpath(xpath), Constants.WEB_DRIVER_WAIT)
    }
    fun getSignInButton() = this.getClickableHomePageElementByXPath("//a[contains(text(), \"Sign in\")]")
}