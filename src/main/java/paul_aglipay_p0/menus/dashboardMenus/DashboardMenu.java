package paul_aglipay_p0.menus.dashboardMenus;

import java.io.BufferedReader;

import paul_aglipay_p0.menus.Menu;
import paul_aglipay_p0.services.UserService;
import paul_aglipay_p0.util.MenuRouter;

public class DashboardMenu extends Menu {
	UserService userService;
	public DashboardMenu(BufferedReader consoleReader, MenuRouter router, UserService userService) {
		super("Dashboard", "/dashboard", consoleReader, router);
		// TODO Auto-generated constructor stub
	}
	
	public void render() throws Exception {

		// TODO: Work on implementing sessions & dashboard functionality
		
		String menu = "1) View/edit my profile information\n" + 
				"2) View/edit/create monsters\n" + 
				"3) Logout\n" +
				"> ";

		System.out.print(menu);

		String userSelection = consoleReader.readLine();

		switch (userSelection) {
		case "1":
			System.out.println("View/edit profile selected");
			router.transfer("/user-profile-edit");
			break;
		case "2":
			System.out.println("View/edit/create monsters selected");
			break;
		case "3":
			// TODO: Implement logout of user account
			break;
		default:
			System.out.println("The user made an invalid selection");
		}
	}

}
