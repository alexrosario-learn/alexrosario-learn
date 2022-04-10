package stepDefenitions;

import com.factory.DriverFactory;
import com.pages.LoginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class LoginPageCRMSteps {
	private LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
	
	private String title = "";

	@Given("User Login into Seibel CRM Application with valid username {string} and password {string}")
	public void user_login_into_seibel_crm_application_with_valid_username_and_password(String uname, String pwd
			) {
		 DriverFactory.getDriver()
		 .get("http://10.64.19.138:6100/ecommunications_enu/start.swe?");
		 loginPage.enterUserName(uname);
		 loginPage.enterPassword(pwd);
		 loginPage.clickOnLogin();
		 loginPage.waitForPageLoad();
		 title = loginPage.getLoginPageTitle();
		 System.out.println("Login TItle of the Page :" + title);
	}
}
