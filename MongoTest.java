/*
 * MongoTest - main program to test connection to MongoDB
 * 
 * Last updated 05-DEC-2019, Paul J. Wagner
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;
 
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoTest {

	public static void main(String[] args) {
		MongoDAO mongoDAO = new MongoDAO();
		mongoDAO.connect();
		mongoDAO.setDataBase("username for database or class here");
		mongoDAO.setCollection("Movies");
		mongoDAO.deleteAll();
		mongoDAO.insertOne("{ 'title' : 'Before The Rain' , 'year' : 2019 }");
		mongoDAO.findAll();

		String yourResultString = mongoDAO.processDocuments();
		System.out.println("Find resilt is: " + yourResultString);
		mongoDAO.insertOne("{'title' : 'Avngers: Endgame' , 'year' : 2019 , 'genre' : ['action' , 'adventure' , 'fantasy' ] , 'directors' : [ 'Anthony Russo' , 'Joe Russo' ]}");
		mongoDAO.findAll();

		String yourResultStrings = mongoDAO.processDocuments();
		System.out.println("Find resilt is: " + yourResultStrings);
		mongoDAO.findSomeEqual("title","Avengers: Endgame");
		mongoDAO.findAll();
		String result = mongoDAO.processDocuments();
		System.out.println("Find result is: " + result);
		mongoDAO.deleteEqualTest("title", "Before The Rain");
		mongoDAO.findAll();
		String result1 = mongoDAO.processDocuments();
		System.out.println(result1);
		mongoDAO.disconect();

	}

}
