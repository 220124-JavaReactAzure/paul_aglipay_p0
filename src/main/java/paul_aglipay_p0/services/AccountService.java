package paul_aglipay_p0.services;

import java.util.ArrayList;

import paul_aglipay_p0.daos.AccountDAO;
import paul_aglipay_p0.daos.UserDAO;
import paul_aglipay_p0.exceptions.InvalidRequestException;
import paul_aglipay_p0.exceptions.ResourcePersistenceException;
import paul_aglipay_p0.models.Account;
import paul_aglipay_p0.models.User;
import paul_aglipay_p0.util.collections.LinkedList;
import paul_aglipay_p0.util.logging.Logger;

public class AccountService {
	private final AccountDAO accountDAO;
	private final UserService userService;
	private Account sessionAccount;
	private final Logger logger;

	public void setSessionAccount(Account sessionAccount) {
		this.sessionAccount = sessionAccount;
	}

	public AccountService(AccountDAO accountDAO, UserService userService) {
		this.accountDAO = accountDAO;
		this.userService = userService;
		this.sessionAccount = null;

		logger = Logger.getLogger(true);
		logger.log("AccountService is initiliazing.....");
	}

	public Account getSessionAccount() {
		return sessionAccount;
	}

	public void createAccount(Account newAccount) {
		if (!isAccountValid(newAccount)) {
			throw new InvalidRequestException("The Account was provided invalid information");
		}

		newAccount.setUser(userService.getSessionUser());
		Account createdAccount = accountDAO.create(newAccount);

		if (createdAccount == null) {
			throw new ResourcePersistenceException("The Account could not be persisted");
		} else {
			setSessionAccount(createdAccount);
		}
	}

	public void updateAccount(Account newAccount) {
		if (!isAccountValid(newAccount)) {
			throw new InvalidRequestException("The Account was provided invalid information");
		}

		newAccount.setUser(userService.getSessionUser());
		boolean createdAccount = accountDAO.update(newAccount);

		if (!createdAccount) {
			throw new ResourcePersistenceException("The Account could not be persisted");
		} else {
			setSessionAccount(newAccount);
		}		
		
	}

	public Account getAccountById(String id) {

		Account userAccount = accountDAO.findById(id);

		sessionAccount = userAccount;
		return sessionAccount;
	}

	public Account getAccountByIdNoSess(String id) {

		Account userAccount = accountDAO.findById(id);

		return userAccount;
	}

	public ArrayList<Account> getAccounts() {

		ArrayList<Account> userAccounts = accountDAO.findByUserId(userService.getSessionUser().getId());

		return userAccounts;
	}

	private boolean isAccountValid(Account newAccount) {
//		System.out.println("newAccount.getAmount(): " + newAccount.getAmount());
		logger.log("newAccount.getAmount(): " + newAccount.getAmount());
		if (newAccount == null)
			return false;
		if (newAccount.getDescription() == null || newAccount.getDescription().trim().equals(""))
			return false;
		if(newAccount.getAmount() == null || newAccount.getAmount().trim().equals("") || 
				Double.compare(Double.parseDouble(newAccount.getAmount()), 20000.00) > 0 || 
				Double.compare(Double.parseDouble(newAccount.getAmount()), 0.00) < 0 ) return false;
		return newAccount.getAmount() != null || !newAccount.getAmount().trim().equals("");
	}
}
