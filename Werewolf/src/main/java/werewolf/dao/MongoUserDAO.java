package werewolf.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoOperations;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

import edu.wm.something.domain.User;

public class MongoUserDAO extends AbstractMongoConfiguration implements IUserDAO  {
	
	@Autowired private MongoClient mongoClient;
	
	public String DATABSE_NAME = "werewolfdb";
	public String COLLECTION_NAME = "users";
	
	@Override
	public void createUser(User user) {
		// For Annotation
		ApplicationContext ctx = 
	             new AnnotationConfigApplicationContext();
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
	 
		
	}

	@Override
	protected String getDatabaseName() {
		return "userdb";
	}

	@Override
	@Bean
	public Mongo mongo() throws Exception {
		return new MongoClient("127.0.0.1");
	}
	
	//@Override
	//public User getUserByName(String name){
		//
	//}
	
	
	 
	

}
