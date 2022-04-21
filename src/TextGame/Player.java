package TextGame;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Player implements Serializable {
    private static String location;
    private double health = 100;
    private double damage = 20.0 ;
    private String playerName;
    private static Map<String, Item> inventory = new HashMap<String, Item>();
    private int lives = 3;

    /**
     * Andy F.
     * created a player class
     */

    // setting a location to start, Andy
    public Player() {
        location = "Main Hallway 1"; // set to first room
    }

    public void equipWeapon(String item){
        Item temp = null;
        if (inventory.containsKey(item)){
            temp = inventory.get(item);
            if (temp.getItemFeatures().equals("Weapon")){
                damage = damage + temp.getItemStrength();
                System.out.println(temp.getItemName() + " has been equipped");
                System.out.println("Damage has increased by " + temp.getItemStrength());
            }else if(!inventory.containsKey(item)){
                System.out.println(item + " is not located in your inventory or does not exist");
                System.out.println("Try again");
            }
            else {
                System.out.println("Sorry invalid command, try again ");

            }

        }
    }

    public void unequipped(String item ){
        Item temp = null;

        if (inventory.containsKey(item) && damage >= 20){
            temp = inventory.get(item);
            damage = damage - temp.getItemStrength();
            System.out.println(temp.getItemName() + " is unequipped");
            System.out.println("Your damage is " + damage);
        } else {
            System.out.println(item + "is not equipped ");
        }

    }

    public void lifeCounter(){
        if (lives >= 0) {
            if (health == 0) {
                lives -= 1;
                health += 100;
            }
        }
        else {
            System.out.println("You have ran out of lives");
            System.out.println("The game has ended, try again");
        }
    }


    // Andy
    public void getInventory() {
        if (inventory.isEmpty()) { //if inventory is empty
            System.out.println("Inventory is empty, try to find items");
        } else { //Looks for items based the current inventory
            for (Map.Entry<String, Item> elt : inventory.entrySet()) {
                System.out.println(elt.getKey());
            }
        }
    }
    // Andy
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
    //Andy
    public void consume (String item){
        Item temp = null;
        double healing ;
        // checks if player has item in inventory
        if (inventory.containsKey(item)){
            temp = inventory.get(item);
            // if player is not 100 and item is a food
            if (!(health == 100) && temp.getItemFeatures().equals("Food")){
                // healing will be item percentage time total health
                healing = health * temp.getItemStrength();
                // add current health and healing to get new health points
                health = health + healing;
                System.out.println(playerName + " has consumed " + temp.getItemName() + "and current health is at " + health);

            }else {
                System.out.println("Your current health is already at 100");
            }

        }else {
            System.out.println("food item is not present in inventory");
        }
    }
    //Andy
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

    // created a movement function to allow player to travel through rooms based on exits, Andy
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
    public void look(String item) {
        System.out.println(inventory.get(item).getItemDescription());
    }

    public void look(HashMap<String, Room> rooms) {
        rooms.get(location).look();
    }



    public String getLocation() {
        return location;
    }

//    public void look(HashMap<String, Room> rooms) {
//        rooms.get(location).look();
//    }


}
