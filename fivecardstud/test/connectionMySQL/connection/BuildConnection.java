package connectionMySQL.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BuildConnection {
	public static void main(String[] args) throws Exception
	{
		String url = "jdbc:mysql://127.0.0.1:3306/mysql";
		String userName = "root";
		String passWord = "123456";
		String queryString = "select * from user";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(url,userName,passWord);
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(queryString);
		
		while(rs.next())
		{
			System.out.print(""+rs.toString());
		}
		
	}
}
