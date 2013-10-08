package werewolf.dao;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import Exceptions.NoPlayerFoundException;
import Exceptions.NoPlayersException;
import edu.wm.service.PlayerService;
import edu.wm.something.domain.GPSLocation;
import edu.wm.something.domain.Player;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;


public class PostgresPlayerDAO extends SimpleJdbcDaoSupport implements IPlayerDAO{
	
	private static PostgresDAO postgresDao = new PostgresDAO();
	static Logger logger = Logger.getLogger(PostgresPlayerDAO.class.getName());
	private static JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Player> getAllAlive() {
		logger.info("In PostgresPLayerDAO");
		//Connection connection = postgresDao.getPostgresConnection();
		String insertTableSQL = "INSERT INTO WEREWOLF"
				+ "(PLAYER_ID, PLAYER_NAME, LAT) " + "VALUES"
				+ "(1,'tim',40)";
		/**
		try {
			java.sql.Statement statement = connection.createStatement();
			// execute insert SQL stetement
			statement.executeUpdate(insertTableSQL);
			System.out.println("Record is inserted into WEREWOLF table!");
			statement.close();
			//connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		jdbcTemplate = postgresDao.getJdbcTemplate();
		jdbcTemplate.execute(insertTableSQL);

	
		return null;
	}

	@Override
	public void setDead(Player p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertPlayer(Player p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Player getPlayerById(String id) throws NoPlayerFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPlayerLocation(String id, GPSLocation loc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Player> getAllPlayers() throws NoPlayersException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePlayer(Player p) throws NoPlayerFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePlayer(Player p) throws NoPlayerFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Player getPlayerById(int id) throws NoPlayerFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void voteOnPlayer(Player p) throws NoPlayerFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Player getPlayerPic(int id) throws NoPlayerFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player getPlayerInfo(int id) throws NoPlayerFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createPlayer(Player p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void killPlayer(Player p) throws NoPlayerFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Player> getAllPlayersNear(Player p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void movePlayer(Player p, double lat, double lng) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
