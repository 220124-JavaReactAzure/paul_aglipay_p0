package paul_aglipay_p0.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import paul_aglipay_p0.models.Account;
import paul_aglipay_p0.models.Transaction;
import paul_aglipay_p0.util.collections.List;
import paul_aglipay_p0.util.datasource.ConnectionFactory;

public class TransactionDAO implements CrudDAO<Transaction> {

	@Override
	public Transaction create(Transaction transaction) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			transaction.setId(UUID.randomUUID().toString());

			String sql = "insert into transactions (id, description, amount, user_id) values (?, ?, ?, ?)";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, transaction.getId());
			ps.setString(2, transaction.getDescription());
			ps.setString(3, transaction.getAmount());
			ps.setString(4, transaction.getAccount().getId());

			int rowsInserted = ps.executeUpdate();

			if (rowsInserted != 0) {
				return transaction;
			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Transaction> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Transaction updatedObj) {
		// TODO Auto-generated method stub
		return false;

	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public ArrayList<Transaction> findByAccountId(String accountId) {

		ArrayList<Transaction> results = new ArrayList<>();
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "select * from transactions where account_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, accountId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Transaction transaction = new Transaction();
				transaction.setId(rs.getString("id"));
				transaction.setDescription(rs.getString("description"));
				transaction.setAmount(rs.getString("amount"));

				results.add(transaction);
//				return account;
			}
			return results;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
