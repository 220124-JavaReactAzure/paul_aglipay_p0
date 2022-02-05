package paul_aglipay_p0.menus.dashboardMenus;

import java.io.BufferedReader;
import java.util.ArrayList;

import paul_aglipay_p0.daos.AccountDAO;
import paul_aglipay_p0.daos.TransactionDAO;
import paul_aglipay_p0.menus.Menu;
import paul_aglipay_p0.models.Account;
import paul_aglipay_p0.models.Transaction;
//import paul_aglipay_p0.models.Account;
import paul_aglipay_p0.models.User;
import paul_aglipay_p0.services.AccountService;
import paul_aglipay_p0.services.TransactionService;
import paul_aglipay_p0.services.UserService;
import paul_aglipay_p0.util.MenuRouter;

public class AccountMenu extends Menu {
	private final AccountService accountService;
	private final UserService userService;
	private final TransactionService transactionService;

	public AccountMenu(BufferedReader consoleReader, MenuRouter router, UserService userService,
			AccountService accountService, TransactionService transactionService) {
		super("Account", "/account", consoleReader, router);
		this.userService = userService;
		this.accountService = accountService;
		this.transactionService = transactionService;
	}

	@Override
	public void render() throws Exception {

		User sessionUser = userService.getSessionUser();
		Account sessionAccount = accountService.getSessionAccount();
		System.out.println("View for: Account\n");
//		Account accountRow = accountService.getAccountById("a51bc22d-e9c6-4cab-a9c8-a3e80ecd10fc");

		System.out.println(sessionAccount.getDescription() + " - " + sessionAccount.getAmount());

		System.out.println("transactionsTable: ");
		ArrayList<Transaction> transactionsTable = transactionService.getTransactionsByAccount("8");
		for (Transaction tt : transactionsTable) {
			System.out.println(tt.getDescription() + " - " + tt.getAmount());
		}

		System.out.println("Is Ok?");
		String okVar = consoleReader.readLine();
		System.out.println("Is Ok: " + okVar);

	}

}
