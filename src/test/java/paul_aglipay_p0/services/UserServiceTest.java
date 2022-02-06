package paul_aglipay_p0.services;

import static org.junit.Assert.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import paul_aglipay_p0.daos.UserDAO;

public class UserServiceTest {

	UserService sut;
	UserDAO mockUserDAO;
	
	@Before
	public void setUp() throws Exception {
		mockUserDAO = mock(UserDAO.class);
		sut = new UserService(mockUserDAO);
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
