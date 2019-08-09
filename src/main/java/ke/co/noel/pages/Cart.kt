package ke.co.noel.pages

import ke.co.noel.utils.Constants
import ke.co.noel.utils.WebDriverUtils
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

class Cart(_webDriver: WebDriver) {

    private var webDriver: WebDriver = _webDriver

    fun getTShirtsBtn(): WebElement {
        return WebDriverUtils.waitForElementToBeClickable(webDriver, By.xpath("//*[@id=\"block_top_menu\"]/ul/li[3]"), Constants.WEB_DRIVER_WAIT)
    }

    fun getProductCount(): List<WebElement> {
        return webDriver.findElements(By.xpath("//*[@id=\"center_column\"]/ul/li"))
    }

    fun getProductByIndex(index: Int): WebElement {
        return WebDriverUtils.waitForElement(webDriver, By.xpath("//*[@id=\"center_column\"]/ul/li[$index]"), Constants.WEB_DRIVER_WAIT)
    }

    fun getAddToCartBtn(): WebElement {
        return WebDriverUtils.waitForElement(webDriver, By.xpath("//*[@id=\"center_column\"]/ul/li//span[contains(text(), \"Add to cart\")]"), Constants.WEB_DRIVER_WAIT)
    }

    fun getContinueShoppingBtn(): WebElement {
            return WebDriverUtils.waitForElementToBeClickable(webDriver, By.xpath("//span[@title=\"Continue shopping\"]"), 5)
    }

    fun getShoppingCartDropdown(): WebElement {
        return WebDriverUtils.waitForElement(webDriver, By.xpath("//b[contains(text(), \"Cart\")]/.."), Constants.WEB_DRIVER_WAIT)
    }

    fun getCartItemsCount(): List<WebElement>? {
        return webDriver.findElements(By.xpath("//dt"))
    }

    fun getCheckoutBtn(): WebElement {
        return WebDriverUtils.waitForElement(webDriver, By.xpath("//a[@id=\"button_order_cart\"]/span[contains(text(), \"Check out\")]"), Constants.WEB_DRIVER_WAIT)
    }

    fun getShoppingSummary(): WebElement {
        return WebDriverUtils.waitForElement(webDriver, By.id("cart_summary"), Constants.WEB_DRIVER_WAIT)
    }

    fun getCartProductCount(): List<WebElement> {
        return webDriver.findElements(By.xpath("//table[@id=\"cart_summary\"]/tbody/tr"))
    }

    fun getCartProductTotals(): WebElement {
        return WebDriverUtils.waitForElement(webDriver, By.id("total_product"), Constants.WEB_DRIVER_WAIT)
    }

    fun getProceedToCheckoutButton(): WebElement {
        return WebDriverUtils.waitForElementToBeClickable(webDriver, By.xpath("//*[@id=\"center_column\"]/p[2]/a[1]/span"), Constants.WEB_DRIVER_WAIT)
    }

    fun getProceedToShippingBtn(): WebElement {
        return WebDriverUtils.waitForElementToBeClickable(webDriver, By.xpath("//button[@type=\"submit\"]/span[contains(text(), \"Proceed to checkout\")]"), Constants.WEB_DRIVER_WAIT)
    }

    fun getTermsAndConditionCheckbox(): WebElement {
        return WebDriverUtils.waitForElement(webDriver, By.id("cgv"), Constants.WEB_DRIVER_WAIT)
    }

    fun getPayByWireButton(): WebElement {
        return WebDriverUtils.waitForElementToBeClickable(webDriver,  By.xpath("//a[@title=\"Pay by bank wire\"]"), Constants.WEB_DRIVER_WAIT)
    }

    fun getConfirmOrderBtn(): WebElement {
        return WebDriverUtils.waitForElementToBeClickable(webDriver,  By.xpath("//button[@type=\"submit\"]/span[contains(text(), \"I confirm my order\")]"), Constants.WEB_DRIVER_WAIT)
    }

    fun getOrderCompleteMessage(): WebElement {
        return WebDriverUtils.waitForElement(webDriver,  By.xpath("//strong[contains(text(), \"Your order on My Store is complete.\")]"), Constants.WEB_DRIVER_WAIT)
    }
}