package paul_aglipay_p0.models;

public class Account {

	private String id;
	private String description;
	private String amount;
	private User user;

	public Account(String description, String amount) {
		super();
		this.description = description;
		this.amount = amount;
		this.user = user;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
