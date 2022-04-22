package TextGame;


public class Puzzle {
    private String name_;
    private String id_;
    private String description_;
    private String location_;
    private String attempt_;


    public Puzzle(String name, String id, String description, String location, String attempt) {
        name_ = name;
        id_ = id;
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
