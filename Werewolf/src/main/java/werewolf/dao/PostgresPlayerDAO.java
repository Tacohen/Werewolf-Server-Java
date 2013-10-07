package werewolf.dao;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import Exceptions.NoPlayerFoundException;
import Exceptions.NoPlayersException;
import edu.wm.something.domain.GPSLocation;
import edu.wm.something.domain.Player;


public class PostgresPlayerDAO implements IPlayerDAO{
	
	public boolean databaseBuilt = false;

	@Override
	public List<Player> getAllAlive() {
		// TODO Auto-generated method stub
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
	
	
	public void implementDatabase(){
		databaseBuilt = true;

		try {

			Class.forName("org.postgresql.Driver");

		} catch (ClassNotFoundException e) {

			System.out.println("Where is your PostgreSQL JDBC Driver? "
					+ "Include in your library path!");
			e.printStackTrace();
			return;

		}

		System.out.println("PostgreSQL JDBC Driver Registered!");

		Connection connection = null;

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
		} else {
			System.out.println("Failed to make connection!");
		}

	}

}
