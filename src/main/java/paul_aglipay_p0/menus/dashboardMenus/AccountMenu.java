package paul_aglipay_p0.menus.dashboardMenus;

import static paul_aglipay_p0.util.AppState.shutdown;

import java.io.BufferedReader;
import java.util.ArrayList;

import paul_aglipay_p0.menus.Menu;
import paul_aglipay_p0.models.Account;
import paul_aglipay_p0.models.Transaction;
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

		Account sessionAccount = accountService.getSessionAccount();
		System.out.println("View for: Account\n");

		System.out.println(sessionAccount.getDescription() + " - " + sessionAccount.getAmount());

		System.out.println("transactionsTable: ");
		ArrayList<Transaction> transactionsTable = transactionService.getTransactionsByAccount(sessionAccount.getId());
		for (Transaction tt : transactionsTable) {
			System.out.println(tt.getDescription() + " - " + tt.getAmount());
		}
		
		ArrayList<String> actions = new ArrayList<>();
		actions.add("Send");
		actions.add("Logout");
		
		String menu = "1) Send\n" + 
				"2) Logout\n" + 
				 "> ";

		System.out.print(menu);
//		String userSelection = consoleReader.readLine();
//
//		switch (userSelection) {
//		case "1":
//			System.out.println("transactionDescription?");
//			String transactionDescription = consoleReader.readLine();
//			
//			System.out.println("transactionAmount?");
//			String transactionAmount = consoleReader.readLine();
//			
//			Transaction newTransaction = new Transaction(transactionDescription, transactionAmount);
//			
//			transactionService.createTransaction(newTransaction);
//			
//			System.out.println("Is Ok?");
//			String okVar = consoleReader.readLine();
//			System.out.println("Is Ok: " + okVar);
//			break;
//		case "2":
//			router.transfer("/register");
//			break;
//		case "3":
//			shutdown();
//			break;
//		default:
//			System.out.println("What on earth are you trying to tell me to do?!?!");
//			break;
//		}



	}

}
