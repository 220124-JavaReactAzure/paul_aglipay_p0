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
		System.out.println("\n");
		System.out.println("Account Name: " + sessionAccount.getDescription() + " - Total Amount: $" + sessionAccount.getAmount());
		System.out.println(
				"---------------------------------------------------------------------------------------------");
		System.out.printf("%5s %10s ", "DESCRIPTION", "AMOUNT");
		System.out.println();
		ArrayList<Transaction> transactionsTable = transactionService.getTransactionsByAccount(sessionAccount.getId());
		for (Transaction tt : transactionsTable) {
			System.out.format("%7s %14s", tt.getDescription(), tt.getAmount());
			System.out.println();
		}
		System.out.println(
				"---------------------------------------------------------------------------------------------");

		ArrayList<String> actions = new ArrayList<>();
		actions.add("Send");
		actions.add("Logout");

		boolean toDo = true;
		while (toDo) {
			String menu = "1) Send\n" + "2) View my account(s)\n" + "3) Dashboard\n" + "4) Home\n" + "> ";

			System.out.print(menu);
			String userSelection = consoleReader.readLine();

			switch (userSelection) {
			case "1":

				boolean processTransactions = true;
				while (processTransactions) {
					System.out.println("Transaction Description: ");
					String transactionDescription = consoleReader.readLine();

					System.out.println("Transaction Amount: ");
					String transactionAmount = consoleReader.readLine();

					System.out.println("transactionDescription: " + transactionDescription + "\ntransactionAmount: "
							+ transactionAmount);

					System.out.println("Is Ok?(y)");
					String okVar = consoleReader.readLine();
					if (okVar.equals("y")) {
						Transaction newTransaction = new Transaction(transactionDescription, transactionAmount);
						transactionService.createTransaction(newTransaction);
					} else {
						System.out.println("Cancelled");

					}
					System.out.println("View for: Account\n");
					System.out.println(sessionAccount.getDescription() + " - " + sessionAccount.getAmount());

					System.out.println(
							"---------------------------------------------------------------------------------------------");
					System.out.printf("%5s %10s ", "DESCRIPTION", "AMOUNT");
					System.out.println();
					ArrayList<Transaction> transactionsTableAfter2 = transactionService
							.getTransactionsByAccount(sessionAccount.getId());
					for (Transaction tt : transactionsTableAfter2) {
						System.out.format("%7s %14s", tt.getDescription(), tt.getAmount());
						System.out.println();
					}
					System.out.println(
							"---------------------------------------------------------------------------------------------");

					System.out.println("Process Another?(y)");
					String processAnother = consoleReader.readLine();
					
					if (processAnother.equals("y")) {
						processTransactions = true;
					} else {
						processTransactions = false;
					}
				}

				break;
			case "2":
				toDo = false;
				router.transfer("/my-accounts");
				break;
			case "3":
				toDo = false;
				router.transfer("/dashboard");
				break;
			case "4":
				toDo = false;
				break;
			default:
				System.out.println("What on earth are you trying to tell me to do?!?!");
				break;
			}

			router.transfer("/dashboard");
		}

	}

}
