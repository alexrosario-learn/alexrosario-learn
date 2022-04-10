package stepDefenitions;


import org.testng.Assert;

import com.factory.DriverFactory;
import com.pages.LoginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginPageSteps {
	private LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
	private String title = "";
	
	@Given("user is on login page")
	public void user_is_on_login_page() {
	 DriverFactory.getDriver()
	 .get("http://10.64.19.138:6100/ecommunications_enu/start.swe?");
	 System.out.println("Inside login");
	}

	@When("user gets the title of the page")
	public void user_gets_the_title_of_the_page() {
		  title = loginPage.getLoginPageTitle();
		  System.out.println("Login Page title is" + title);
	}

	@Then("page title should be {string}")
	public void page_title_should_be(String expectedTitleName) {
		//   Assert.assertTrue(title.contains(expectedTitleName));
		System.out.println("Inside home page");
	}

	@When("user enters username {string}")
	public void user_enters_username(String username) {
	 loginPage.enterUserName(username);
	}

	@When("user enters password {string}")
	public void user_enters_password(String password) {
		loginPage.enterPassword(password);
	}

	@When("user clicks on Login button")
	public void user_clicks_on_login_button() {
	   loginPage.clickOnLogin();
	}


}
