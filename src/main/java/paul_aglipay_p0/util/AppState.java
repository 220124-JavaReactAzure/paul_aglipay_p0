package paul_aglipay_p0.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import paul_aglipay_p0.daos.AccountDAO;
import paul_aglipay_p0.daos.UserDAO;
import paul_aglipay_p0.menus.dashboardMenus.AccountCreationMenu;
import paul_aglipay_p0.menus.dashboardMenus.AccountMenu;
import paul_aglipay_p0.menus.dashboardMenus.DashboardMenu;
import paul_aglipay_p0.menus.dashboardMenus.ViewMyAccount;
import paul_aglipay_p0.menus.startPages.LoginMenu;
import paul_aglipay_p0.menus.startPages.RegisterMenu;
import paul_aglipay_p0.menus.startPages.WelcomeMenu;
import paul_aglipay_p0.services.AccountService;
import paul_aglipay_p0.services.UserService;

public class AppState {

	{System.out.println("AppState");}
	

	private static boolean isRunning;
	private final MenuRouter router;
	
	public AppState() {
		isRunning = true;
		router = new MenuRouter();
		BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

		UserDAO userDAO = new UserDAO();
		AccountDAO accountDAO = new AccountDAO();
		
		UserService userService = new UserService(userDAO);
		AccountService accountService = new AccountService(accountDAO, userService);
		
		router.addMenu(new WelcomeMenu(consoleReader, router));
		router.addMenu(new RegisterMenu(consoleReader, router, userService));
		router.addMenu(new LoginMenu(consoleReader, router, userService));
		router.addMenu(new DashboardMenu(consoleReader, router, userService));
		router.addMenu(new AccountCreationMenu(consoleReader, router, accountService));
//		router.addMenu(new ViewMyAccount(consoleReader, router, accountService));
		router.addMenu(new ViewMyAccount(consoleReader, router, userService, accountService));
		router.addMenu(new AccountMenu(consoleReader, router, userService, accountService));
		
	}
	
	public void startup() {
		try {
			while(isRunning) {
				router.transfer("/welcome");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void shutdown() {
		isRunning = false;
	}

}
