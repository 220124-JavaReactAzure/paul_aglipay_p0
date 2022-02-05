package paul_aglipay_p0.models;

public class Transaction {
	private String id;
	private String description;
	private String amount;
	private Account account;
	
	

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Transaction(String description, String amount) {
		super();
		this.description = description;
		this.amount = amount;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}
