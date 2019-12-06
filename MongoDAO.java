import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static com.mongodb.client.model.Filters.*;
import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDAO {
    private MongoClient client;
    private MongoCredential credential;
    private MongoDatabase database;
    private MongoCollection collection;
    private List<Document> documents;

    public void connect(){
        // set up credentials - user, authenticationDB, pw (all case-sensitive)
        credential = MongoCredential.createCredential("user name here","CS260", getPW().toCharArray());
        // make a client connection to the remote MongoDB database instance (host, port)  - with credentials
        client = new MongoClient(new ServerAddress("10.35.195.203", 27017), Arrays.asList(credential));
    }

    public void setDataBase(String s){
        // set the database
       database = client.getDatabase(s);
    }

    public void setCollection(String s){
         collection = database.getCollection(s);
    }

    public void findAll(){
        documents = (List<Document>) collection.find().into(
                new ArrayList<Document>());
    }

    public String processDocuments(){
        String docs = "";
        for(Document document : documents) {
           docs += document.toString();


        }
            /**System.out.println(document);
            String cname = document.getString("name");
            System.out.println("name is: " + cname);
            if (document.getInteger("salary") != null) {
                int csalary = document.getInteger("salary");
                System.out.println("salary is: " + csalary);
            }
            List<String> c_skills = (List<String>) document.get("achievedSkills");
            if (c_skills != null) {
                for (String skill : c_skills) {
                    System.out.println("  " + skill);
                }
            }
            else {
                System.out.println("no skills achieved");
            }
            System.out.println();
        }	// end - for*/
            return docs;
    }

    public void disconect(){
        client.close();
    }

    private static String getPW () {

        return new String("user password here");
    }
    public void insertOne(String jsonString){
        Document doc = Document.parse(jsonString);
        collection.insertOne(doc);

    }

    public void deleteEqualTest(String field, Object value){
        collection.deleteMany(eq(field, value));
    }

    public void findSomeEqual (String field, Object value){
        documents = (List<Document>) collection.find(eq(field, value)).into(new
                ArrayList<Document>());
    }

    public void deleteAll(){
        collection.deleteMany(new Document());
    }
}
