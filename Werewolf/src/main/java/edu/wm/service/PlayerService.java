package edu.wm.service;

import java.util.List;
import java.util.UUID;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.DB;
import com.mongodb.MongoClient;

import edu.wm.something.domain.Player;
 
 
@Repository
public class PlayerService {
     
    @Autowired
    private static MongoTemplate mongoTemplate;
     
    public static final String COLLECTION_NAME = "player";   

     
    public void addplayer(Player player) {
        if (!mongoTemplate.collectionExists(Player.class)) {
            mongoTemplate.createCollection(Player.class);
        }       
        player.setId(UUID.randomUUID().toString());
        mongoTemplate.insert(player, COLLECTION_NAME);
    }
     
    public static List<Player> getAllPlayers() {
        return mongoTemplate.findAll(Player.class, COLLECTION_NAME);
    }
     
    public void deletePlayer(Player player) {
        mongoTemplate.remove(player, COLLECTION_NAME);
    }
     
    public void updateplayer(Player player) {
        mongoTemplate.insert(player, COLLECTION_NAME);      
    }
    
    public Player getPlayerFromDbByID(Integer ownerId){
    	List<Player> listOfPlayers = mongoTemplate.findAll(Player.class, COLLECTION_NAME);
    	int length = listOfPlayers.size();
    	for(int i=0; i<length; i++){
    		Player p = listOfPlayers.get(i);
    		if (p.getId().equals(ownerId.toString())){
    			return p;
    		}
    	}
    	//If the player isn't there
    	return null;
    }
    
    
}