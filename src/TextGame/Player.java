package TextGame;

import java.util.HashMap;

public class Player {
    private static String location;

    // setting a location to start
    public Player() {
        location = "Main Hallway 1"; // set to first room
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
