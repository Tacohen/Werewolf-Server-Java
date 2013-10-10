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

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

@SuppressWarnings("unchecked")
@SuppressWarnings("deprecation")
public class PostgresPlayerDAO extends SimpleJdbcDaoSupport implements IPlayerDAO{
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private static PostgresDAO postgresDao = new PostgresDAO();
	static Logger logger = Logger.getLogger(PostgresPlayerDAO.class.getName());
	private static JdbcTemplate jdbcTemplate;
	

	@Override
	public List<Player> getAllAlive() {
		logger.info("In PostgresPLayerDAO");
		
		String getPlayers = "SELECT * FROM WEREWOLF WHERE IS_DEAD=FALSE;";
		jdbcTemplate = postgresDao.getJdbcTemplate();
		List<Player> p = jdbcTemplate.queryForObject(getPlayers, new BeanPropertyRowMapper(Player.class));
		
		/**List<Player> players = new ArrayList<Player>();
		 
		List<Map> rows = getJdbcTemplate().queryForList(getPlayers);
		for (Map row : rows) {
			Customer customer = new Customer();
			customer.setCustId((Long)(row.get("CUST_ID")));
			customer.setName((String)row.get("NAME"));
			customer.setAge((Integer)row.get("AGE"));
			customers.add(customer);
		}*/
		
		
		return p;
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
		String insertPlayerSQL = "INSERT INTO WEREWOLF"
				+ "(PLAYER_ID, PLAYER_NAME, LAT, LNG, IS_DEAD, IS_WEREWOLF, NUM_VOTES_AGAINST, PLAYER_PIC) " + "VALUES"
				+ "("+p.getUserID()+",'"+ p.getId() +"',"+ p.getLat()+","+p.getLng()+","+p.isDead()+","+p.isWereWolf()
				+ ","+p.getVoteCount()+","+"'picture'"+")";
		jdbcTemplate = postgresDao.getJdbcTemplate();
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
		BeanPropertyRowMapper b =new BeanPropertyRowMapper(Player.class);
		List<Player> p = (List<Player>) jdbcTemplate.queryForObject(getPlayers, b);
		return p;
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
	public Player getPlayerPic(int id) throws NoPlayerFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player getPlayerInfo(int id) throws NoPlayerFoundException {
		String getPlayerById = "SELECT * FROM WEREWOLF WHERE PLAYER_ID = "+id+";";
		jdbcTemplate = postgresDao.getJdbcTemplate();
		Player p =(Player) jdbcTemplate.queryForObject(getPlayerById, new PlayerRowMapper());
		return p;
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
		String movePlayerSQL = "UPDATE WEREWOLF SET (LAT,LNG) = ("
		+lat+","+lng+") WHERE PLAYER_ID = "+p.getUserID()+";";
		jdbcTemplate = postgresDao.getJdbcTemplate();
		jdbcTemplate.execute(movePlayerSQL);
		
	}
	
	
	

}
