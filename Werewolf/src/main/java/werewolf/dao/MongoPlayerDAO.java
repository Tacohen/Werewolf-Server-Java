package werewolf.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import Exceptions.NoPlayerFoundException;
import Exceptions.NoPlayersException;

import edu.wm.something.domain.GPSLocation;
import edu.wm.something.domain.Player;

public class MongoPlayerDAO implements IPlayerDAO{
	
	@Autowired private MongoClient mongoClient;
	@Autowired private MongoTemplate mongoTemplate;
	
	public String DATABSE_NAME = "test";
	public String COLLECTION_NAME = "Players";
    public static final double maxKillDistance = 0.005;//26.4 feet (1/150th of a mile)
    public static final GPSLocation gpsLocation = new GPSLocation();

	
	@Override
	public List<Player> getAllAlive() {
		
		List<Player> players = new ArrayList<>();
		/**
		Player bob = new Player();
		bob.setUserID(1);
		bob.setWerewolf(true);
		bob.setLat(40.3f);
		bob.setLng(40.3f);
		
		players.add(bob);
		*/
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

	@Override
	public List<Player> getAllPlayers() throws NoPlayersException {
		List<Player> resultsList = mongoTemplate.findAll(Player.class, COLLECTION_NAME);
        if (resultsList.size()== 0){
        	throw new NoPlayersException();
        }
        else{
        	return resultsList;
        }
	}

	@Override
	public void deletePlayer(Player player) throws NoPlayerFoundException {
		try {
        	mongoTemplate.remove(player, COLLECTION_NAME);
        }
        catch (Exception e){
        	throw new NoPlayerFoundException(player.getId());
        }
	}

	@Override
	public void updatePlayer(Player player) throws NoPlayerFoundException {
		try {
        	mongoTemplate.insert(player, COLLECTION_NAME);
        }
        catch (Exception e){
        	throw new NoPlayerFoundException(player.getId());
        }
		
	}

	@Override
	public Player getPlayerById(int id) throws NoPlayerFoundException {
		List<Player> listOfPlayers = mongoTemplate.findAll(Player.class, COLLECTION_NAME);
    	int length = listOfPlayers.size();
    	for(int i=0; i<length; i++){
    		Player p = listOfPlayers.get(i);
    		if (p.getId().equals(id)){
    			return p;
    		}
    	}
    	//If we didn't find anyone with that id
    	throw new NoPlayerFoundException(((Integer)id).toString());
	}

	@Override
	public void voteOnPlayer(Player p) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(p.getId()));
		int voteCount = (p.getVoteCount()+1);
		Update update = new Update();
		update.set("voteCount", voteCount);
		mongoTemplate.updateFirst(query,update, Player.class);	
	}

	@Override
	public Player getPlayerPic(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player getPlayerInfo(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createPlayer(Player p) {
		if (!mongoTemplate.collectionExists(Player.class)) {
            mongoTemplate.createCollection(Player.class);
        }       
        p.setId(UUID.randomUUID().toString());
        mongoTemplate.insert(p, COLLECTION_NAME);
		
		
	}

	@Override
	public void killPlayer(Player p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Player> getAllPlayersNear(Player p) {
		List<Player> listOfPlayers = mongoTemplate.findAll(Player.class, COLLECTION_NAME);
		List<Player> listOfPlayersNear = (List<Player>) new ArrayList<Player>();
		int length = listOfPlayers.size();
		for (int i=0;i<length;i++){
			Player player = listOfPlayers.get(i);
			double playerLat = player.getLat();
			double playerLng = player.getLng();
			double distance = gpsLocation.distance(p.getLat(), p.getLng(), playerLat, playerLng);
			if (distance < maxKillDistance){
				listOfPlayersNear.add(player);
			}
		}
		return listOfPlayersNear;
	}

	@Override
	public void movePlayer(Player p, double lat, double lng) {
		// TODO Auto-generated method stub
		
	}

}
