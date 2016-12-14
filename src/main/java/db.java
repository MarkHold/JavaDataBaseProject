import java.sql.*;

/**
 * Created by markuslyconhold on 12/12/16.
 */
public class db {

    private static Connection connect;
    private static boolean hasData = false;



    //A method to display the online users
    public ResultSet displayUsers() throws SQLException, ClassNotFoundException {

        //if we are connected keep going, if not, start a connection
        if(connect == null){
            startConnection();
        }

        //create a statment
        Statement state = connect.createStatement();
        //a simple query that will return the first and last name

        ResultSet results = state.executeQuery("SELECT fname, lname FROM user");

        return results;
    }


    public void startConnection() throws ClassNotFoundException, SQLException {

        //we need to load our jdbc driver into our driver manager, and we do it by doing this:

        Class.forName("org.sqlite.JDBC");
        connect = DriverManager.getConnection("jdbc:sqlite:SQLiteTest1.db");
        initialise();
    }

    // the funciton that builds the table
    private void initialise() throws SQLException {

        if(hasData == true){

            hasData = true;

            Statement state = connect.createStatement();

            ResultSet res = state.executeQuery("SELECT name FROM sqlite master WHERE type ='table' AND name = 'user'");

            if(!res.next()){
                System.out.println("Building the table brbrbr");
                //Now we need to build the table

                Statement state2 = connect.createStatement();

                //the table
                state.execute("CREATE TABLE user(id integer,"
                        +"fName varchar(60)," + "lName varchar(60),"
                        + "primary key(id));");

                //adding the data , three ? because it has three values our table.

                PreparedStatement prep = connect.prepareStatement("INSERT INTO user values(?,?,?);");

                //colom 1 is primary, it will be inserted into the table anyway.
                //colom 2, first name
                prep.setString(2, "Murre");
                prep.setString(3, "Murgot");
                prep.execute();


                //second prep statement
                PreparedStatement prep2 = connect.prepareStatement("INSERT INTO user values(?,?,?);");

                prep.setString(2, "Markus");
                prep.setString(3, "Deucalion");
                prep.execute();
            }
        }
    }

    //the method to give the ability to add things to the table.
    public void addUser(String firstName, String lastName) throws SQLException, ClassNotFoundException {

        if(connect == null){
            startConnection();
        }

        PreparedStatement prep = connect.prepareStatement("INSERT INTO user values(?,?,?);");
        prep.setString(2, firstName);
        prep.setString(3, lastName);
        prep.execute();
    }


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
