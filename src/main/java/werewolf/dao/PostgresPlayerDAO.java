package werewolf.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import Exceptions.NoPlayerFoundException;
import Exceptions.NoPlayersException;
import edu.wm.something.PlayerRowMapper;
import edu.wm.something.domain.GPSLocation;
import edu.wm.something.domain.Player;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;

@SuppressWarnings({"unused", "unchecked", "deprecation"})
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
		 List<Player> result = jdbcTemplate.query(getPlayers, new PlayerRowMapper());
		 return result;
	}

	@Override
	public void setDead(Player p) {
		String setDeadSQL = "UPDATE WEREWOLF "
					+"SET IS_DEAD = TRUE "
					+"WHERE PLAYER_ID = "+ p.getUserID() +";";
		jdbcTemplate = postgresDao.getJdbcTemplate();
		jdbcTemplate.execute(setDeadSQL);
		logger.info("set dead sql is: "+setDeadSQL);
		
	}

	@Override
	public void insertPlayer(Player p) {
		logger.info("adding player, in postgresplayerdao");
		
		String insertPlayerSQL = "INSERT INTO WEREWOLF"
				+ "(PLAYER_ID, PLAYER_NAME, LAT, LNG, IS_DEAD, IS_WEREWOLF, NUM_KILLS, NUM_VOTES_AGAINST, PLAYER_PIC) " + "VALUES"
				+ "("+p.getUserID()+",'"+ p.getId() +"',"+ p.getLat()+","+p.getLng()+","+p.isDead()+","+p.isWereWolf()
				+","+p.getKills()+","+p.getVoteCount()+","+"'picture'"+")";
		jdbcTemplate = postgresDao.getJdbcTemplate();
		logger.info("sql query was: "+insertPlayerSQL);
		jdbcTemplate.execute(insertPlayerSQL);
		
	}

	@Override
	public Player getPlayerById(String id) throws NoPlayerFoundException {
		String getPlayerById = "SELECT * FROM WEREWOLF WHERE PLAYER_NAME = '"+id+"';";
		jdbcTemplate = postgresDao.getJdbcTemplate();
		Player p = null;
		try{ p =(Player) jdbcTemplate.queryForObject(getPlayerById, new PlayerRowMapper());
		} catch (IncorrectResultSizeDataAccessException e){
			p = null;
		}
		return p;
	}

	@Override
	public void setPlayerLocation(String id, double lat, double lng) {
		String locationSQL = "UPDATE WEREWOLF "
				+"SET (LAT,LNG) = ("+lat+","+lng+") "
				+"WHERE PLAYER_ID = "+ id +";";
		jdbcTemplate = postgresDao.getJdbcTemplate();
		jdbcTemplate.execute(locationSQL);
		
		
	}

	@Override
	public List<Player> getAllPlayers() throws NoPlayersException {
		String getPlayers = "SELECT * FROM WEREWOLF;";
		jdbcTemplate = postgresDao.getJdbcTemplate();
		List<Player> p = (List<Player>) jdbcTemplate.queryForObject(getPlayers, new PlayerRowMapper());
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
		
		String insertPlayerSQL = "INSERT INTO WEREWOLF"
				+ "(PLAYER_ID, PLAYER_NAME, LAT, LNG, IS_DEAD, IS_WEREWOLF, NUM_KILLS, NUM_VOTES_AGAINST, PLAYER_PIC) " + "VALUES"
				+ "("+p.getUserID()+",'"+ p.getId() +"',"+ p.getLat()+","+p.getLng()+","+p.isDead()+","+p.isWereWolf()
				+","+p.getKills()+","+p.getVoteCount()+","+"'picture'"+")";
		jdbcTemplate = postgresDao.getJdbcTemplate();
		logger.info("sql query was: "+insertPlayerSQL);
		jdbcTemplate.execute(insertPlayerSQL);
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
				+"SET NUM_VOTES_AGAINST = NUM_VOTES_AGAINST + 1 "
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
		String createPlayer = "INSERT INTO WEREWOLF (PLAYER_ID, PLAYER_NAME, LAT, LNG, IS_DEAD, IS_WEREWOLF, NUM_KILLS, NUM_VOTES_AGAINST, PLAYER_PIC) VALUES ("
				+p.getUserID()+",'"+p.getId()+"',"+p.getLat()+","+p.getLng()+","+p.isDead()+","+p.isWereWolf()+","+p.getKills()+","+ p.getVoteCount()+",'"+p.getUserID()+"');";
		jdbcTemplate = postgresDao.getJdbcTemplate();
		jdbcTemplate.execute(createPlayer);
	}

	@Override
	public void killPlayer(Player p, Player killer) throws NoPlayerFoundException {
		String killPlayerSQL = "UPDATE WEREWOLF "
				+"SET IS_DEAD = TRUE "
				+"WHERE PLAYER_ID = "+ p.getUserID() +";";
		logger.info("Killed player "+p.getId());
		jdbcTemplate = postgresDao.getJdbcTemplate();
		jdbcTemplate.execute(killPlayerSQL);
		
		String updateKillCountSql = "UPDATE WEREWOLF "
				+"SET NUM_KILLS = NUM_KILLS + 1 "
				+"WHERE PLAYER_ID = "+ killer.getUserID() +";";
		jdbcTemplate = postgresDao.getJdbcTemplate();
		jdbcTemplate.execute(updateKillCountSql);
		
	}

	@Override
	public List<Player> getAllPlayersNear(Player p) {
		float playerLat = (float) p.getLat();
		float playerLng = (float) p.getLng();

		//(1/1380) degrees latitude ~ 0.05 miles
		//float maxLat = (playerLat+(1/690));
		double maxLat = (playerLat+(0.001));
		double minLat = (playerLat-(0.001));
		//float minLat = (playerLat-(1/690));
		//(1/1100) degrees longitude ~ 0.05 miles
		//float maxLng = (playerLng+(1/550));
		//float minLng = (playerLng-(1/550));
		double maxLng = (playerLng+(0.001));
		double minLng = (playerLng-(0.001));

		/**
		double maxLat = playerLat+1;
		double minLat = playerLat-1;
		double maxLng = playerLng+1;
		double minLng = playerLng-1;
		*/
		
		String name = p.getId();
		
		String getClosePlayers = "SELECT * FROM WEREWOLF WHERE ((LAT <= "+maxLat+" AND LAT >= "+
		minLat+") AND (LNG <= "+maxLng+" AND LNG >= "+minLng+") AND (IS_DEAD = FALSE) AND (PLAYER_NAME <> '"+name+"'));";
		jdbcTemplate = postgresDao.getJdbcTemplate();
		logger.info("getting close players: sql is: "+getClosePlayers);
		List<Player> players = new ArrayList<Player>();
		try {
			players = (List<Player>) jdbcTemplate.query(getClosePlayers, new PlayerRowMapper());
		} catch (IncorrectResultSizeDataAccessException e){
			logger.warning("No players near!");
		}
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

	@Override
	public Player getPlayerWithMostVotes() {
		String getVotedPlayersSQL = "select t1.* from werewolf t1 inner join(select max(num_votes_against) numvotes from werewolf where is_dead = false) t2 on t1.num_votes_against = t2.numvotes;";
		jdbcTemplate = postgresDao.getJdbcTemplate();
		List<Player> players = new ArrayList<Player>();
		Player p = new Player();
		//Player p = jdbcTemplate.queryForList(getVotedPlayersSQL, new PlayerRowMapper());
		try {
			players = (List<Player>) jdbcTemplate.query(getVotedPlayersSQL, new PlayerRowMapper());
		} catch (IncorrectResultSizeDataAccessException e){
			logger.warning("No players most voted!");
		}
		p = players.get(0);
		return p;
	}
	
	
	

}
