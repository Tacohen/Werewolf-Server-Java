package werewolf.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.sql.DataSource;

//import javax.activation.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class PostgresDAO {
	
	private boolean databaseBuilt;
	//private Connection connection;
	private JdbcTemplate jdbcTemplate;
	static Logger logger = Logger.getLogger(PostgresDAO.class.getName());

	 
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	/**
	public Connection getPostgresConnection(){
		return connection;		
	}

	public boolean isDatabaseBuilt() {
		return databaseBuilt;
	}*/

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
		/**
		try {

			//Class.forName("org.postgresql.Driver");
			Class.forName("org.springframework.jdbc");

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
         
         //connection.setAutoCommit(false);
         
         
         ///connection.nativeSQL(sqlStr);
         ///connection.prepareStatement(connection.nativeSQL(sqlStr));
         java.sql.Statement statement = connection.createStatement();
         statement.execute(sqlStr);
         statement.close();
         //connection.commit();
         
         System.out.println(connection.toString());
			
			
		} else {
			System.out.println("Failed to make connection!");
		}*/
		
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://127.0.0.1:5432/testing");
		dataSource.setUsername("postgres");
		dataSource.setPassword("letmeindb");
		//dataSource.getConnection("postgres", "letmeindb");
		setDataSource(dataSource);
		
		File f = new File(getClass().getResource("/werewolf.sql").getFile());
        String sqlStr = loadContents(f);
        System.out.println(sqlStr);
        
        //DatabaseMetaData md = connection.getMetaData();
        //ResultSet rs = md.getTables(null, null, "WEREWOLF", null);
        /**
        if (rs.next()) {
          //Table Exists, so no need to set it up
        	logger.info("werewolf table not created");
        }
        else{
        	//create the werewolf table
        	jdbcTemplate.execute(sqlStr);
        	logger.info("werewolf table not created");
        }*/
        
        //jdbcTemplate.execute(sqlStr);
        
        //connection.setAutoCommit(false);
        
        
        ///connection.nativeSQL(sqlStr);
        ///connection.prepareStatement(connection.nativeSQL(sqlStr));
        //java.sql.Statement statement = connection.createStatement();
        //statement.execute(sqlStr);
        //statement.close();
        //connection.commit();
        
        //System.out.println(connection.toString());
		
	}
	
	public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
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
