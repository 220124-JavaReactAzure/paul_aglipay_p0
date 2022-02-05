package paul_aglipay_p0.menus.dashboardMenus;

import java.io.BufferedReader;

import paul_aglipay_p0.menus.Menu;
import paul_aglipay_p0.services.AccountService;
import paul_aglipay_p0.services.UserService;
import paul_aglipay_p0.util.MenuRouter;

public class ViewMyTransactions extends Menu {
	public ViewMyTransactions(BufferedReader consoleReader, MenuRouter router, UserService userService,
			AccountService accountService) {
		super("ViewMyTransactions", "/my-transactions", consoleReader, router);
//		this.userService = userService;
//		this.accountService = accountService;
	}

	@Override
	public void render() throws Exception {
		// TODO Auto-generated method stub

	}

}
