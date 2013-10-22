package werewolf.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.encoding.PasswordEncoder;

import edu.wm.service.GameService;
import edu.wm.something.PlayerRowMapper;
import edu.wm.something.domain.MyUser;

public class PostgresUserDAO implements IUserDAO {
	
	private static PostgresDAO postgresDao = new PostgresDAO();
	static Logger logger = Logger.getLogger(PostgresUserDAO.class.getName());
	private static JdbcTemplate jdbcTemplate;
	private GameService gameService = new GameService();
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public void createUser(MyUser user) throws IOException {
		File f = new File(getClass().getResource("/users.sql").getFile());
        String sqlStr = loadContents(f);
        System.out.println(sqlStr);
		String hashedPass = passwordEncoder.encodePassword(user.getPassword(), "INREOUHG984N5V9V98N54W8");
		user.setHashedPassword(hashedPass);
		user.setKills(0);
		user.setScore(0);
		user.setGamesPlayed(0);
        
        try{
        	//create the werewolf table
        	jdbcTemplate.execute(sqlStr);
        	logger.info("users table being created");
        } catch (Exception e){
        	//Do nothing
        	logger.info("users table already created");
        }
        
        jdbcTemplate.execute("INSERT INTO USERS"
				+ "(USERNAME, HASHED_PASSWORD, KILLS, GAMES_PLAYED, SCORE) " + "VALUES"
				+ "("+user.getUsername()+",'"+user.getHashedPassword() +"',"+0+","+0+","+0+");");
		
	}

	@Override
	public void deleteUser(MyUser user) {
			String username = user.getUsername();
        	jdbcTemplate.execute("DELETE FROM USERS WHERE USERNAME = "+username+";");
        	logger.info("Deleted user "+username);

		
	}

	@Override
	public void restartGame() throws IOException {
		//Drop the old werewolf table
		String dropSQL = "DROP TABLE WEREWOLF;";
		jdbcTemplate = postgresDao.getJdbcTemplate();
		jdbcTemplate.execute(dropSQL);
		
		File f = new File(getClass().getResource("/werewolf.sql").getFile());
        String sqlStr = loadContents(f);
        System.out.println(sqlStr);

       //create the werewolf table, now empty
        jdbcTemplate.execute(sqlStr);
        logger.info("werewolf being created");
        
        //set as day to start the new game
        gameService.setNight(false);
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

	@Override
	public void login(String username, String password) {
		// TODO Auto-generated method stub
		
	}
		
	

}
