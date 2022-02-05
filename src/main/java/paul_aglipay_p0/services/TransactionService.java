package paul_aglipay_p0.services;

import java.util.ArrayList;

import paul_aglipay_p0.daos.AccountDAO;
import paul_aglipay_p0.daos.TransactionDAO;
import paul_aglipay_p0.models.Account;
import paul_aglipay_p0.models.Transaction;

public class TransactionService {

	private TransactionDAO transactionDAO;

	public TransactionService(TransactionDAO transactionDAO) {
		this.transactionDAO = transactionDAO;
	}
	
	public ArrayList<Transaction> getTransactionsByAccount(String id) {

		ArrayList<Transaction> userAccounts = transactionDAO.findByAccountId(id);

		return userAccounts;
	}
}
