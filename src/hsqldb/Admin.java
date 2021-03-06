package hsqldb;
import h2db.H2DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import utils.DB;
import utils.Debug;

public class Admin {

	static String USER = "sa";
	static String PW = "";

	public static void init()  {
		try {
			// initialize database
			Debug.log("initializing ...");
			Connection conn = DB.connectDB(HSQL.DRIVER, HSQL.DB_URL, USER, PW);
			dropTable(conn);
			createTable(conn);
	
		
			//DB.createUser(conn, "writer", "123");
			//DB.createUser(conn, "reader", "123");
			//DB.grantAllOnTableToPublic(conn, "REGISTRATION");

			insertRecords(conn);
			Debug.log("current table is");
			printAll(conn);
			DB.disconnectDB(conn);
			Debug.log("initialized ...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static void dropTable(Connection conn) throws Exception {
		Debug.log("Deleting table in given REGISTRATION...");
		
		Statement stmt = conn.createStatement();
		String sql = "DROP TABLE REGISTRATION IF EXISTS";
		stmt.executeUpdate(sql);
		
		Debug.log("Table  deleted in given REGISTRATION...");
	}

	static void createTable(Connection conn) throws Exception {
		Debug.log("Creating table in given REGISTRATION...");
		
		Statement stmt = conn.createStatement();
		String sql = "CREATE TABLE REGISTRATION " +
				"(id INTEGER not NULL, " +
				" first VARCHAR(255), " + 
				" last VARCHAR(255), " + 
				" age INTEGER, " + 
				" PRIMARY KEY ( id ))"; 
		stmt.executeUpdate(sql);

		Debug.log("Created table in given REGISTRATION...");	
	}

	static void insertRecords(Connection conn) throws Exception {
		Debug.log("Inserting records into the REGISTRATION table...");
		Statement stmt = conn.createStatement();

		String sql = "INSERT INTO Registration " +
				"VALUES (100, 'Zara', 'Ali', 18)";
		stmt.executeUpdate(sql);

		sql = "INSERT INTO Registration " +
				"VALUES (101, 'Mahnaz', 'Fatma', 25)";
		stmt.executeUpdate(sql);
		sql = "INSERT INTO Registration " +
				"VALUES (102, 'Zaid', 'Khan', 30)";
		stmt.executeUpdate(sql);
		sql = "INSERT INTO Registration " +
				"VALUES(103, 'Sumit', 'Mittal', 28)";
		stmt.executeUpdate(sql);
		Debug.log("Inserted records into the REGISTRATION table...");
	}

	static void updateRecords(Connection conn) throws Exception {
		Debug.log("Updating records ...");
		Statement stmt = conn.createStatement();
		String sql = "UPDATE Registration " +
				"SET age = 30 WHERE id in (100, 101)";
		stmt.executeUpdate(sql);

		// Now you can extract all the records
		// to see the updated records
		sql = "SELECT id, first, last, age FROM Registration";
		ResultSet rs = stmt.executeQuery(sql);

		while(rs.next()){
			// Retrieve by column name
			int id  = rs.getInt("id");
			int age = rs.getInt("age");
			String first = rs.getString("first");
			String last = rs.getString("last");

			// Display values
			Debug.log("ID: " + id + ", Age: " + age
					+ ", First: " + first + ", Last: " + last);
		}
		rs.close();
	}

	static void deleteRecords(Connection conn) throws Exception {
		Debug.log("Deleting records...");
		Statement stmt = conn.createStatement();
		String sql = "DELETE FROM Registration " +
				"WHERE id = 101";
		stmt.executeUpdate(sql);

		// Now you can extract all the records
		// to see the remaining records
		sql = "SELECT id, first, last, age FROM Registration";
		ResultSet rs = stmt.executeQuery(sql);

		while(rs.next()){
			// Retrieve by column name
			int id  = rs.getInt("id");
			int age = rs.getInt("age");
			String first = rs.getString("first");
			String last = rs.getString("last");

			// Display values
			Debug.log("ID: " + id + ", Age: " + age
					+ ", First: " + first + ", Last: " + last);
		}
		rs.close();
	}

	static void printAll(Connection conn) throws Exception {
		String sql = "SELECT * FROM Registration";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		while(rs.next()){
			// Retrieve by column name
			int id  = rs.getInt("id");
			int age = rs.getInt("age");
			String first = rs.getString("first");
			String last = rs.getString("last");

			// Display values
			Debug.log("ID: " + id + ", Age: " + age
					+ ", First: " + first + ", Last: " + last);
		}
	}
}
