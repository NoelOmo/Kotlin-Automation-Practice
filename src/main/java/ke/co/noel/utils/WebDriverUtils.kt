package ke.co.noel.utils

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

object WebDriverUtils {

    fun waitForElement(webDriver: WebDriver, selector: By, waitTime: Int ): WebElement{
        return WebDriverWait(webDriver, waitTime.toLong()).until(ExpectedConditions.presenceOfElementLocated(selector))
    }

    fun sendKeys(webElement: WebElement, text: String) {
        webElement.clear()
        webElement.sendKeys(text)
    }
}