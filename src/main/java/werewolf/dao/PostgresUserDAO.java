package werewolf.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import edu.wm.service.GameService;
import edu.wm.something.PlayerRowMapper;
import edu.wm.something.domain.MyUser;

public class PostgresUserDAO implements IUserDAO {
	
	private static PostgresDAO postgresDao = new PostgresDAO();
	static Logger logger = Logger.getLogger(PostgresUserDAO.class.getName());
	private static JdbcTemplate jdbcTemplate;
	private PlayerRowMapper playerRowMapper = new PlayerRowMapper();
	@Autowired private GameService gameService;

	@Override
	public void createUser(MyUser user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(MyUser user) {
		// TODO Auto-generated method stub
		
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
		
	

}
