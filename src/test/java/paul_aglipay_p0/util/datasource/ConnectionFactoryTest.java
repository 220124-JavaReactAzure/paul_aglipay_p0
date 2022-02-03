package paul_aglipay_p0.util.datasource;


import java.sql.Connection;

import org.junit.Assert;
import org.junit.Test;


public class ConnectionFactoryTest {

	@Test
	public void test_getConnection_returnValidConnection_givenProvidenCredentials() {
		try (Connection conn = ConnectionFactory.getInstance().getConnection();){
			System.out.println(conn);
			Assert.assertNotNull(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
