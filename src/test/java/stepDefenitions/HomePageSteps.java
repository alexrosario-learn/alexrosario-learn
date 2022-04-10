package stepDefenitions;

import java.util.List;
import java.util.Map;

import com.factory.DriverFactory;
import com.pages.HomePage;
import com.pages.LoginPage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class HomePageSteps {
	private LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
	private HomePage homePage;
	public static String kpkNum = "";
	
	
	@Given("User Login into Seibel CRM Application with valid credentials")
	public void user_login_into_seibel_crm_application_with_valid_credentials(DataTable credTable) {
		List<Map<String, String>> credList = credTable.asMaps();
		String userName = credList.get(0).get("username");
		String password = credList.get(0).get("password");

		DriverFactory.getDriver()
				.get("http://10.64.19.138:6100/ecommunications_enu/start.swe?");
		homePage = loginPage.doLogin(userName, password);
	}

	@Given("User is on Seibel CRM Home screen")
	public void user_is_on_seibel_crm_home_screen() {
		System.out.println("inside CRM home screen");
		String title ="Siebel Web Call Center Home";
		homePage.verifyTitle(title);
	    System.out.println("Verify Home screen is success");
	}

	@Given("User verify Retailer Balance for {string} in CRM")
	public void user_verify_retailer_balance_for_in_crm(String QRcode) throws InterruptedException {
	    homePage.verifyPreTupsBalance(QRcode);
	}

	@When("User fetch one valid KPK number from CRM Database")
	public void user_fetch_one_valid_kpk_number_from_crm_database() {
	   kpkNum = homePage.fetchKPKNumFromDB();
	}

	
	@When("User Launch NSGUI Application")
	public void user_launch_nsgui_application() {
	   System.out.println("Inside NSGUI Application");
	}

	@When("User Submit short message with {string} and {int} and {int}")
	public void user_submit_short_message_with_and_and(String string, Integer int1, Integer int2) {
	   System.out.println("Inside short message NS");
	}

	/*
	 * @When("User verify Retailer Balance for <QRCode> in CRM") public void
	 * user_verify_retailer_balance_for_qr_code_in_crm() { System.out.println(); }
	 */

	@When("User verify Price plan name for KPK CRM Subscription Menu")
	public void user_verify_price_plan_name_for_kpk_crm_subscription_menu() {
	   homePage.VerifyPricePlanName(kpkNum);
	}

	@When("User Logout of Siebel CRM Application")
	public void user_logout_of_siebel_crm_application() {
	    System.out.println("inside logout block of CRM");
	}
}
