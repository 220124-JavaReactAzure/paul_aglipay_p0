package paul_aglipay_p0.menus.dashboardMenus;

import java.io.BufferedReader;

import paul_aglipay_p0.exceptions.InvalidRequestException;
import paul_aglipay_p0.menus.Menu;
import paul_aglipay_p0.models.User;
import paul_aglipay_p0.services.UserService;
import paul_aglipay_p0.util.MenuRouter;

public class UserProfileEdit extends Menu {
	UserService userService;

	public UserProfileEdit(BufferedReader consoleReader, MenuRouter router, UserService UserService) {
		super("UserProfileEdit", "/user-profile-edit", consoleReader, router);
		this.userService = UserService;
	}

	@Override
	public void render() throws Exception {
		System.out.println("Hi, " + userService.getSessionUser().getFirstName() + " "
				+ userService.getSessionUser().getLastName());
		System.out.println(
				"---------------------------------------------------------------------------------------------");

		System.out.println("Full Name: " + userService.getSessionUser().getFirstName() + " "
				+ userService.getSessionUser().getLastName());
		System.out.println("Email: " + userService.getSessionUser().getEmail());

		System.out.println("Would you like to make come changes?(y/n)");
		String userSelection = consoleReader.readLine();

		switch (userSelection) {
		case "y":
			System.out.println("Please provided us with some basic information");
			System.out.print("First Name: ");
			String firstName = consoleReader.readLine();

			System.out.print("Last Name: ");
			String lastName = consoleReader.readLine();

			System.out.print("Email: ");
			String email = consoleReader.readLine();

			System.out.print("Password: ");
			String password = consoleReader.readLine();

			User user = new User(firstName, lastName, email, password);
			
			try {
				userService.updateUser(user);
			} catch (InvalidRequestException e) {
				// e.printStackTrace();
				System.out.println("YOU HAVE PROVIDED INVALID DATA PLEASE TRY AGAIN\n\n\n");
				router.transfer("/dashboard");
			}
			break;
		case "n":
			System.out.println("The user selected no");
		default:
			System.out.println("The user made an invalid selection");
		}
		System.out.println(
				"---------------------------------------------------------------------------------------------");

		System.out.println("Full Name: " + userService.getSessionUser().getFirstName() + " "
				+ userService.getSessionUser().getLastName());
		System.out.println("Email: " + userService.getSessionUser().getEmail());
		router.transfer("/dashboard");

	}

}
