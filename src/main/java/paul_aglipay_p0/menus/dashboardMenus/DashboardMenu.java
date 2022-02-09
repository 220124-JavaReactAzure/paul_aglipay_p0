package paul_aglipay_p0.menus.dashboardMenus;

import java.io.BufferedReader;

import paul_aglipay_p0.menus.Menu;
import paul_aglipay_p0.models.User;
import paul_aglipay_p0.services.UserService;
import paul_aglipay_p0.util.MenuRouter;

public class DashboardMenu extends Menu {
	
	private final UserService userService;
	
	public DashboardMenu(BufferedReader consoleReader, MenuRouter router, UserService userService) {
		super("Dashboard", "/dashboard", consoleReader, router);
		this.userService = userService;
	}
	
	public void render() throws Exception {

		User sessionUser = userService.getSessionUser();

		System.out.println("Welcome " + sessionUser.getFirstName() + " " + sessionUser.getLastName());
		System.out.println("What woud you like to do?");
		
		String menu = "1) View/edit my profile information\n" + 
				"2) Edit/create account(s)\n" +
				"3) View my accounts\n" + 
				"4) Logout\n" + 
				 "> ";

		System.out.print(menu);

		String userSelection = consoleReader.readLine();

		switch (userSelection) {
		case "1":
//			System.out.println("View/edit profile selected");
			router.transfer("/user-profile-edit");
			break;
		case "2":
//			System.out.println("View/edit/create account selected");
			router.transfer("/create-account");
			break;
		case "3":
//			System.out.println("View of My Account(s):");
			router.transfer("/my-accounts");
			break;
		case "4":
			System.out.println("Logging Out...");
			userService.logout();
			router.transfer("/welcome");
			break;
		default:
			System.out.println("The user made an invalid selection");
		}
	}

}
