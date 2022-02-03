package paul_aglipay_p0.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import paul_aglipay_p0.models.User;
import paul_aglipay_p0.util.collections.List;
import paul_aglipay_p0.util.datasource.ConnectionFactory;

public class UserDAO implements CrudDAO<User> {

	@Override
	public User create(User newUser) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			newUser.setId(UUID.randomUUID().toString());

			String sql = "insert into Users (User_id, first_name, last_name, email, username, password) values (?, ?, ?, ?, ?, ?)";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, newUser.getId());
			ps.setString(2, newUser.getFirstName());
			ps.setString(3, newUser.getLastName());
			ps.setString(4, newUser.getEmail());
			ps.setString(5, newUser.getUsername());
			ps.setString(6, newUser.getPassword());

			int rowsInserted = ps.executeUpdate();

			if (rowsInserted != 0) {
				return newUser;
			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public User findByUsername(String username) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "select * from Users where username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User User = new User();
				User.setId(rs.getString("id"));
				User.setFirstName(rs.getString("first_name"));
				User.setLastName(rs.getString("last_name"));
				User.setEmail(rs.getString("email"));
				User.setUsername(rs.getString("username"));
				User.setPassword(rs.getString("password"));

				return User;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean update(User updatedObj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}

}
