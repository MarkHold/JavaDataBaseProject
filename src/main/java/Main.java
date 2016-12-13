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

    private static String file = "/Users/markuslyconhold/IdeaProjects/JavaDataBaseProject/theFile.txt";

    public static void main(String[] args) throws FileNotFoundException {

        Connection con = null;

        org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();

        try{
            Object obj = parser.parse(new FileReader("/Users/markuslyconhold/IdeaProjects/JavaDataBaseProject/theFile.txt"));

            JSONObject JObject = (JSONObject) obj;
            String line;
            BufferedReader bf = new BufferedReader(new FileReader(file));

            while ((line = bf.readLine()) != null) {

                String[] arr = new String[10];

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

                //first we connect to the database using DriverManager
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/redditDatabase", "root", "");

                String tableStuff = "INSERT INTO User(id, parent_id, link_id, namee, author, body, subreddit_id, subreddit, score, created_utc) VALUES (?,?,?,?,?,?,?,?,?,?)";
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


                /*
                String link_id = (String) JObject.get("link_id");
                String name = (String) JObject.get("name");
                String author = (String) JObject.get("author");
                String body = (String) JObject.get("body");
                String subreddit_id = (String) JObject.get("subreddit_id");
                String subreddit = (String) JObject.get("subreddit_id");
                int score = (Integer) JObject.get("subreddit_id");
                int created_utc = (Integer) JObject.get("subreddit_id");
                */
            }

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        finally {

            try {
                //if the connection is still open, close it.
                if (con != null) {
                    con.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }

    }
    }



    /*
    public void DBConnect(){

        Connection con = null;

        try{



            while(rs.next()){
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("id: " + rs.getInt("id"));
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
        //we do finally as well because we know it will be executed no matter what.
        finally {

            try {
                //if the connection is still open, close it.
                if (con != null) {
                    con.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

*/



        /*

        //first we create a connection and set it to null because we dont have a connection at the start.
        Connection con = null;

        try{


            //first we connect to the database using DriverManager
            con = DriverManager.getConnection("jdbc:mysql:test.db");

            //now we create a statement so that we can get quieries.

            Statement s = con.createStatement();

            //once this query has executed , the resultset will be populated by this result.
            ResultSet rs = s.executeQuery("SELECT * FROM brbr LIMIT 10");


            while(rs.next()){
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("id: " + rs.getInt("id"));
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
        //we do finally as well because we know it will be executed no matter what.
        finally {

            try {
                //if the connection is still open, close it.
                if (con != null) {
                    con.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }

        }

*/

}
