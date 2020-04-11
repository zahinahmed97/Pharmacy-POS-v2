package pharmacy;

import java.sql.*;

public class SQLiteConnect {
        public static final String SQCONN= "jdbc:sqlite:pharmacy.sqlite";

	public static Connection getConnection()throws SQLException {
		
		try {
                    Class.forName("org.sqlite.JDBC");
                    return DriverManager.getConnection(SQCONN);
		} catch (ClassNotFoundException e) {
                    e.printStackTrace();
		}
		return null;
	}
}

	/**public int updateDB(String query) throws Exception {
		Statement statment = this.connection.createStatement();
		return statment.executeUpdate(query);
	}

	public ResultSet searchDB(String query) throws Exception {
		Statement statement = this.connection.createStatement();
		return statement.executeQuery(query);
	}

	public void close() {
		try {
			this.connection.close();
		} catch (Exception e) {
			System.out.println("Exception in close: " + e);
		}
		System.out.println("Closed");
	}
}**/