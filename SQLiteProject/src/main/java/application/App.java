package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");

		String dbUrl = "jdbc:sqlite:people.db";
		Connection conn = DriverManager.getConnection(dbUrl);
		Statement stmt = conn.createStatement();

		String query = "create table if not exists user (id integer primary key, name text not null)";
		stmt.execute(query);
		
		query = "insert into user (id, name) values (0, 'Bob')";
		stmt.execute(query);
		
		query = "insert into user (id, name) values (1, 'Mary')";
		stmt.execute(query);
		
		query = "select id, name from user";
		ResultSet result = stmt.executeQuery(query);
		
		while(result.next()) {
			int id = result.getInt("id");
			String name = result.getString("name");
			
			System.out.println(id + ": " + name);
		}
		
		query = "drop table user";
		stmt.execute(query);
		
		stmt.close();
		conn.close();
	}

}
