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
	public User findByEmailAndPassword(String email, String password) {

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "select * from users where email = ? and upassword = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				User User = new User();
				User.setId(rs.getString("id"));
				User.setFirstName(rs.getString("first_name"));
				User.setLastName(rs.getString("last_name"));
				User.setEmail(rs.getString("email"));
//				User.setUsername(rs.getString("username"));
//				User.setPassword(rs.getString("password"));

				return User;
			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public User create(User newUser) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			newUser.setId(UUID.randomUUID().toString());

//			String sql = "insert into Users (User_id, first_name, last_name, email, username, password) values (?, ?, ?, ?, ?, ?)";
			String sql = "insert into Users (id, first_name, last_name, email, upassword) values (?, ?, ?, ?, ?)";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, newUser.getId());
			ps.setString(2, newUser.getFirstName());
			ps.setString(3, newUser.getLastName());
			ps.setString(4, newUser.getEmail());
			ps.setString(5, newUser.getPassword());

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
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "select * from Users where id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setId(rs.getString("id"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
//				User.setUsername(rs.getString("username"));
//				User.setPassword(rs.getString("password"));

				return user;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public User findByEmail(String email) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "select * from Users where email = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setId(rs.getString("id"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
//				User.setUsername(rs.getString("username"));
//				User.setPassword(rs.getString("password"));

				return user;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean update(User updatedObj) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			//UPDATE table_name	SET column1 = value1, column2 = value2,	WHERE condition;
			String sql = "UPDATE Users SET first_name = ?, last_name = ?, email = ? WHERE id = ?";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, updatedObj.getFirstName());
			ps.setString(2, updatedObj.getLastName());
			ps.setString(3, updatedObj.getEmail());
			ps.setString(4, updatedObj.getId());

			int rowsInserted = ps.executeUpdate();

			if (rowsInserted != 0) {
				return true;
			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}

}
