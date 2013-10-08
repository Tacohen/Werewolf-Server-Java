package werewolf.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PostgresDAO {
	
	private boolean databaseBuilt;
	private Connection connection;
	
	 
	
	public Connection getPostgresConnection(){
		return connection;		
	}

	public boolean isDatabaseBuilt() {
		return databaseBuilt;
	}

	public PostgresDAO(){
		super();
		this.databaseBuilt = false;
		try {
			connectToDatabase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setDatabaseBuilt(boolean databaseBuilt) {
		this.databaseBuilt = databaseBuilt;
	}
	
	private void connectToDatabase() throws IOException, SQLException{

		try {

			Class.forName("org.postgresql.Driver");

		} catch (ClassNotFoundException e) {

			System.out.println("Where is your PostgreSQL JDBC Driver? "
					+ "Include in your library path!");
			e.printStackTrace();
			return;

		}

		System.out.println("PostgreSQL JDBC Driver Registered!");

		connection = null;

		try {

			connection = DriverManager.getConnection(
					"jdbc:postgresql://127.0.0.1:5432/testing", "postgres",
					"letmeindb");

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;

		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");
			databaseBuilt= true;
		
         File f = new File(getClass().getResource("/werewolf.sql").getFile());
         String sqlStr = loadContents(f);
         System.out.println(sqlStr);
         
         connection.setAutoCommit(false);
         
         
         ///connection.nativeSQL(sqlStr);
         connection.prepareStatement(connection.nativeSQL(sqlStr));
         connection.commit();
         
         System.out.println(connection.toString());
			
			
		} else {
			System.out.println("Failed to make connection!");
		}

	}
	
	private String loadContents(File resource) throws IOException {
	    StringBuffer contents = new StringBuffer();
	    BufferedReader reader = new BufferedReader(new FileReader(resource));
	    String buf = null;
	    
	    while((buf = reader.readLine()) != null) {
	      contents.append(buf);
	    }
	    
	    reader.close();
	 
	    return contents.toString();
	  }

}
