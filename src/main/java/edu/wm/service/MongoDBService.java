package edu.wm.service;

import org.apache.commons.logging.Log;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.Mongo;

import edu.wm.something.domain.MyUser;
import edu.wm.something.domain.Player;

public class MongoDBService {
	
	private static final Log log = LogFactory.getLog(MongoDBService.class);
	
	private static MongoOperations mongoOps;

	  @SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		  /**
		mongoOps = new MongoTemplate(new Mongo(), "database");
	    //String id, boolean isDead, double lat, double lng, String userID, Boolean isWerewolf
	    mongoOps.insert(new Player("Joe", false, 30, 40, 123456789, true));

	    //log.info(mongoOps.findOne(new Query(where("name").is("Joe")), Player.class));

	    mongoOps.dropCollection("person");
	    */
	    
	 // For XML
		//ApplicationContext ctx = new GenericXmlApplicationContext("SpringConfig.xml");
	 
		// For Annotation
		ApplicationContext ctx = 
	             new AnnotationConfigApplicationContext(SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
	 //Player(String id, boolean isDead, double lat, double lng, int userId, Boolean isWerewolf) {
		Player user = new Player("testid", false, 30, 40,101,true,0);
	 
		// save
		mongoOperation.save(user);
	 
		// now user object got the created id.
		System.out.println("1. user : " + user);
	 
		// query to search user
		Query searchUserQuery = new Query(Criteria.where("username").is("mkyong"));
	 
		// find the saved user again.
		MyUser savedUser = mongoOperation.findOne(searchUserQuery, MyUser.class);
		System.out.println("2. find - savedUser : " + savedUser);
	 
		// update password
		mongoOperation.updateFirst(searchUserQuery, 
	                         Update.update("password", "new password"),MyUser.class);
	 
		// find the updated user object
		MyUser updatedUser = mongoOperation.findOne(searchUserQuery, MyUser.class);
	 
		System.out.println("3. updatedUser : " + updatedUser);
	 
		// delete
		mongoOperation.remove(searchUserQuery, MyUser.class);
	 
		// List, it should be empty now.
		List<MyUser> listUser = mongoOperation.findAll(MyUser.class);
		System.out.println("4. Number of user = " + listUser.size());
	  }


}
