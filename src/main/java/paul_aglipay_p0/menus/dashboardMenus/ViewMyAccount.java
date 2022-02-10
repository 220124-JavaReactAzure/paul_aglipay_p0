package paul_aglipay_p0.menus.dashboardMenus;

import java.io.BufferedReader;
import java.text.DecimalFormat;
import java.util.ArrayList;

import paul_aglipay_p0.menus.Menu;
import paul_aglipay_p0.models.Account;
import paul_aglipay_p0.models.Transaction;
import paul_aglipay_p0.models.User;
import paul_aglipay_p0.services.AccountService;
import paul_aglipay_p0.services.UserService;
//import paul_aglipay_p0.services.AccountService;
import paul_aglipay_p0.util.MenuRouter;

public class ViewMyAccount extends Menu {
	private final AccountService accountService;
	private final UserService userService;

	public ViewMyAccount(BufferedReader consoleReader, MenuRouter router, UserService userService,
			AccountService accountService) {
		super("MyAccounts", "/my-accounts", consoleReader, router);
		this.userService = userService;
		this.accountService = accountService;
	}

	@Override
	public void render() throws Exception {
		User sessionUser = userService.getSessionUser();

		System.out.println("Account(s) View for: \n" + sessionUser.getFirstName() + " " + sessionUser.getLastName());
		System.out.println("Loading...");
		int rowNum = 0;
		ArrayList<Account> accountTable = accountService.getAccounts();

		System.out.println(
				"---------------------------------------------------------------------------------------------");
		System.out.printf("%7s %5s %10s ", "Select", "DESCRIPTION", "AMOUNT");
		System.out.println("");
				
		for (Account accountRow : accountTable) {

			DecimalFormat twoPlaces = new DecimalFormat("0.00");
			String accountRow_getAmount = "$" + String.valueOf(twoPlaces.format(Double.parseDouble(accountRow.getAmount())));
			System.out.format("%7s %7s %14s", String.valueOf(rowNum + 1), accountRow.getDescription(), accountRow_getAmount );			
			System.out.println("");
			accountService.setSessionAccount(accountRow);
			rowNum++;

		}

		System.out.println(
				"---------------------------------------------------------------------------------------------");

		if (accountTable.size() > 0) {
			System.out.println("Please select by number:");
			String okVar = consoleReader.readLine();
			Account accountRow = accountTable.get(Integer.parseInt(okVar) - 1);
			accountService.setSessionAccount(accountRow);
			router.transfer("/account");
		} else {

			System.out.println("You have no accounts.");
		}
	}

}
