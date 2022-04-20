package TextGame;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class Controller {
	public static HashMap<String, Monster> monsters;
	public static Player player;
	public static HashMap<String, Room> rooms;
	public static ArrayList<String> vistedRooms;

	public static void main(String[] args) {
		player = new Player();
		HashMap<String, Item> items = ReadTextFile.createItems();
		monsters = ReadTextFile.createMonster();
		HashMap<String, Puzzle>  puzzles = ReadTextFile.createPuzzles();
		rooms = ReadTextFile.createRooms(items, monsters, puzzles);
		ArrayList<String> flags = new ArrayList<String>();
		ArrayList<String> pflags = new ArrayList<String>();
		ArrayList<String> mflags = new ArrayList<String>();
		vistedRooms = new ArrayList<String>();
		Integer currentAttempt;
		Boolean doubleatt = false;
		Scanner input = new Scanner(System.in);

		// show current location of room

		System.out.println(
				"The final product will be a text-based adventure game in which the player will interact with a console to act within the game.");

		/**
		 * Andy F.
		 */
		Thread seconds = new Thread();
		seconds.start();
		// this will make the program wait 10 seconds
		try {
			seconds.sleep(10000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}

		System.out.println("Enter your code name!");
		String codeName = input.nextLine();

		System.out.println("hello " + codeName);

		System.out.println(rooms.get(player.getLocation()).getDescription());
		while (true) {
			String in = input.nextLine();
			in = in.toLowerCase();

			String[] command = in.split(" ");
			// commands that are one word

			if (command[0].equals("pickup") || command[0].contains("pickup") || command[0].contains("pic")) {
				if (command.length >= 2) {
					String temp = "";
					for (int i = 1; i < command.length; i++) {
						temp = temp + command[i] + " ";
					}
					temp = temp.trim();
					player.add(temp, rooms);

				} else {
					System.out.println(command[1] + " not doesn't exist, please try again!");
				}
			} else if (command[0].equals("drop") || command[0].contains("drop") || command[0].contains("d")) {
				if (command.length >= 2) {
					String temp = "";
					for (int i = 1; i < command.length; i++) {
						temp = temp + command[i] + " ";
					}
					temp = temp.trim();
					player.drop(temp, rooms);
				}else if (command.length >= 1) {
					System.out.println(command[0] + " not found/doesn't exist, please try again!");
				}
				else {
					System.out.println(command[1] + " not found/doesn't exist, please try again!");
				}
			} else if (command[0].equals("search") || command[0].contains("search") || command[0].contains("sea")) { // if
																														// player
																														// wants
																														// to
																														// look
				// if player wants to look at object
				if (command.length >= 2) {
					String temp = "";
					for (int i = 1; i < command.length; i++) {
						temp = temp + command[i] + " ";
					}
					temp = temp.trim();
					player.look(temp);
				} else if (command.length == 1) { // else want to look at room
					player.look(rooms);
				} else {
					System.out.println(command[1] + "not found/doesn't exist, please try again!");
				}
			} else if (command[0].equals("north") || command[0].equals("south") || command[0].equals("east")
					|| command[0].equals("west") || command[0].equals("n") || command[0].equals("so")
					|| command[0].equals("e") || command[0].equals("w") || command[0].contains("so") 
					|| command[0].contains("south") || command[0].contains("north") || command[0].contains("no") 
					|| command[0].contains("east") || command[0].contains("west") 
					|| command[0].contains("w")) {
				player.movement(command[0], rooms);
				System.out.println(rooms.get(player.getLocation()).getDescription());
				vistedRooms.add(rooms.get(player.getLocation()).getName());
				if (rooms.get(player.getLocation()).getPuzzle().containsKey(player.getLocation())) {
					currentAttempt = Integer.parseInt(
							rooms.get(player.getLocation()).getPuzzle().get(player.getLocation()).getAttempt());
					while (currentAttempt > 0 && !pflags.contains(rooms.get(player.getLocation()).getId())) {
						System.out.println(
								rooms.get(player.getLocation()).getPuzzle().get(player.getLocation()).getDescription());
						System.out.println("Current attempt left: " + currentAttempt + '\n');
						String Solution = rooms.get(player.getLocation()).getPuzzle().get(player.getLocation())
								.getName();
						in = input.nextLine();
						in = in.toLowerCase();
						command = in.split(" ");
						if (!command[0].equals(Solution)) {
							currentAttempt--;
						} else {
							System.out.println("Puzzle was Solved" + '\n');
							pflags.add(rooms.get(player.getLocation()).getId());

						}
					}
					System.out.print(rooms.get(player.getLocation()).getName());

					if (!flags.contains(rooms.get(player.getLocation()).getId())) {
						System.out.println(" not visited" + '\n');
						flags.add(rooms.get(player.getLocation()).getId());
					} else {
						System.out.println(" visited" + '\n');
					}
					System.out.println(rooms.get(player.getLocation()).getDescription());
					System.out.println("Type look or l to retrieve the description of the room");
				} else {
					System.out.print(rooms.get(player.getLocation()).getName());

					if (!flags.contains(rooms.get(player.getLocation()).getId())) {
						System.out.println(" not visited" + '\n');
						flags.add(rooms.get(player.getLocation()).getId());
					} else {
						System.out.println(" visited" + '\n');

					}
					System.out.println(rooms.get(player.getLocation()).getDescription());
					System.out.println("Type look or l to retrieve the description of the room");
				}

			} else if (command[0].equals("consume") || command[0].contains("consume") || command[0].contains("cons")) {
				if (command.length >= 2) {
					String temp = "";
					for (int i = 0; i < command.length; i++) {
						temp = temp + command[i] + " ";
					}
					temp = temp.trim();
					player.consume(temp);
				}
			} else if (command[0].equals("equip") || command[0].contains("equip") || command[0].contains("eq")) {
				if (command.length >= 2) {
					String temp = "";
					for (int i = 0; i < command.length; i++) {
						temp = temp + command[i] + " ";
					}
					temp.trim();
					player.equipWeapon(temp);
				}
			} else if (command[0].equals("unequip") || command[0].contains("unequip") || command[0].contains("uneq")) {
				if (command.length >= 2) {
					String temp = "";
					for (int i = 0; i < command.length; i++) {
						temp = temp + command[i] + " ";
					}
					temp.trim();
					player.unequipped(temp);
				}
			} else if (command[0].equals("attack") || command[0].contains("attack") || command[0].contains("att")) {
				if (command.length >= 2) {
					String temp = "";
					for (int i = 0; i < command.length; i++) {
						temp = temp + command[i] + " ";
					}
					temp.trim();
					// player.attack(temp);
				}
			} else if (command.length == 1) {
				
				if (command[0].equals("help")) {
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
				} else if (command[0].equals("quit")) {
					System.out.println("Thanks for playing");
					Scanner saver = new Scanner(System.in);
					System.out.println("would you like to save, type y to save if not hit any other key");
					String choice = saver.nextLine();
					choice = choice.toLowerCase();
					if (choice.equals("y")) {
						// save
						save();
						System.exit(0);
					} else {
						System.exit(0);
					}
				} else if (command[0].equals("save") || command[0].contains("save") || command[0].contains("s")) {
					System.out.println("Game will attempt to save");
					save();
				} else if (command[0].equals("flee") || command[0].contains("flee") || command[0].contains("f")) {

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
          
            
            // ??? artifacts
            
            // TODO: get Monster object, if in the room???
            
            //Save Visited rooms
            object.writeObject("Visited Rooms");
            object.writeObject(vistedRooms);
            
            //save current room.... This should also have the puzzle object
            object.writeObject("Current Room");
            Room r = rooms.get(player.getLocation());
            object.writeObject(r);
            
            //save player 
            object.writeObject("Player");
            object.writeObject(player);

            object.flush();
            object.close();
            System.out.println("Game is saved");
            
        } catch (Exception ex){
            System.out.println("Serialization Error! Can't sava data.");
        }
    }

}
