package edu.wm.service;

import java.util.List;
import java.util.UUID;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

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
     
    public void deleteplayer(Player player) {
        mongoTemplate.remove(player, COLLECTION_NAME);
    }
     
    public void updateplayer(Player player) {
        mongoTemplate.insert(player, COLLECTION_NAME);      
    }
    
    
}
