package TextGame;

import java.util.HashMap;
import java.util.Map;

public class Player {
    private static String location;
    private double Health = 100;
   // private double attackDamage = 0;
    private static Map<String, Item> inventory = new HashMap<String, Item>();

    // setting a location to start
    public Player() {
        location = "Main Hallway 1"; // set to first room
    }

    public void getInventory() {
        if (inventory.isEmpty()) { //if inventory is empty
            System.out.println("Inventory is empty, try to find items");
        } else { //Looks for items based the current inventory
            for (Map.Entry<String, Item> elt : inventory.entrySet()) {
                System.out.println(elt.getKey());
            }
        }
    }

    public void add(String item, HashMap<String, Room> rooms) {
        // check whether object is in room you are in
        Room current = rooms.get(location);

        Item temp = null;

        if (current.getInventory().containsKey(item)) { // item is in room
            temp = current.getInventory().get(item);

            current.getInventory().remove(item);
            inventory.put(temp.getItemName(), temp);
            System.out.println(item + " was successfully added to your inventory !");
        } else { //else
            System.out.println( item + " is not in the room.");
        }
    }

    public void drop(String item, HashMap<String, Room> rooms) {
        Item temp = null;

        if (inventory.containsKey(item)) { //if item is in inventory
            temp = inventory.get(item);
            inventory.remove(item);

            Room current = rooms.get(location); // where the player is currently at

            current.getInventory().put(temp.getItemName(), temp);
            System.out.println(item + " was successfully dropped");
        } else { // else
            System.out.println("Sorry, " + item + " is not in your inventory.");
        }
    }














    // created a movement function to allow player to travel through rooms based on exits
    public void movement(String direction, HashMap<String, Room> rooms) {
        direction = direction.toLowerCase();
        Room current = rooms.get(location);

        String[] temp = current.getNeighbors();

        if (direction.equals("north")) { //if direction is north...
            if (!temp[0].equals("-")) {//if there is a room in said direction
                location = temp[0];

            } else {
                System.out.println("Sorry, cannot go this way, try again!");
            }
        } else if (direction.equals("south")) {//if direction is south...
            if (!temp[1].equals("-")) {//if there is a room in said direction
                location = temp[1];

            } else {
                System.out.println("Sorry, cannot go this way, try again!");
            }
        } else if (direction.equals("east")) {//if direction is east...
            if (!temp[2].equals("-")) { //if there is a room in said direction
                location = temp[2];

            } else {
                System.out.println("Sorry, cannot go this way, try again!");
            }
        } else if (direction.equals("west")) {//if direction is west...
            if (!temp[3].equals("-")) {//if there is a room in said direction
                location = temp[3];

            } else {
                System.out.println("Sorry, cannot go this way, try again!");
            }
        } else { //else
            System.out.println("Sorry, not valid direction, try again!");
        }

    }



    public String getLocation() {
        return location;
    }

//    public void look(HashMap<String, Room> rooms) {
//        rooms.get(location).look();
//    }


}
