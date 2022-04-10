package dbmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {

	String MSISDN = "";
	
	
	
	public  String getMSISDN(String column) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");

		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@10.64.19.141:1521/SIBUAT01", "Siebel",
				"siebel!123");

		Statement stmt = con.createStatement();

		String query = "select MSISDN from interfaces.CRM_BATCH_PRE_ACTIVATION a, siebel.cx_num_mstr b where  a.PRICE_PLAN_NAME = 'Zero Air Time'  and a.registered = 'N'  and a.batch_id = B.PRE_ACTIVATION_ID  and B.NUMBER_STRING = a.msisdn  and a.expiry_date > sysdate  and batch_id like '%AK-409%'  and WARE_HOUSE_OUT is not null  AND ROWNUM < 2"
				+ "order by  a.Batch_ID desc";

		ResultSet res = stmt.executeQuery(query);
		while (res.next()) {
			MSISDN = res.getString("MSISDN");
		}
		con.close();
		return MSISDN;
	}
}
