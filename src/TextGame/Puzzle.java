package TextGame;
import java.io.Serializable;

public class Puzzle implements Serializable{
    private String name_;
    private String id_;
    private String puzzle_;
    private String description_;
    private String location_;
    private String attempt_;


    public Puzzle(String name, String id, String puzzle, String description, String location, String attempt) {
        name_ = name;
        id_ = id;
        puzzle_ = puzzle;
        description_ = description;
        location_ = location;
        attempt_ = attempt;
    }

    public String getName () {
        return name_;
    }

    public String getId () {
        return id_;
    }

    public String getPuzzle () {
        return puzzle_;
    }

    public String getDescription () {
        return description_;
    }

    public String getLocation () {
        return location_;
    }

    public String getAttempt () {
        return attempt_;
    }
}
