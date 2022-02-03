package paul_aglipay_p0.services;

import paul_aglipay_p0.exceptions.InvalidRequestException;
import paul_aglipay_p0.models.User;

public class UserService {
	public boolean registerNewUser(User newUser) {
		if(!isUserValid(newUser)) {
			throw new InvalidRequestException("Invalid user data provider");
		}

		// TODO: Write logic that verifies the new users information isn't duplicated int he system
//		UserDao.create(newUser);
		

		return true;
	}
	
	public boolean isUserValid(User newUser) {
		if(newUser == null) return false;
		if(newUser.getFirstName() == null || newUser.getFirstName().trim().equals("")) return false;
		if(newUser.getLastName() == null || newUser.getLastName().trim().equals("")) return false;
		if(newUser.getEmail() == null || newUser.getEmail().trim().equals("")) return false;
		if(newUser.getUsername() == null || newUser.getUsername().trim().equals("")) return false;
		return newUser.getPassword() != null || !newUser.getPassword().trim().equals("");


	}
}
