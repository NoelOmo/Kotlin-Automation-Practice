package ke.co.noel.pages

import ke.co.noel.utils.Constants
import ke.co.noel.utils.WebDriverUtils
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.Select

class SignupForm(_webDriver: WebDriver) {

    private var webDriver: WebDriver = _webDriver


    fun getAccountElement(elementId: String): WebElement {
        return WebDriverUtils.waitForElement(webDriver, By.id(elementId), Constants.WEB_DRIVER_WAIT)
    }

    fun getClickableAccountElementByXpath(xpath: String): WebElement {
        return WebDriverUtils.waitForElementToBeClickable(webDriver, By.xpath(xpath), Constants.WEB_DRIVER_WAIT)
    }

    fun getUserTitle(gender: String): WebElement? {
        return when(gender){
            Constants.MALE -> {
                WebDriverUtils.waitForElement(webDriver, By.id("uniform-id_gender1"), Constants.WEB_DRIVER_WAIT)
            }
            Constants.FEMALE -> {
                WebDriverUtils.waitForElement(webDriver, By.id("uniform-id_gender2"), Constants.WEB_DRIVER_WAIT)
            }
            else -> {
                null
            }
        }
    }

    fun getSelectDropDown(elementId: String): Select {
        return Select(WebDriverUtils.waitForElement(webDriver, By.id(elementId), Constants.WEB_DRIVER_WAIT))
    }

    fun getSubmitFormButton(): WebElement {
        return this.getClickableAccountElementByXpath("//*[@id=\"submitAccount\"]")
    }

    fun getDropdown(elementId: String): Select {
        val dayOfBirth = WebDriverUtils.waitForElement(webDriver, By.id(elementId), Constants.WEB_DRIVER_WAIT)
        return Select(dayOfBirth)
    }

    fun setDropDownValue(dropDown: Select, value: String) {
        dropDown.selectByValue(value)
    }

    fun getGenericErrorMessage(xpath: String): WebElement {
        return WebDriverUtils.waitForElement(webDriver, By.xpath(xpath), Constants.WEB_DRIVER_WAIT)
    }

    fun getCreateAccountBtn(): WebElement {
        return this.getClickableAccountElementByXpath("//button[@id=\"SubmitCreate\"]")
    }

    fun getHighlightedFormElement(xpath: String): WebElement {
        return WebDriverUtils.waitForElement(webDriver, By.xpath(xpath), Constants.WEB_DRIVER_WAIT)
    }


    fun getLoginForm() = this.getAccountElement("login_form")

    fun getCreateAccountForm() = this.getAccountElement("create-account_form")

    fun getCreateAccountEmail() = this.getAccountElement("email_create")

    fun getAccountRegistrationForm() = this.getAccountElement("account-creation_form")

    fun getPasswordField() = this.getAccountElement("passwd")

    fun getPostCodeField() = this.getAccountElement("postcode")

    fun getPhoneField() = this.getAccountElement("phone_mobile")

    fun getPhoneField2() = this.getAccountElement("phone")

    fun getAddress1Field() = this.getAccountElement("address1")

    fun getCityField() = this.getAccountElement("city")


    fun getCustomerLastName() = this.getAccountElement("customer_lastname")

    fun getCustomerFirstName() = this.getAccountElement("customer_firstname")

    fun getEmailField() = this.getAccountElement("email")

    fun getCountryDropdown() = this.getSelectDropDown("id_country")

    fun getAliasField() = this.getAccountElement("alias")

    fun getPhoneNumberError() = getGenericErrorMessage("//li[contains(text(), \"You must register at least one phone number.\")]")

    fun getLastNameError() = getGenericErrorMessage("//b[contains(text(), \"lastname\")]")

    fun getFirstNameError() = getGenericErrorMessage("//b[contains(text(), \"firstname\")]")

    fun getEmailRequiredError() = getGenericErrorMessage("//b[contains(text(), \"lastname\")]")

    fun getPasswordRequiredError() = getGenericErrorMessage("//b[contains(text(), \"passwd\")]")

    fun passwordInputError() = this.getHighlightedFormElement("//div[@class=\"required password form-group form-error\"]//input[@id=\"passwd\"]")

    fun emailInputError() = this.getHighlightedFormElement("//div[@class=\"required form-group form-error\"]//input[@id=\"email\"]")

    fun lastNameInputError() = this.getHighlightedFormElement("//div[@class=\"required form-group form-error\"]//input[@id=\"customer_lastname\"]")

    fun firstNameInputError() = this.getHighlightedFormElement("//div[@class=\"required form-group form-error\"]//input[@id=\"customer_firstname\"]")

    fun passwordInputOk() = this.getHighlightedFormElement("//div[@class=\"required password form-group form-ok\"]//input[@id=\"passwd\"]")

    fun emailInputOk() = this.getHighlightedFormElement("//div[@class=\"required form-group form-ok\"]//input[@id=\"email\"]")

    fun lastNameInputOk() = this.getHighlightedFormElement("//div[@class=\"required form-group form-ok\"]//input[@id=\"customer_lastname\"]")

    fun firstNameInputOk() = this.getHighlightedFormElement("//div[@class=\"required form-group form-ok\"]//input[@id=\"customer_firstname\"]")

    fun getCountryRequiredError() = this.getHighlightedFormElement("//b[contains(text(), \"id_country\")]")

    fun getAddressAliasRequiredError() = this.getHighlightedFormElement("//b[contains(text(), \"alias\")]")

    fun getAddressRequiredError() = this.getHighlightedFormElement("//b[contains(text(), \"address1\")]")

    fun getCityRequiredError() = this.getHighlightedFormElement("//b[contains(text(), \"city\")]")

    fun getCountryUnselectedError() = this.getHighlightedFormElement("//li[contains(text(), \"Country is invalid\")]")

    fun getStateRequiredError() = this.getHighlightedFormElement("//li[contains(text(), \"This country requires you to choose a State.\")]")

    fun getPostalCodeError() = this.getHighlightedFormElement("//li[contains(text(), \"The Zip/Postal code you\")]")
}