package TextGame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ReadTextFile {
    public static HashMap<String, Room> createRooms() {
        try {
            // creating a reader to read the text file
            BufferedReader reader = new BufferedReader(new FileReader("Room.txt"));
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
                rooms.put(name, new Room(name, roomNum,deckNum,roomId,description, neighbors));

                line = reader.readLine(); // move line to beginning of next room

            }

            return rooms;
        } catch (IOException e) {
            System.out.println("Room File could not be accessed, try again!");
        }
        return null;
    }
}
