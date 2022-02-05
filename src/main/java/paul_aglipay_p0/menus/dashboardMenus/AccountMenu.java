package paul_aglipay_p0.menus.dashboardMenus;

import java.io.BufferedReader;

import paul_aglipay_p0.menus.Menu;
import paul_aglipay_p0.models.Account;
//import paul_aglipay_p0.models.Account;
import paul_aglipay_p0.models.User;
import paul_aglipay_p0.services.AccountService;
import paul_aglipay_p0.services.UserService;
import paul_aglipay_p0.util.MenuRouter;

public class AccountMenu extends Menu {
	private final AccountService accountService;
	private final UserService userService;

	public AccountMenu(BufferedReader consoleReader, MenuRouter router, UserService userService,
			AccountService accountService) {
		super("Account", "/account", consoleReader, router);
		this.userService = userService;
		this.accountService = accountService;
	}

	@Override
	public void render() throws Exception {
		User sessionUser = userService.getSessionUser();
		Account sessionAccount = accountService.getSessionAccount();
		System.out.println("View for: Account\n");
//		Account accountRow = accountService.getAccountById("a51bc22d-e9c6-4cab-a9c8-a3e80ecd10fc");
		
	
		System.out.println(sessionAccount.getDescription() + " - " + sessionAccount.getAmount());
		
//		System.out.println("Welcome " + sessionUser.getFirstName() + " " + sessionUser.getLastName());
//
//		for (Account accountRow : accountService.getAccounts()) {
//			System.out.println(accountRow.getDescription() + " - " + accountRow.getAmount());
//		}

		System.out.println("Is Ok?");
		String okVar = consoleReader.readLine();
		System.out.println("Is Ok: " + okVar);
		
	}

}
