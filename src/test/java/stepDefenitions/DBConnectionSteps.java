package stepDefenitions;

import java.sql.SQLException;

import com.pages.HomePage;

import io.cucumber.java.en.Given;

public class DBConnectionSteps {
	private HomePage homepage;
	
	@Given("I fetch MSISDN from CRM DB")
	public void i_fetch_msisdn_from_crm_db() {
		try {
			String msisdn = homepage.getMsisdn();
			System.out.println("KPK number from DB " + msisdn);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
