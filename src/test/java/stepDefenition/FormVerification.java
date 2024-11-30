package stepDefenition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.junit.Assert;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.List;

public class FormVerification {
    public ChromeDriver driver;
    String username = "Hina";
    String password = "Hina";

    @When("user redirect to website {string}")
    public void user_redirect_to_website(String website) {
        System.setProperty("webdriver.chrome.driver", "Driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(website);
    }

    @Then("Verify that the text input element with xpath {string} is disabled in the form")
    public void verify_that_the_text_input_element_with_xpath_input_name_is_disabled_in_the_form(String xpath) {
        WebElement disabledElement = driver.findElement(By.xpath(xpath));
        System.out.println("element is enabled or not "+disabledElement.isEnabled());
        Assert.assertFalse("The element should be disabled", disabledElement.isEnabled());
    }

    @Then("Verify that the text input with value {string} is in readonly state by using two xpaths")
    public void verify_that_the_text_input_with_value_is_in_readonly_state_by_using_xpaths(String inputValue) {
    	
    	//using xpath1
        WebElement readOnlyInput = driver.findElement(By.xpath("//input[@value='" + inputValue + "']"));
        String readOnlyAttribute = readOnlyInput.getAttribute("readonly");
        
        System.out.println("element is readonly "+readOnlyAttribute);
        Assert.assertTrue("The input field should be readonly, but it's not.", Boolean.parseBoolean(readOnlyAttribute));
        
        //using xpth2
        WebElement readOnlyInput2 = driver.findElement(By.xpath("//input[@name='my-readonly']"));
        String readOnlyAttribute2 = readOnlyInput2.getAttribute("readonly");
        Assert.assertTrue("The input field should be readonly, but it's not.", Boolean.parseBoolean(readOnlyAttribute2));
    }

    @Then("Verify that the dropdown field to select color is having {int} elements using two xpaths")
    public void verify_that_the_dropdown_field_to_select_color_is_having_elements_using_xpaths(Integer expectedCount) {
    	
    	//using xpath1
    	
    	WebElement selectElement = driver.findElement(By.className("form-select"));
        Select select = new Select(selectElement);
        List<WebElement> options = select.getOptions();
        System.out.println("color dropdown size"+ options.size());
        Assert.assertEquals("Dropdown element count should be 8", expectedCount.intValue(), options.size());
        
        //using xpath2
        WebElement selectElement2 = driver.findElement(By.name("my-select"));
        Select select2 = new Select(selectElement2);
        List<WebElement> options2 = select2.getOptions();
        System.out.println("color dropdown size"+ options2.size());
        Assert.assertEquals("Dropdown element count does not match", expectedCount.intValue(), options2.size());
    }

    @Then("Verify that the submit button is disabled when no data is entered in Name field")
    public void verify_that_the_submit_button_is_disabled_when_no_data_is_entered_in_name_field() {
        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
        Assert.assertFalse("Submit button should be disabled", submitButton.isEnabled());
    }

    @Then("Verify that the submit button enabled when both Name and Password field is entered")
    public void verify_that_the_submit_button_enabled_when_both_name_and_password_field_is_entered() {
        WebElement nameField = driver.findElement(By.name("my-name"));
        WebElement passwordField = driver.findElement(By.id("my-password-id"));
        WebElement submitButton = driver.findElement(By.id("submit-form"));

        nameField.sendKeys(username);
        passwordField.sendKeys(password);
        Assert.assertTrue("Submit button should be enabled.", submitButton.isEnabled());
    }

    @Then("Verify that on submit of {string} button the page shows {string} text")
    public void verify_that_on_submit_of_button_the_page_shows_text(String buttonName, String expectedText) {
        WebElement submitButton = driver.findElement(By.xpath("//button[text()='" + buttonName + "']"));
        submitButton.click();

        WebElement receivedText = driver.findElement(By.xpath("//*[text()='" + expectedText + "']"));
        Assert.assertTrue("The expected text is not displayed.", receivedText.isDisplayed());
    }

    @Then("Verify that on submit of form all the data passed to the URL")
    public void verify_that_on_submit_of_form_all_the_data_passed_to_the_url() {
        String currentURL = driver.getCurrentUrl();
        Assert.assertTrue("URL does not contain expected data.",
                currentURL.contains("name=" + username) && currentURL.contains("password=" + password));
        driver.close();
    }
}
