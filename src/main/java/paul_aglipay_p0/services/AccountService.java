package paul_aglipay_p0.services;

import paul_aglipay_p0.daos.AccountDAO;
import paul_aglipay_p0.daos.UserDAO;
import paul_aglipay_p0.exceptions.InvalidRequestException;
import paul_aglipay_p0.exceptions.ResourcePersistenceException;
import paul_aglipay_p0.models.Account;

public class AccountService {
	private final AccountDAO accountDAO;
	private final UserService userService;
	
	public AccountService(AccountDAO accountDAO, UserService userService) {
		this.accountDAO = accountDAO;
		this.userService = userService;
	}
	
	public void createAccount(Account newAccount) {
		if(!isAccountValid(newAccount)) {
			throw new InvalidRequestException("The Account was provided invalid information");
		}
		
		newAccount.setUser(userService.getSessionUser());
		Account createdAccount = accountDAO.create(newAccount);
		
		if(createdAccount == null) {
			throw new ResourcePersistenceException("The Account could not be persisted");
		}
	}
	
	private boolean isAccountValid(Account newAccount) {
		
		if(newAccount == null) return false;
		if(newAccount.getDescription() == null || newAccount.getDescription().trim().equals("")) return false;
		if(newAccount.getAmount() == null || newAccount.getAmount().trim().equals("") || Integer.valueOf(newAccount.getAmount()) > 20 || Integer.valueOf(newAccount.getAmount()) < 0) return false;
		return newAccount.getAmount() != null || !newAccount.getAmount().trim().equals("");
	}
}
