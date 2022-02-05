package paul_aglipay_p0.daos;

import static org.junit.Assert.*;

import org.junit.Test;

import paul_aglipay_p0.exceptions.InvalidRequestException;
import paul_aglipay_p0.models.User;
import paul_aglipay_p0.services.UserService;

public class UserDAOTest {

	@Test
	public void testCreate() {

		UserDAO userDao = new UserDAO();
		User user = new User("Paul", "A", "pa@mail.com");
		userDao.create(user);
		

		try {
		} catch (InvalidRequestException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace(); 
			System.out.println("YOU HAVE PROVIDED INVALID DATA PLEASE TRY AGAIN\n\n\n");

		}
	}

//	@Test
//	public void testFindByUsername() {
//		fail("Not yet implemented");
//	}

}
