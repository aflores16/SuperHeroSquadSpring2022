package TextGame;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Room implements Serializable {
    private String name_;
    private String roomNum_;
    private String deckNum_;
    private String roomID_;
    private String[] neighbors_;
    private boolean trader_;
    private String description_;
    private HashMap<String, Item> item = new HashMap<String, Item>();
    private HashMap<String, Monster> monster = new HashMap<String, Monster>();
    private HashMap<String, Puzzle> puzzle = new HashMap<String, Puzzle>();

    public Room(String name, boolean trader , String roomNum, String deckNum,
                String roomID, String description, String[] neighbors,
                HashMap<String, Item> items, HashMap<String, Monster> monsters,
                HashMap<String, Puzzle> puzzles) {
        name_ = name;
        description_ = description;
        roomNum_ = roomNum;
        deckNum_ = deckNum;
        roomID_ = roomID;
        neighbors_ = neighbors;
        trader_ = trader;

        setItems(items);
        setMonsters(monsters);
        setPuzzles(puzzles);
    }
    public void look() {
        //System.out.println(description_);

        System.out.println("You can exit to the ");

        if (!neighbors_[0].equals("-")) { // if there is spot to north
            System.out.print("NORTH, ");
        }

        if (!neighbors_[1].equals("-")) { // if there is spot to south
            System.out.print("SOUTH, ");
        }

        if (!neighbors_[2].equals("-")) { // if there is spot to east
            System.out.print("EAST, ");
        }

        if (!neighbors_[3].equals("-")) { // if there is spot to west
            System.out.print("WEST");
        }

        System.out.println();
        System.out.println();

        if (item.isEmpty()) { // if collection of items in room is empty
            System.out.println("No items are located in this room, \n" + "Try a different room" );
        } else {
            for (Map.Entry<String, Item> ex : item.entrySet()) {
                System.out.print(ex.getKey() + ", ");
            }
            System.out.println(" are in the room.");
        }
        if (monster.isEmpty()){
            System.out.println();
            System.out.println("There are no monsters in this room");
        } else {
            for (Map.Entry<String,Monster >ex : monster.entrySet()){
                System.out.println();
                System.out.print(ex.getKey() + ", " + ex.getValue().getDescription_());
            }

        }
    }


    public void setItems(HashMap<String, Item> items) {
        for (Map.Entry<String, Item> ex : items.entrySet()) {
            //if items location matches rooms name
            if (ex.getValue().getLocation().equals(name_)) {

                item.put(ex.getKey(), ex.getValue());
            }
        }
    }
    public void setMonsters(HashMap<String, Monster> monsters){
        for(Map.Entry<String, Monster> ex : monsters.entrySet()){
            if (ex.getValue().getLocation_().equals(name_)){

                monster.put(ex.getKey(), ex.getValue());
            }
        }
    }

    public void setPuzzles(HashMap<String, Puzzle> puzzles) {
        for (Map.Entry<String, Puzzle> elt : puzzles.entrySet()) {

            if (elt.getValue().getLocation().equals(name_)) {

                puzzle.put(elt.getKey(), elt.getValue());
            }
        }
    }

    public String getName() {
        return name_;
    }

    public String getId() {
        return roomID_;
    }

    public String[] getNeighbors() {
        return neighbors_;
    }

    public String getDescription() {
        return description_;
    }

    public HashMap<String, Item> getInventory() {
        return item;
    }

    public HashMap<String, Puzzle> getPuzzle() {
        return puzzle;
    }

    public HashMap<String, Monster> getMonster() {
        return monster;
    }

}
