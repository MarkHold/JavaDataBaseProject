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

    private static String file = "/Users/markuslyconhold/IdeaProjects/JavaDataBaseProject/RC_2007-1";

    private static boolean hasData = false;

    private static String[] arr = new String[10];

    private static Connection con;
    public static void main(String[] args) throws FileNotFoundException, SQLException, ClassNotFoundException {

        if(con == null){
            startConnection();
        }

    }


    public static void startConnection() throws ClassNotFoundException, SQLException {

        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("jdbc:sqlite:theDataBase.db");
        initialise();
    }

    public static void createTable() throws SQLException {

        Statement state2 = con.createStatement();

        //the table
        state2.execute("CREATE TABLE IF NOT EXISTS redditTablexoxoo(id varchar(30), parent_id varchar(30), link_id varchar(30), namee varchar(30), " +
                "author varchar(30), body varchar(30000), subreddit_id varchar(30), subreddit varchar(30), " +
                " score varchar(30), created_utc varchar(30),primary key(id));");

        String tableStuff = "INSERT INTO redditTablexoxoo(id, parent_id, link_id, namee, author, body, subreddit_id, subreddit, score, created_utc) VALUES (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement prep = con.prepareStatement(tableStuff);

        for(int i = 0; i < arr.length; i++){

            prep.setString(1, arr[0]);
            prep.setString(2, arr[1]);
            prep.setString(3, arr[2]);
            prep.setString(4, arr[3]);
            prep.setString(5, arr[4]);
            prep.setString(6, arr[5]);
            prep.setString(7, arr[6]);
            prep.setString(8, arr[7]);
            prep.setString(9, arr[8]);
            prep.setString(1, arr[9]);
        }
        prep.executeBatch();

    }

    //initialize metoden som läser filen och stoppar all data i en string array.
    public static void initialise() throws SQLException, ClassNotFoundException {


        org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();

        try{
            JSONParser parseNew  = new JSONParser();

            String line;
            BufferedReader bf = new BufferedReader(new FileReader(file));
            while ((line = bf.readLine()) != null) {

                Object obj = parser.parse(line);
                JSONObject JObject = (JSONObject) obj;

                arr[0] = JObject.get("id").toString();
                arr[1] = JObject.get("parent_id").toString();
                arr[2] = JObject.get("link_id").toString();
                arr[3] = JObject.get("name").toString();
                arr[4] = JObject.get("author").toString();
                arr[5] = JObject.get("body").toString();
                arr[6] = JObject.get("subreddit_id").toString();
                arr[7] = JObject.get("subreddit").toString();
                arr[8] = JObject.get("score").toString();
                arr[9] = JObject.get("created_utc").toString();
            }

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        createTable();

    }

    public void destroyTable(){

    }

    public void  queries() throws SQLException {

        Statement state = con.createStatement();

        //writing the first query.
        ResultSet res = state.executeQuery("SELECT id FROM sqlite master WHERE type ='table' AND name = 'user'");

    }
}
