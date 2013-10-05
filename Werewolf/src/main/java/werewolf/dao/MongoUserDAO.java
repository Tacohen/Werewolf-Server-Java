package werewolf.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import Exceptions.NoPlayersException;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

import edu.wm.something.domain.MyUser;
import edu.wm.something.domain.Player;

public class MongoUserDAO extends AbstractMongoConfiguration implements IUserDAO  {
	
	@Autowired private MongoClient mongoClient;
	@Autowired private static MongoTemplate mongoTemplate;
	     
	
	public String DATABSE_NAME = "test";
	public String COLLECTION_NAME = "Players";
	
	@Override
	public void createUser(MyUser user) {
		// For Annotation
		ApplicationContext ctx = 
	             new AnnotationConfigApplicationContext();
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
	}

	@Override
	protected String getDatabaseName() {
		return "test";
	}

	@Override
	@Bean
	public Mongo mongo() throws Exception {
		return new MongoClient("127.0.0.1");
	}

	@Override
	public void deleteUser(MyUser user) {
		// TODO Auto-generated method stub
		
	}
}
