package paul_aglipay_p0.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import paul_aglipay_p0.daos.AccountDAO;
import paul_aglipay_p0.daos.TransactionDAO;
import paul_aglipay_p0.daos.UserDAO;
import paul_aglipay_p0.menus.dashboardMenus.AccountCreationMenu;
import paul_aglipay_p0.menus.dashboardMenus.AccountMenu;
import paul_aglipay_p0.menus.dashboardMenus.DashboardMenu;
import paul_aglipay_p0.menus.dashboardMenus.UserProfileEdit;
import paul_aglipay_p0.menus.dashboardMenus.ViewMyAccount;
import paul_aglipay_p0.menus.startPages.LoginMenu;
import paul_aglipay_p0.menus.startPages.RegisterMenu;
import paul_aglipay_p0.menus.startPages.WelcomeMenu;
import paul_aglipay_p0.services.AccountService;
import paul_aglipay_p0.services.TransactionService;
import paul_aglipay_p0.services.UserService;
import paul_aglipay_p0.util.logging.Logger;

public class AppState {

	private final Logger logger;
	private static boolean isRunning;
	private final MenuRouter router;
	
	public AppState() {
		
		logger = Logger.getLogger(true);
		logger.log("Application is initiliazing.....");
		
		isRunning = true;
		router = new MenuRouter();
		BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

		UserDAO userDAO = new UserDAO();
		AccountDAO accountDAO = new AccountDAO();
		TransactionDAO transactionDAO = new TransactionDAO();
		
		UserService userService = new UserService(userDAO);
		AccountService accountService = new AccountService(accountDAO, userService);
		TransactionService transactionService = new TransactionService(transactionDAO, accountService);
		
		router.addMenu(new WelcomeMenu(consoleReader, router));
		router.addMenu(new RegisterMenu(consoleReader, router, userService));
		router.addMenu(new LoginMenu(consoleReader, router, userService));
		router.addMenu(new DashboardMenu(consoleReader, router, userService));
		router.addMenu(new AccountCreationMenu(consoleReader, router, accountService));
		router.addMenu(new ViewMyAccount(consoleReader, router, userService, accountService));
		router.addMenu(new AccountMenu(consoleReader, router, userService, accountService, transactionService));
		router.addMenu(new UserProfileEdit(consoleReader, router, userService));

		logger.log("Application initiliazed!!! We do did it!~WOOO~");
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
