package paul_aglipay_p0.menus.dashboardMenus;

import java.io.BufferedReader;

import paul_aglipay_p0.menus.Menu;
import paul_aglipay_p0.models.Account;
import paul_aglipay_p0.services.AccountService;
import paul_aglipay_p0.util.MenuRouter;

public class AccountCreationMenu extends Menu {
	private final AccountService accountService;
	
	public AccountCreationMenu(BufferedReader consoleReader, MenuRouter router, AccountService accountService) {
		super("AccountCreation", "/create-account", consoleReader, router);
		this.accountService = accountService;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void render() throws Exception {
		System.out.println("Account Creator\n" + "Fill out the attributes below");
		
		System.out.println("Please provide an account description: ");
		String accountDescription = consoleReader.readLine();
		System.out.println("Initialize the amount: ");
		String accountAmount = consoleReader.readLine();
		
		Account newAccount = new Account(accountDescription, accountAmount);
		
		accountService.createAccount(newAccount);
		router.transfer("/account");
	}

}
