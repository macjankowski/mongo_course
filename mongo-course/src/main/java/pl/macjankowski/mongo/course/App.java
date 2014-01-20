package pl.macjankowski.mongo.course;

import java.net.UnknownHostException;
import java.util.Map;
import java.util.Set;

import org.bson.BSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) throws UnknownHostException {

		MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));

		DB database = client.getDB("students");
		DBCollection collection = database.getCollection("grades");

		for (int i = 0; i < 200; i++) {
			DBCursor res = collection.find(new BasicDBObject("type", "homework").append("student_id", i)).sort(
					new BasicDBObject("score", 1));
			if (res.hasNext()) {
				DBObject o = res.next();
				System.out.println(o);
				collection.remove(o);
			}
		}

	}
}
