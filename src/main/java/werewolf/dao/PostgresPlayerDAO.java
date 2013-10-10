package werewolf.dao;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import Exceptions.NoPlayerFoundException;
import Exceptions.NoPlayersException;
import edu.wm.something.PlayerRowMapper;
import edu.wm.something.domain.GPSLocation;
import edu.wm.something.domain.Player;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

@SuppressWarnings({"unused", "unchecked"})
public class PostgresPlayerDAO extends SimpleJdbcDaoSupport implements IPlayerDAO{
	
	private static PostgresDAO postgresDao = new PostgresDAO();
	static Logger logger = Logger.getLogger(PostgresPlayerDAO.class.getName());
	private static JdbcTemplate jdbcTemplate;
	private PlayerRowMapper playerRowMapper = new PlayerRowMapper();
	

	@Override
	public List<Player> getAllAlive() {
		logger.info("In PostgresPLayerDAO");
		
		String getPlayers = "SELECT * FROM WEREWOLF WHERE IS_DEAD=FALSE;";
		jdbcTemplate = postgresDao.getJdbcTemplate();
		//List<Player> p = jdbcTemplate.queryForObject(getPlayers, new PlayerRowMapper());
		 //List list = jdbcTemplate.queryForList(getPlayers);
		List<Player> result = jdbcTemplate.query(getPlayers, new PlayerRowMapper());
		 return result;
	}

	@Override
	public void setDead(Player p) {
		String setDeadSQL = "UPDATE WEREWOLF"
					+"SET IS_DEAD = TRUE "
					+"WHERE PLAYER_ID = "+ p.getUserID() +";";
		jdbcTemplate = postgresDao.getJdbcTemplate();
		jdbcTemplate.execute(setDeadSQL);
		
	}

	@Override
	public void insertPlayer(Player p) {
		logger.info("adding player, in postgresplayerdao");
		
		String insertPlayerSQL = "INSERT INTO WEREWOLF"
				+ "(PLAYER_ID, PLAYER_NAME, LAT, LNG, IS_DEAD, IS_WEREWOLF, NUM_VOTES_AGAINST, PLAYER_PIC) " + "VALUES"
				+ "("+p.getUserID()+",'"+ p.getId() +"',"+ p.getLat()+","+p.getLng()+","+p.isDead()+","+p.isWereWolf()
				+ ","+p.getVoteCount()+","+"'picture'"+")";
		jdbcTemplate = postgresDao.getJdbcTemplate();
		logger.info("sql query was: "+insertPlayerSQL);
		jdbcTemplate.execute(insertPlayerSQL);
		
	}

	@Override
	public Player getPlayerById(String id) throws NoPlayerFoundException {
		String getPlayerById = "SELECT * FROM WEREWOLF WHERE PLAYER_ID = "+id+";";
		jdbcTemplate = postgresDao.getJdbcTemplate();
		Player p =(Player) jdbcTemplate.queryForObject(getPlayerById, new PlayerRowMapper());
		return p;
	}

	@Override
	public void setPlayerLocation(String id, GPSLocation loc) {
		String locationSQL = "UPDATE WEREWOLF "
				+"SET (LAT,LNG) = ("+loc.getLat()+","+loc.getLng()+") "
				+"WHERE PLAYER_ID = "+ id +";";
		jdbcTemplate = postgresDao.getJdbcTemplate();
		jdbcTemplate.execute(locationSQL);
		
	}

	@Override
	public List<Player> getAllPlayers() throws NoPlayersException {
		String getPlayers = "SELECT * FROM WEREWOLF;";
		jdbcTemplate = postgresDao.getJdbcTemplate();
		BeanPropertyRowMapper<Player> b =new BeanPropertyRowMapper<Player>(Player.class);
		List<Player> p = (List<Player>) jdbcTemplate.queryForObject(getPlayers, b);
		return p;
	}

	@Override
	public void deletePlayer(Player p) throws NoPlayerFoundException {
		String delete = "DELETE FROM WEREWOLF WHERE PLAYER_ID = "+p.getUserID()+";";
		jdbcTemplate = postgresDao.getJdbcTemplate();
		jdbcTemplate.execute(delete);
		
	}

	@Override
	public void updatePlayer(Player p) throws NoPlayerFoundException {
		String delete = "DELETE FROM WEREWOLF WHERE PLAYER_ID = "+p.getUserID()+";";
		jdbcTemplate = postgresDao.getJdbcTemplate();
		jdbcTemplate.execute(delete);
	}

	@Override
	public Player getPlayerById(double id) throws NoPlayerFoundException {
		String getPlayerById = "SELECT * FROM WEREWOLF WHERE PLAYER_ID = "+id+";";
		jdbcTemplate = postgresDao.getJdbcTemplate();
		Player p =(Player) jdbcTemplate.queryForObject(getPlayerById, new PlayerRowMapper());
		return p;
	}

	@Override
	public void voteOnPlayer(Player p) throws NoPlayerFoundException {
		String votePlayerSQL = "UPDATE WEREWOLF "
				+"SET NUM_VOTES_AGAINST = NUM_VOTES + 1 "
				+"WHERE PLAYER_ID = "+ p.getUserID() +";";
		jdbcTemplate = postgresDao.getJdbcTemplate();
		jdbcTemplate.execute(votePlayerSQL);
		
	}

	@Override
	public Player getPlayerPic(double id) throws NoPlayerFoundException {
		String getPlayerById = "SELECT * FROM WEREWOLF WHERE PLAYER_ID = "+id+";";
		jdbcTemplate = postgresDao.getJdbcTemplate();
		Player p =(Player) jdbcTemplate.queryForObject(getPlayerById, new PlayerRowMapper());
		return p;
	}

	@Override
	public Player getPlayerInfo(double id) throws NoPlayerFoundException {
		String getPlayerById = "SELECT * FROM WEREWOLF WHERE PLAYER_ID = "+id+";";
		jdbcTemplate = postgresDao.getJdbcTemplate();
		Player p =(Player) jdbcTemplate.queryForObject(getPlayerById, new PlayerRowMapper());
		return p;
	}

	@Override
	public void createPlayer(Player p) {
		String createPlayer = "INSERT INTO WEREWOLF (PLAYER_ID, PLAYER_NAME, LAT, LNG, IS_DEAD, IS_WEREWOLF, NUM_VOTES_AGAINST, PLAYER_PIC) VALUES ("
				+p.getUserID()+",'"+p.getId()+"',"+p.getLat()+","+p.getLng()+","+p.isDead()+","+p.isWereWolf()+","+p.getVoteCount()+",'"+p.getUserID()+"');";
		jdbcTemplate = postgresDao.getJdbcTemplate();
		jdbcTemplate.execute(createPlayer);
	}

	@Override
	public void killPlayer(Player p) throws NoPlayerFoundException {
		String killPlayerSQL = "UPDATE WEREWOLF "
				+"SET IS_DEAD = TRUE "
				+"WHERE PLAYER_ID = "+ p.getUserID() +";";
		jdbcTemplate = postgresDao.getJdbcTemplate();
		jdbcTemplate.execute(killPlayerSQL);
		
	}

	@Override
	public List<Player> getAllPlayersNear(Player p) {
		double playerLat = p.getLat();
		double playerLng = p.getLng();
		//(1/1380) degrees latitude ~ 0.05 miles
		double maxLat = playerLat+(1/1380);
		double minLat = playerLat-(1/1380);
		//(1/1100) degrees longitude ~ 0.05 miles
		double maxLng = playerLng+(1/1100);
		double minLng = playerLng-(1/1100);
		String getClosePlayers = "SELECT * FROM WEREWOLF WHERE ((LAT < "+maxLat+" AND LAT > "+
		minLat+") AND (LNG < "+maxLng+" AND LNG > "+minLng+"));";
		jdbcTemplate = postgresDao.getJdbcTemplate();
		BeanPropertyRowMapper<Player> b =new BeanPropertyRowMapper<Player>(Player.class);
		List<Player> players = (List<Player>) jdbcTemplate.queryForObject(getClosePlayers, b);
		return players;
	}

	@Override
	public void movePlayer(Player p, double lat, double lng) {
		String movePlayerSQL = "UPDATE WEREWOLF SET (LAT,LNG) = ("
		+lat+","+lng+") WHERE PLAYER_ID = "+p.getUserID()+";";
		logger.info("moving player, sql query is: "+movePlayerSQL);
		jdbcTemplate = postgresDao.getJdbcTemplate();
		jdbcTemplate.execute(movePlayerSQL);
		
	}
	
	
	

}
