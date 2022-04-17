package TextGame;

import java.io.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class Controller {

    public static void main(String[] args) {
        Player player = new Player();
        HashMap<String, Item> items = ReadTextFile.createItems();
        HashMap<String, Monster> monsters = ReadTextFile.createMonster();
        HashMap<String, Room> rooms = ReadTextFile.createRooms(items, monsters);
       // HashMap<String, Puzzle> scores = ReadTextFile.createPuzzle();
        Scanner input = new Scanner(System.in);

        // show current location of room
        System.out.println(rooms.get(player.getLocation()).getDescription());
        System.out.println("The final product will be a text-based adventure game in which the player will interact with a console to act within the game.");

        /**
         * Andy F.
         */
        Thread seconds = new Thread();
        seconds.start();
        // this will make the program wait 10 seconds
        try {
            seconds.sleep(10000);
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }

        System.out.println("Enter your code name!");
        String codeName = input.nextLine();

        while (true){
            String in = input.nextLine();
            in = in.toLowerCase();

            String [] command = in.split(" ");

            // commands that are one word
            if (command.length==1){
                if (command[0].equals("help")){
                    System.out.println("---------------------Command Menu---------------------------");
                    System.out.println("Save: This will save the game");
                    System.out.println("Quit: This will quit the game without saving the game");
                    System.out.println("Save & Quit : This will save and quit the game");
                    System.out.println("Show Health: This will show player current health");
                    System.out.println("Navigation: North, South, West, East is used to travel between rooms");
                    System.out.println("Unlock: unlocks door if key is present in inventory");
                    System.out.println("Search: shows all items in the current room");
                    System.out.println("Attack: command to attack any monster in the room");
                    System.out.println("Flee: This will flee the battle and player will restore Hp before battle");
                    System.out.println("Drop: This will drop an item in your current inventory");
                    System.out.println("Pickup / Take: This will pick up any item in current room");
                    System.out.println("Explore : this will look at an item description");
                    System.out.println("Hint : This will give a hint on where the key card will go ");
                    System.out.println("Equip: Allows to equip items to player hands");
                    System.out.println("unequipped: put item back in their inventory");
                    System.out.println("Consume: allows to eat an food item to restore health");
                    System.out.println("Shoot: shots gun if gun is equip");
                    System.out.println("Reload: reload guns");
                    System.out.println("Melee: attack monster with either item equip or hand");
                    System.out.println("Wear: equip armor items to increase defense");
            }
                else if (command[0].equals("quit")){
                    System.out.println("Thanks for playing");
                    Scanner saver = new Scanner(System.in);
                    System.out.println("would you like to say, type y to save if not hit any other key");
                    String choice =saver.nextLine();
                    choice= choice.toLowerCase();
                        if(choice.equals("y")){
                            // save
                        }else {
                            System.exit(0);
                        }
                }
                else  if (command[0].equals("save")){
                    System.out.println("Game will attempt to save");
                    //save
                }
            }
            else if (command[0].equals("pickup")){
                if (command.length >= 2) {
                    String temp  = "";
                    for (int i = 1; i < command.length; i++) {
                        temp = temp + command[i] + " ";
                    }
                    temp = temp.trim();
                    player.add(temp, rooms);

                } else {
                    System.out.println(command[1] + " not doesn't exist, please try again!");
                }
            }
            else if (command[0].equals("drop")){
                if (command.length >= 2) {
                    String temp = "";
                    for (int i = 1; i < command.length; i++) {
                        temp = temp + command[i] + " ";
                    }
                    temp = temp.trim();
                    player.drop(temp, rooms);
                } else {
                    System.out.println(command[1] + "not found/doesn't exist, please try again!");
            }
        }
            else if (command[0].equals("search")) { // if player wants to look
                // if player wants to look at object
                if (command.length >= 2) {
                    String temp = "";
                    for (int i = 1; i < command.length; i++) {
                        temp = temp + command[i] + " ";
                    }
                    temp = temp.trim();
                    player.look(temp);
                } else if (command.length == 1) { // else want to look at room
                    player.look(rooms);}
                else {
                    System.out.println(command[1] + "not found/doesn't exist, please try again!");
                }}
            else if (command[0].equals("north") || command[0].equals("south") || command[0].equals("east")
                    || command[0].equals("west") || command[0].equals("n") || command[0].equals("s") || command[0].equals("e")
                    || command[0].equals("w")) {

                player.movement(command[0], rooms);
                System.out.println(rooms.get(player.getLocation()).getDescription());
    }
            else if(command[0].equals("consume")){
                if (command.length >= 2){
                    String temp = "";
                    for (int i = 0 ; i < command.length; i++) {
                        temp = temp + command[i] + " ";
                    }
                    temp=temp.trim();
                    player.consume(temp);
            }
        }
            else if (command[0].equals("equip")){
                if (command.length >= 2){
                    String temp = "";
                    for (int i = 0 ; i < command.length; i++) {
                        temp = temp + command[i] + " ";
                    }
                    temp.trim();
                    player.equipWeapon(temp);
            }
    }
        }
    }

    // Andy
    // This is still in the works but just an idea for the amount
    public static void save(){
        try {
            FileOutputStream fos = new FileOutputStream("Save.bat");
            ObjectOutput object = new ObjectOutputStream(fos);
           // object.writeObject();
            object.flush();
            object.close();
            System.out.println("Game is saved");
        } catch (Exception ex){
            System.out.println("Serialization Error! Can't sava data.");
        }
    }

}
