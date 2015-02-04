import java.sql.*;


public class DB2 
{
	public static void main(String[] args) throws ClassNotFoundException
	{
		Connection conn = null;
		try 
		{
			//Load the jdbc-odbc bridge driver
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			//Attempt to connect to a driver
			//conn = DriverManager.getConnection("jdbc:derby:C:\\Users\\ME\\Sample");
			 conn = DriverManager.getConnection("jdbc:derby:derbyDB;create=true");
			//create a statement object for submitting to driver
			//stmt = conn.createStatement();
			System.out.println("DB is open ");
			
			String testString = "This string";
			Double testDouble = 0.5;
			
			Statement stmt = conn.createStatement();   
		//	String QueryString = "CREATE TABLE Table1   (test varchar(" + 100 +"),column0 double )";
			String InsertQuery = "INSERT INTO table1 (test, column0) VALUES ('" + testString + "', 0.5)";
			
		
			int updateQuery = 0;			
			try
			{
				updateQuery = stmt.executeUpdate(InsertQuery);
				if (updateQuery != 0)
				{
					System.out.println("table is created successfully and " + updateQuery
							+ " row is inserted.");
				}
			}
			catch(SQLException e)
			{
				while(e != null)
				{
					System.out.println("SQLException: " + e.getMessage());
					e = e.getNextException();
				}
			}
			
			String QueryString = "SELECT * FROM table1";
			ResultSet rs = stmt.executeQuery(QueryString);
			
			while(rs.next())
			{
				for(int i = 0; i < 5; i++)
				{
					String string = rs.getString("test");
					double D = rs.getDouble("column0");
					System.out.println(string + " " + D);
				}
			}
			
			
			
			System.out.println("table1  created");
			conn.close();
		}
		catch(SQLException e)
		{
			while(e != null)
			{
				System.out.println("SQL Exception: " + e.getMessage() );
				e = e.getNextException();
			}
		}
	}

}
