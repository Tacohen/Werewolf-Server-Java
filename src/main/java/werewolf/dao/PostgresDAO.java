package werewolf.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.sql.DataSource;

//import javax.activation.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class PostgresDAO {
	
	private boolean databaseBuilt;
	//private Connection connection;
	private JdbcTemplate jdbcTemplate;
	static Logger logger = Logger.getLogger(PostgresDAO.class.getName());
	
	/**
	 * Db settings when run on localhost
	 * static String host ="127.0.0.1";
		static String port  = "5432";
		static String dbName = "testing";
		static String username = "postgres";
		static String password = "letmeindb";
	 */
	/**
	 * Db settings on Heroku
	 */
	static String host ="ec2-50-19-228-92.compute-1.amazonaws.com";
	static String port  = "5432";
	static String dbName = "d93sjhhsidb08s";
	static String username = "ngqmjlahecmnwa";
	static String password = "5RciJ5xWzvVx4oIJtEw3jreH8R";
	 
	
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
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://"+host+":"+port+"/"+dbName);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		setDataSource(dataSource);
		
		File f = new File(getClass().getResource("/werewolf.sql").getFile());
        String sqlStr = loadContents(f);
        System.out.println(sqlStr);
        
        try{
        	//create the werewolf table
        	jdbcTemplate.execute(sqlStr);
        	logger.info("werewolf being created");
        } catch (Exception e){
        	//Do nothing
        	logger.info("werewolf already created");
        }
        
        /**
         * Note to Timothy: find some way to check if table 'Werewolf" has 
         * already been created, and create iff it has not. Right now, it simply
         * assumes the table has been created. And that's terrible
         */
        
        
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
        
		
	}
	
	@Autowired
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
