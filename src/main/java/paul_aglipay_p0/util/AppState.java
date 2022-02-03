package paul_aglipay_p0.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import paul_aglipay_p0.menus.RegisterMenu;
import paul_aglipay_p0.menus.WelcomeMenu;
import paul_aglipay_p0.services.UserService;

public class AppState {

	{System.out.println("AppState");}
	

	private static boolean isRunning;
	private final MenuRouter router;
	
	public AppState() {
		isRunning = true;
		router = new MenuRouter();
		BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
		
		UserService userService = new UserService();
		router.addMenu(new WelcomeMenu(consoleReader, router));
		router.addMenu(new RegisterMenu(consoleReader, router, userService));
//		router.addMenu(new LoginMenu(consoleReader, router, UserService));
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
