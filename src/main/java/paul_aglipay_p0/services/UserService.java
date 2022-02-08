package paul_aglipay_p0.services;

import paul_aglipay_p0.daos.UserDAO;
import paul_aglipay_p0.exceptions.AuthenticationException;
import paul_aglipay_p0.exceptions.InvalidRequestException;
import paul_aglipay_p0.exceptions.ResourcePersistenceException;
import paul_aglipay_p0.models.User;

public class UserService {

	private final UserDAO userDao;
	private User sessionUser;

	public UserService(UserDAO userDao) {
		this.userDao = userDao;
		this.sessionUser = null;
	}

	public User getSessionUser() {
		return sessionUser;
	}

	public User registerNewUser(User newUser) {
		if (!isUserValid(newUser)) {
			throw new InvalidRequestException("Invalid user data provider");
		}

		boolean EmailAvailable = userDao.findByEmail(newUser.getEmail()) == null;
		boolean emailAvailable = userDao.findByEmail(newUser.getEmail()) == null;

		if (!EmailAvailable || !emailAvailable) {
			if (!EmailAvailable && emailAvailable) {
				throw new ResourcePersistenceException("The provided Email was already taken in the database");
			} else if (EmailAvailable) {
				throw new ResourcePersistenceException("The provided email was already taken in the database");
			} else {
				throw new ResourcePersistenceException(
						"The provided Email and email were already taken in the database");
			}
		}

		User persistedUser = userDao.create(newUser);

		if (persistedUser == null) {
			throw new ResourcePersistenceException("The User could not be persisted");
		}

		return persistedUser;
	}

	public User updateUser(User user) {
		if (!isUserValid(user)) {
			throw new InvalidRequestException("Invalid user data provider");
		}

//		boolean IdAvailable = userDao.findById(user.getId()) != null;
//		boolean idAvailable = userDao.findById(user.getId()) != null;
		
		boolean IdAvailable = true;
		boolean idAvailable = true;

		if (!IdAvailable || !idAvailable) {
			if (!IdAvailable && idAvailable) {
				throw new ResourcePersistenceException("The provided User does not exist in the database");
			} else if (IdAvailable) {
				throw new ResourcePersistenceException("The provided User does not exist in the database");
			} else {
				throw new ResourcePersistenceException(
						"User does not exist in the database");
			}
		}
		
		user.setId(sessionUser.getId());
		boolean persistedUser = userDao.update(user);

		if (!persistedUser) {
			throw new ResourcePersistenceException("The User could not be persisted");
		}
		
		sessionUser = user;

		return user;
	}

	public void authenticateUser(String email) {

		if (email == null || email.trim().equals("")) {
			throw new InvalidRequestException("Either email is an invalid entry. Please try logging in again");
		}

		User authenticatedUser = userDao.findByEmail(email);

		if (authenticatedUser == null) {
			throw new AuthenticationException(
					"Unauthenticated user, information provided was not found in our database.");
		}
		sessionUser = authenticatedUser;
	}

	public boolean isUserValid(User newUser) {
		if (newUser == null)
			return false;
		if (newUser.getFirstName() == null || newUser.getFirstName().trim().equals(""))
			return false;
		if (newUser.getLastName() == null || newUser.getLastName().trim().equals(""))
			return false;
//		if(newUser.getEmail() == null || newUser.getEmail().trim().equals("")) return false;
//		if(newUser.getEmail() == null || newUser.getEmail().trim().equals("")) return false;
//		return newUser.getPassword() != null || !newUser.getPassword().trim().equals("");

		return newUser.getEmail() != null || !newUser.getEmail().trim().equals("");

	}

	public void logout() {
		sessionUser = null;
	}

	public boolean isSessionActive() {
		return sessionUser != null;
	}
}
