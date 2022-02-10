package paul_aglipay_p0.services;

import java.text.DecimalFormat;
import java.util.ArrayList;

import paul_aglipay_p0.daos.TransactionDAO;
import paul_aglipay_p0.exceptions.InvalidRequestException;
import paul_aglipay_p0.exceptions.ResourcePersistenceException;
import paul_aglipay_p0.models.Account;
import paul_aglipay_p0.models.Transaction;
import paul_aglipay_p0.util.logging.Logger;

public class TransactionService {

	private TransactionDAO transactionDAO;
	private final AccountService accountService;
	private final Logger logger;
	public TransactionService(TransactionDAO transactionDAO, AccountService accountService) {
		this.accountService = accountService;
		this.transactionDAO = transactionDAO;
		
		logger = Logger.getLogger(true);
		logger.log("TransactionService is initiliazing.....");
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
		
		Account acSession = accountService.getSessionAccount();
		logger.log("newTransaction: " + newTransaction.getAmount());	
		DecimalFormat twoPlaces = new DecimalFormat("0.00");
		acSession.setAmount(String.valueOf(twoPlaces.format(Double.parseDouble(acSession.getAmount()) - Double.parseDouble(newTransaction.getAmount()))));		

		logger.log("acSession: " + acSession.getAmount());
		
		try {
			accountService.updateAccount(acSession);
		}catch(InvalidRequestException e) {
			System.out.println("InvalidRequestException: acSession" + acSession.getAmount() + e);
		}
		
	}

	public void receiveTransaction(Transaction newTransaction, Account sendToAccount) {
		if (!isTransactionValid(newTransaction)) {
			throw new InvalidRequestException("The Transaction was provided invalid information");
		}

		newTransaction.setAccount(sendToAccount);
		Transaction createdTransaction = transactionDAO.create(newTransaction);

		if (createdTransaction == null) {
			throw new ResourcePersistenceException("The Transaction could not be persisted");
		}
		
		Account acSession = sendToAccount;
		logger.log("newTransaction: " + newTransaction.getAmount());	
		DecimalFormat twoPlaces = new DecimalFormat("0.00");
		acSession.setAmount(String.valueOf(twoPlaces.format(Double.parseDouble(acSession.getAmount()) + Double.parseDouble(newTransaction.getAmount()))));		

		logger.log("acSession: " + acSession.getAmount());
		
		try {
			accountService.updateAccount(acSession);
		}catch(InvalidRequestException e) {
			System.out.println("InvalidRequestException: acSession" + acSession.getAmount() + e);
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
