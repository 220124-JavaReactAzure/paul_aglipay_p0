package paul_aglipay_p0.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import paul_aglipay_p0.models.Account;
import paul_aglipay_p0.util.collections.List;
import paul_aglipay_p0.util.datasource.ConnectionFactory;

public class AccountDAO implements CrudDAO<Account> {
	public List<Account> findAccountByCreatorId(String id){
		return null;
	}

	@Override
	public Account create(Account account) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			account.setId(UUID.randomUUID().toString());

			String sql = "insert into accounts (id, description, amount, user_id) values (?, ?, ?, ?)";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, account.getId());
			ps.setString(2, account.getDescription());
			ps.setString(3, account.getAmount());
			ps.setString(4, account.getUser().getId());

			int rowsInserted = ps.executeUpdate();

			if (rowsInserted != 0) {
				return account;
			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Account updatedObj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}
}
