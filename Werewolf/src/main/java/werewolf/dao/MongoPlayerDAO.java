package werewolf.dao;

import java.util.ArrayList;
import java.util.List;

import Exceptions.NoPlayerFoundException;

import edu.wm.something.domain.Player;

public class MongoPlayerDAO implements IPlayerDAO{
	

	@Override
	public List<Player> getAllAlive() {
		
		List<Player> players = new ArrayList<>();
		
		Player bob = new Player();
		bob.setUserID("1");
		bob.setWerewolf(true);
		bob.setLat(40.3f);
		bob.setLng(40.3f);
		
		players.add(bob);
		
		return players;
		
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

}
