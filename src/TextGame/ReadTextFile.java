package TextGame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ReadTextFile {
    public static HashMap<String, Room> createRooms(HashMap<String, Item> items ,HashMap <String, Monster> monsters) {
        try {
            // creating a reader to read the text file
            BufferedReader reader = new BufferedReader(new FileReader(".idea/Room.txt"));
            String line = reader.readLine();
            HashMap<String, Room> rooms = new HashMap<String, Room>();

            while (line != null) { // while we can still read from file
                String name = line;
                // line will equal the next line able to read
                line = reader.readLine();

                String roomNum = line;
                line = reader.readLine();

                String deckNum = line;
                line = reader.readLine();

                String roomId = line;
                line=reader.readLine();

                String[] neighbors = line.split(",");
                for (int i = 0; i < neighbors.length; i++) {
                    neighbors[i] = neighbors[i].trim();
                }

                line = reader.readLine();
                String description = "";

                while (!line.equals("END")) { // while reader has not hit "END"
                    // add each line to overall string for description
                    description = description + line + '\n';
                    line = reader.readLine();
                }

                // put new room object and rooms name in HashMap
                rooms.put(name, new Room(name, roomNum,deckNum,roomId,description, neighbors,  items, monsters));

                line = reader.readLine(); // move line to beginning of next room

            }

            return rooms;
        } catch (IOException e) {
            System.out.println("Room File could not be accessed, try again!");
        }
        return null;
    }

    public static HashMap<String, Monster> createMonster() {
        try{
            BufferedReader reader = new BufferedReader(new FileReader("Monster.txt"));
            String line = reader.readLine();
            HashMap<String, Monster> monsters = new HashMap<String, Monster> ();

            while (line != null){
                String itemID = line ;
                line = reader.readLine();

                String location = line;
                line = reader.readLine();

                String name = line;
                line = reader.readLine();

                String hp = line;
                Integer health =Integer.valueOf(hp);
                line = reader.readLine();

                String lowAttack = Integer.parseInt(line);
                line = reader.readLine();
				
				String highAttack = Integer.parseInt(line);
				line = reader.readLine();
				
				String keyChance = Integer.parseInt(line);
				line = reader.readLine();
				
				String keyName = line;
                line = reader.readLine();

                String foodChance = Integer.parseInt(line);
                line = reader.readLine();

                String item = line;
                line = reader.readLine();
               
                if(line.equals(9)){
					 String[] neighbors = line.split(",");
                for (int i = 0; i < neighbors.length; i++) { // travers
                    // neighbors
                    // trim each name
                    neighbors[i] = neighbors[i].trim();
                }
				
				}
				line = reader.readLine();

                String description = "";

                while (!line.equals("END")){
                    description = description + line + '\n';
                    line = reader.readLine();
                }
                monsters.put(name, new Monster(name, health , attack_,location,description, itemID, chance, item, chanceOfKey, keyName ));

                line = reader.readLine();
            }
            return monsters;
        } catch (IOException e) {
            System.out.println("File not found for monsters");
        }

        return null;
    }
    
    
	/**
	 * Method: createItems()
	 * 
	 * This method creates and initialize all items found in the 
	 * Artifact.txt store them in the HashMap
	 * 
	 * @return a list of items which are then stored in items variable
	 */
	public static HashMap<String, Item> createItems() {
		
		//Try and catch blocks to handle exceptions
		try {
			BufferedReader reader = new BufferedReader(new FileReader("Artifact.txt"));
			String line = reader.readLine();
			HashMap<String, Item> items = new HashMap<String, Item>();

			while (line != null) { 
				
				String itemId = line;
				line = reader.readLine();
				
				String itemName = line;
				line = reader.readLine();

				String location = line;
				line = reader.readLine();
				
				String itemType = line;
				line = reader.readLine();
				
				String itemDescription = "";
				
				String itemFeatures = line;
				line = reader.readLine();
				
				String itemUsage = line;
				line = reader.readLine();
				
				String strength = line;
				Double itemStrength = Double.valueOf(strength);
				line = reader.readLine();
				
				while (!line.equals("END")) { 
					
					// add each line to overall string for description
					itemDescription = itemDescription + line + '\n';
					line = reader.readLine();
				}

				// put new item object and items name in HashMap
				items.put(itemName, new Item(itemId, itemName, location, itemType, itemDescription, itemFeatures, itemUsage, itemStrength));

				line = reader.readLine(); // move line to beginning of next item
											
			}
			return items;
			
		} 
		catch (IOException e) {
			System.out.println("File could not be accessed, please try again!");
		}
		return null;
	}
}
