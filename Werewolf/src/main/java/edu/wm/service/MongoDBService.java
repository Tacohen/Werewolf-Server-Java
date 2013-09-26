package edu.wm.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.Mongo;

import edu.wm.something.domain.Player;

public class MongoDBService {
	
	private static final Log log = LogFactory.getLog(MongoDBService.class);
	
	private static MongoOperations mongoOps;

	  @SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {

		mongoOps = new MongoTemplate(new Mongo(), "database");
	    //String id, boolean isDead, double lat, double lng, String userID, Boolean isWerewolf
	    mongoOps.insert(new Player("Joe", false, 30, 40, 123456789, true));

	    //log.info(mongoOps.findOne(new Query(where("name").is("Joe")), Player.class));

	    mongoOps.dropCollection("person");
	  }


}
