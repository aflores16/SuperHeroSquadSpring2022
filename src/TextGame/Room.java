package TextGame;

import java.util.HashMap;
import java.util.Map;

public class Room {
    private String name_;
    private String roomNum_;
    private String deckNum_;
    private String roomID_;
    private String[] neighbors_;
    private String description_;
    private HashMap<String, Item> item = new HashMap<String, Item>();
    private HashMap<String, Monster> monster = new HashMap<String, Monster>();

    public Room(String name, String roomNum, String deckNum,
                String roomID, String description, String[] neighbors,
                HashMap<String, Item> items, HashMap<String, Monster> monsters) {
        name_ = name;
        description_ = description;
        roomNum_ = roomNum;
        deckNum_ = deckNum;
        roomID_ = roomID;
        neighbors_ = neighbors;

        setItems(items);
        setMonsters(monsters);
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

    public String[] getNeighbors() {
        return neighbors_;
    }


    public String getName() {
        return name_;
    }

    public HashMap<String, Item> getInventory() {
        return item;
    }


    public String getDescription() {
        return description_;
    }

}
