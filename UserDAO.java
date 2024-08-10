package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.User;

public class UserDAO {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/データベース名";
	private static final String JDBC_USER = "";//ユーザー名
	private static final String JDBC_PASSWORD = "";//パスワード

	// ユーザーリストを取得するメソッド
	public List<User> getUsersWithNoAdmin() throws SQLException {
		List<User> users = new ArrayList<>();
		String sql = "SELECT id, name FROM sample WHERE isAdmin = 0";

		try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet resultSet = statement.executeQuery()) {

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				users.add(new User(id, name));
			}
		}
		return users;
	}

	public List<User> getUsersWithAdmin() throws SQLException {
		List<User> users = new ArrayList<>();
		String sql = "SELECT id, name FROM sample WHERE isAdmin = 1";

		try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet resultSet = statement.executeQuery()) {

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				users.add(new User(id, name));
			}
		}
		return users;
	}}
