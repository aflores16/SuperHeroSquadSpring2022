package TextGame;

import java.util.*;

public class Room {

    private String name_;
    private String id_;
    private String deck_;
    private String roomid_;
    private String[] neighbors_;
    private String description_;
    private HashMap<String, Item> items_ = new HashMap<String, Item>();
    private HashMap<String, Puzzle> puzzles_ = new HashMap<String, Puzzle>();
    private HashMap<String, Monster> monsters_ = new HashMap<String, Monster>();

    public Room(String name, String id, String deck, String roomid, String[] neighbors, String description, HashMap<String, Item> items, HashMap<String, Puzzle> puzzles, HashMap<String, Monster> monsters) {

        name_ = name;
        id_ = id;
        deck_ = deck;
        roomid_ = roomid;
        neighbors_ = neighbors;
        description_ = description;
        setItems(items);
        setPuzzles(puzzles);
        setMonsters(monsters);

    }

    public void setItems(HashMap<String, Item> items) {
        for (Map.Entry<String, Item> elt : items.entrySet()) {

            if (elt.getValue().getLocation().equals(id_) && elt.getValue().getSpawnrate() > Math.random()) {

                items_.put(elt.getKey(), elt.getValue());
            }
        }
    }

    public void setPuzzles(HashMap<String, Puzzle> puzzles) {
        for (Map.Entry<String, Puzzle> elt : puzzles.entrySet()) {

            if (elt.getValue().getLocation().equals(id_)) {

                puzzles_.put(elt.getKey(), elt.getValue());
            }
        }
    }

    public void setMonsters(HashMap<String, Monster> monsters) {
        for (Map.Entry<String, Monster> elt : monsters.entrySet()) {

            if (elt.getValue().getLocation().equals(id_) && elt.getValue().getSpawnrate() > Math.random()) {

                monsters_.put(elt.getKey(), elt.getValue());
            }
        }
    }

    public void look() {
        System.out.println(description_);

        System.out.println("Can exit to the ");

        if (neighbors_[0].contains("/")) {
            System.out.print("NORTH/LOCKED, ");
        } else if (!neighbors_[0].equals("-")) {
            System.out.print("NORTH, ");
        }
        if (neighbors_[1].contains("/")) {
            System.out.print("SOUTH/LOCKED, ");
        }else if (!neighbors_[1].equals("-")) {
            System.out.print("SOUTH, ");
        }
        if (neighbors_[2].contains("/")) {
            System.out.print("EAST/LOCKED, ");
        } else if (!neighbors_[2].equals("-")) {
            System.out.print("EAST, ");
        }

        if (neighbors_[3].contains("/")) {
            System.out.print("WEST/LOCKED, ");
        }else if (!neighbors_[3].equals("-")) {
            System.out.print("WEST, ");
        }

        System.out.println();
        System.out.println();

        if (items_.isEmpty()) {
            System.out.println("No items in room, sorry.");
        } else {
            for (Map.Entry<String, Item> elt : items_.entrySet()) {
                if (items_.get(elt.getKey()).getRoomloot().equals("1")){
                    System.out.print(elt.getKey() + ", ");
                }
            }
            System.out.println("are in the room.");
        }

    }

    public String getName() {
        return name_;
    }

    public String getId() {
        return id_;
    }

    public String[] getNeighbors() {
        return neighbors_;
    }

    public String getDescription() {
        return description_;
    }

    public HashMap<String, Item> getInventory() {
        return items_;
    }

    public HashMap<String, Puzzle> getPuzzle() {
        return puzzles_;
    }

    public HashMap<String, Monster> getMonster() {
        return monsters_;
    }

    public void setNeighbors_(String[] neighbors_) {
        this.neighbors_ = neighbors_;
    }
}