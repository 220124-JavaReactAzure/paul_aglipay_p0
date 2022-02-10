package paul_aglipay_p0.menus.startPages;

import java.io.BufferedReader;

import paul_aglipay_p0.exceptions.AuthenticationException;
import paul_aglipay_p0.menus.Menu;
import paul_aglipay_p0.services.UserService;
import paul_aglipay_p0.util.MenuRouter;

public class LoginMenu extends Menu {

	private final UserService userService;

	public LoginMenu(BufferedReader consoleReader, MenuRouter router, UserService userService) {
		super("Login", "/login", consoleReader, router);
		this.userService = userService;
	}

	@Override
	public void render() throws Exception {
		System.out.println("Please enter your credentials for you account.");
		System.out.print("Email: ");
		String username = consoleReader.readLine();
	     System.out.print("Password: ");
	     String password = consoleReader.readLine();

		try {
			userService.authenticateUser(username, password);
			router.transfer("/dashboard");
		} catch (AuthenticationException e) {
			System.out.println("Incorrect credentials provided! No matching user account found.");
		}

	}

}
