import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Iterator;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Created by markuslyconhold on 12/12/16.
 */
public class Main {

    private static boolean hasData = false;

    private static Connection con;
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException, ParseException {

        if(con == null){
            startConnection();
        }

    }


    public static void startConnection() throws ClassNotFoundException, SQLException, IOException, ParseException {

        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("jdbc:sqlite:theDataBase.db");
        //initialise();
        Initialise();
    }


    //in this method we import the data from the file and parse it
    public static void Initialise() throws SQLException, IOException, ParseException {

            Statement state2 = con.createStatement();

            //the table
            state2.execute("CREATE TABLE IF NOT EXISTS redditTablex(id varchar(30), parent_id varchar(30), link_id varchar(30), namee varchar(30), " +
                    "author varchar(30), body varchar(30000), subreddit_id varchar(30), subreddit varchar(30), " +
                    " score varchar(30), created_utc varchar(30))");

            String tableStuff = "INSERT INTO redditTablex(id, parent_id, link_id, namee, author, body, subreddit_id, subreddit, score, created_utc) VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement prep = con.prepareStatement(tableStuff);

            BufferedReader bf;

            bf = new BufferedReader(new FileReader("/Users/markuslyconhold/IdeaProjects/JavaDataBaseProject/RC_2007-1"));

            JSONParser parser  = new JSONParser();
            con.setAutoCommit(false);
            String s;

            while((s = bf.readLine()) != null){

                Object obj = parser.parse(s);
                JSONObject JObject = (JSONObject) obj;

                prep.setString(1, JObject.get("id").toString());
                prep.setString(2, JObject.get("parent_id").toString());
                prep.setString(3, JObject.get("link_id").toString());
                prep.setString(4, JObject.get("name").toString());
                prep.setString(5, JObject.get("author").toString());
                prep.setString(6, JObject.get("body").toString());
                prep.setString(7, JObject.get("subreddit_id").toString());
                prep.setString(8, JObject.get("subreddit").toString());
                prep.setString(9, JObject.get("score").toString());
                prep.setString(10, JObject.get("created_utc").toString());
                prep.execute();


            }
            con.commit();
            con.setAutoCommit(true);

        queries();

    }


    //the method that contains the queries.
    public static void queries() throws SQLException {

        Statement state = con.createStatement();

        //Query1
        ResultSet q1 = state.executeQuery("SELECT parent_id FROM redditTablex WHERE link_id ='t3_5yba3' AND id = 'c0299ar'");
        while(q1.next()){
            System.out.println("1. How many comments have a specific user posted: " + q1.getString("parent_id"));
        }
        //Query2
        ResultSet q2 = state.executeQuery("SELECT id FROM redditTableNew WHERE type ='table' AND name = 'user'");
        while(q2.next()){
            System.out.println("2. How many comments does a specific subreddit get per day:  " + q2.getString("name"));
        }

        //Query3
        ResultSet q3 = state.executeQuery("SELECT id FROM redditTableNew WHERE type ='table' AND name = 'user'");
        while(q3.next()){
            System.out.println("3. How many comments include the word ‘lol’: "+ q3.getString("namse"));
        }

        //Query4
        ResultSet q4 = state.executeQuery("SELECT id FROM redditTableNew WHERE type ='table' AND name = 'user'");
        while(q4.next()){
            System.out.println("4. Users that commented on a specific link has also posted to which subreddits " + q4.getString("name"));
        }

        //Query5
        ResultSet q5 = state.executeQuery("SELECT id FROM redditTableNew WHERE type ='table' AND name = 'user'");
        while(q5.next()){
            System.out.println("5. Which users have the highest and lowest combined scores: " + q5.getString("zszs"));
        }

        //Query6
        ResultSet q6 = state.executeQuery("SELECT id FROM redditTableNew WHERE type ='table' AND name = 'user'");
        while(q5.next()){
            System.out.println("6. Which subreddits have the highest and lowest scored comments: " + q5.getString("zszs"));
        }

        //Query7
        ResultSet q7 = state.executeQuery("SELECT id FROM redditTableNew WHERE type ='table' AND name = 'user'");
        while(q5.next()){
            System.out.println("7. Given a specific user, list all the users he or she has potentially interacted with: " + q5.getString("zszs"));
        }

        //Query8
        ResultSet q8 = state.executeQuery("SELECT id FROM redditTableNew WHERE type ='table' AND name = 'user'");
        while(q5.next()){
            System.out.println("8. Which users has only posted to a single subreddit: " + q5.getString("zszs"));
        }


    }
}
