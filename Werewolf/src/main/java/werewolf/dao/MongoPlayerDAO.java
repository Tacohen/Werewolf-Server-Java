package werewolf.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import Exceptions.NoPlayerFoundException;

import edu.wm.something.domain.GPSLocation;
import edu.wm.something.domain.Player;

public class MongoPlayerDAO implements IPlayerDAO{
	
	@Autowired private MongoClient mongoClient;
	
	@Override
	public List<Player> getAllAlive() {
		
		List<Player> players = new ArrayList<>();
		
		Player bob = new Player();
		bob.setUserID(1);
		bob.setWerewolf(true);
		bob.setLat(40.3f);
		bob.setLng(40.3f);
		
		players.add(bob);
		
		return players;
		
	}

	@Override
	public void setDead(Player p) {
		BasicDBObject newDocument = new BasicDBObject();
		newDocument.append("$set", new BasicDBObject().append("isDead", true));
	 
		BasicDBObject searchQuery = new BasicDBObject().append("_id", p.getId());
	 
		DBCollection collection = getCollection();
		collection.update(searchQuery, newDocument);
		
	}

	@Override
	public void insertPlayer(Player p) {
		
		DBCollection coll = getCollection();
		
	}

	private DBCollection getCollection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player getPlayerById(String id) throws NoPlayerFoundException {
		
		DBCollection coll = getCollection();
		
		BasicDBObject query =new BasicDBObject();
		//somemore stuff i didn't get in time
		
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setPlayerLocation(String id, GPSLocation loc){
	BasicDBObject newDocument = new BasicDBObject();
	newDocument.append("$set", new BasicDBObject().append("lat",loc.getLat() ));
	newDocument.append("$set", new BasicDBObject().append("lng",loc.getLng() ));
 
	BasicDBObject searchQuery = new BasicDBObject().append("_id", id);
 
	DBCollection collection = getCollection();
	collection.update(searchQuery, newDocument);
	}

}
