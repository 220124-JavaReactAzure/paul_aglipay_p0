package paul_aglipay_p0.services;

import java.util.ArrayList;

import paul_aglipay_p0.daos.TransactionDAO;
import paul_aglipay_p0.exceptions.InvalidRequestException;
import paul_aglipay_p0.exceptions.ResourcePersistenceException;
import paul_aglipay_p0.models.Transaction;

public class TransactionService {

	private TransactionDAO transactionDAO;
	private final AccountService accountService;

	public TransactionService(TransactionDAO transactionDAO, AccountService accountService) {
		this.accountService = accountService;
		this.transactionDAO = transactionDAO;
	}

	public ArrayList<Transaction> getTransactionsByAccount(String id) {
		ArrayList<Transaction> userAccounts = transactionDAO.findByAccountId(id);
		return userAccounts;
	}

	public void createTransaction(Transaction newTransaction) {
		if (!isTransactionValid(newTransaction)) {
			throw new InvalidRequestException("The Transaction was provided invalid information");
		}

		newTransaction.setAccount(accountService.getSessionAccount());
		Transaction createdTransaction = transactionDAO.create(newTransaction);

		if (createdTransaction == null) {
			throw new ResourcePersistenceException("The Transaction could not be persisted");
		}
	}
	
	private boolean isTransactionValid(Transaction newTransaction) {

		if (newTransaction == null)
			return false;
		if (newTransaction.getDescription() == null || newTransaction.getDescription().trim().equals(""))
			return false;
//		if(newAccount.getAmount() == null || newAccount.getAmount().trim().equals("") || Integer.valueOf(newAccount.getAmount()) > 20 || Integer.valueOf(newAccount.getAmount()) < 0) return false;
		return newTransaction.getAmount() != null || !newTransaction.getAmount().trim().equals("");
	}
}
