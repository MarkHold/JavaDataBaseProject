import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by markuslyconhold on 12/12/16.
 */
public class User {

    private int id;
    private String parent_id;
    private String link_id;

    private String name;
    private String author;
    private String body;

    private String subreddit_id;
    private String subreddit;
    private int score;

    private String created_utc;
}
