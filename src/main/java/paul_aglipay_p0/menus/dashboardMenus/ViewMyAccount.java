package paul_aglipay_p0.menus.dashboardMenus;

import java.io.BufferedReader;

import paul_aglipay_p0.menus.Menu;
import paul_aglipay_p0.models.User;
import paul_aglipay_p0.services.AccountService;
import paul_aglipay_p0.services.UserService;
//import paul_aglipay_p0.services.AccountService;
import paul_aglipay_p0.util.MenuRouter;

public class ViewMyAccount extends Menu {
	private final AccountService accountService;
	private final UserService userService;
	public ViewMyAccount(BufferedReader consoleReader, MenuRouter router, UserService userService, AccountService accountService) {
		super("MyAccounts", "/my-accounts", consoleReader, router);
		this.userService = userService;
		this.accountService = accountService;
	}
	
	@Override
	public void render() throws Exception {
		User sessionUser = userService.getSessionUser();
		System.out.println("Account(s) View for: \n");
		System.out.println("Welcome " + sessionUser.getFirstName() + " " + sessionUser.getLastName());
		
		accountService.getAccounts();
		
		System.out.println("Is Ok?");
		String okVar = consoleReader.readLine();
		System.out.println("Is Ok: " + okVar);
	}

}
