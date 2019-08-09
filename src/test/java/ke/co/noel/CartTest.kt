package ke.co.noel

import ke.co.noel.pages.Cart
import ke.co.noel.pages.LoginForm
import ke.co.noel.utils.Constants
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.interactions.Actions
import org.testng.Assert
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test

class CartTest{

    private lateinit var webDriver: WebDriver
    private lateinit var cart: Cart
    private lateinit var actions: Actions
    private lateinit var loginForm: LoginForm

    @BeforeClass
    fun setup() {
        System.setProperty("webdriver.chrome.driver", Constants.WEB_DRIVER_LOCATION)
        webDriver = ChromeDriver()
        cart = Cart(webDriver)
        loginForm = LoginForm(webDriver)
        actions = Actions(webDriver)
        webDriver.manage().window().maximize()
        webDriver.get(Constants.BASE_URL)
    }

    @Test(priority = 1)
//    fun addProductToCart() {
//        Assert.assertTrue(cart.getTShirtsBtn().isDisplayed)
//        cart.getTShirtsBtn().click()
//        Assert.assertEquals(cart.getProductCount().size, 1)
//        actions.moveToElement(cart.getProductByIndex(1)).perform()
//        Assert.assertTrue(cart.getAddToCartBtn().isDisplayed)
//        actions.moveToElement(cart.getAddToCartBtn()).perform()
//        actions.click(cart.getAddToCartBtn()).build().perform()
//        actions.moveToElement(cart.getContinueShoppingBtn()).perform()
//        actions.click(cart.getContinueShoppingBtn()).build().perform()
//        actions.moveToElement(cart.getShoppingCartDropdown()).perform()
//    }

    fun addProductToCart() {
        Assert.assertTrue(cart.getTShirtsBtn().isDisplayed)
        actions.moveToElement(cart.getTShirtsBtn()).perform()
        cart.getTShirtsBtn().click()
        Assert.assertTrue(cart.getProductByIndex(1).isDisplayed)
        Assert.assertEquals(cart.getProductCount().size, 1)
        actions.moveToElement(cart.getProductByIndex(1)).perform()
        actions.moveToElement(cart.getAddToCartBtn()).perform()
        Assert.assertTrue(cart.getAddToCartBtn().isDisplayed)
        actions.click(cart.getAddToCartBtn()).build().perform()
        actions.moveToElement(cart.getContinueShoppingBtn()).perform()
        actions.click(cart.getContinueShoppingBtn()).build().perform()
        Assert.assertTrue(cart.getContinueShoppingBtn().isDisplayed)
        actions.moveToElement(cart.getShoppingCartDropdown()).perform()
        Assert.assertEquals(cart.getCartItemsCount()!!.size, 1)
    }

    @Test(priority = 2)
    fun checkout() {
        actions.moveToElement(cart.getShoppingCartDropdown()).perform()
        actions.moveToElement(cart.getCheckoutBtn()).perform()
        actions.click(cart.getCheckoutBtn()).build().perform()
        Assert.assertTrue(cart.getShoppingSummary().isDisplayed)
        Assert.assertEquals(cart.getCartProductCount().size, 1)
        Assert.assertEquals(cart.getCartProductTotals().text, "\$16.51")
    }

    @Test(priority = 3)
    fun loginToContinue() {
        cart.getProceedToCheckoutButton().click()
        Assert.assertTrue(loginForm.getLoginForm().isDisplayed)
        loginForm.setEmail("user@testuser.com")
        loginForm.setPassword("123Hello")
        loginForm.getLoginButton().click()
        cart.getProceedToShippingBtn().click()
    }

    @Test(priority = 4)
    fun acceptTermsAndConditions() {
        cart.getTermsAndConditionCheckbox().click()
        cart.getProceedToShippingBtn().click()
    }

    @Test(priority = 5)
    fun payViaWire() {
        cart.getPayByWireButton().click()
    }

    @Test(priority = 6)
    fun confirmOrder() {
        cart.getConfirmOrderBtn().click()
        Assert.assertTrue(cart.getOrderCompleteMessage().isDisplayed)
    }

}