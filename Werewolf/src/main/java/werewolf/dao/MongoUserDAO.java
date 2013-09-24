package werewolf.dao;

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
	
	
	 
	

}
