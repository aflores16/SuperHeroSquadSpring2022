package TextGame;

import java.io.*;
import java.util.*;

class Read {

    public static HashMap<String, Room> createRooms(HashMap<String, Item> items, HashMap<String, Puzzle> puzzles, HashMap<String, Monster> monsters) {
        try {

            BufferedReader reader = new BufferedReader(new FileReader("Rooms.txt"));
            String line = reader.readLine();
            HashMap<String, Room> rooms = new HashMap<String, Room>();

            while (line != null) {
                String name = line;

                String id = reader.readLine();

                line = reader.readLine();

                String[] neighbors = line.split(",");
                for (int i = 0; i < neighbors.length; i++) {
                    neighbors[i] = neighbors[i].trim();
                }

                line = reader.readLine();
                String description = "";

                while (!line.equals("END")) {
                    description = description + line + '\n';
                    line = reader.readLine();
                }

                rooms.put(id, new Room(name, id, neighbors, description, items, puzzles, monsters));

                line = reader.readLine();

            }
            reader.close();

            return rooms;
        } catch (IOException e) {
            System.out.println("File could not be accessed, try again!");
        }
        return null;
    }

    public static HashMap<String, Item> createItems() {
        try {

            BufferedReader reader = new BufferedReader(new FileReader("Items.txt"));
            String line = reader.readLine();
            HashMap<String, Item> items = new HashMap<String, Item>();

            while (line != null) {
                String name = line;

                name = name.toLowerCase();

                String id = reader.readLine();

                line = reader.readLine();

                String location = line.trim();

                line = reader.readLine();

                String type = line.trim();

                line = reader.readLine();

                Integer roomloot = Integer.parseInt(line);

                line = reader.readLine();

                Integer mobloot = Integer.parseInt(line);

                line = reader.readLine();

                Integer shoploot = Integer.parseInt(line);

                line = reader.readLine();

                Integer quantity = Integer.parseInt(line);

                line = reader.readLine();

                Integer attack = Integer.parseInt(line);

                line = reader.readLine();

                Integer mag = Integer.parseInt(line);

                line = reader.readLine();

                Double value = Double.parseDouble(line);

                line = reader.readLine();

                Double spawnrate = Double.parseDouble(line);

                line = reader.readLine();

                String description = "";

                while (!line.equals("END")) {
                    description = description + line + '\n';
                    line = reader.readLine();
                }

                items.put(name, new Item(name, id, description, location, type, roomloot, mobloot, shoploot, quantity, attack, mag, value, spawnrate));

                line = reader.readLine();

            }
            reader.close();

            return items;

        } catch (IOException e) {
            System.out.println("File could not be accessed, please try again!");
        }
        return null;
    }

    public static HashMap<String, Puzzle> createPuzzles() {
        try {

            BufferedReader reader = new BufferedReader(new FileReader("Puzzles.txt"));
            String line = reader.readLine();
            HashMap<String, Puzzle> puzzles = new HashMap<String, Puzzle>();

            while (line != null) {
                String name = line;

                name = name.toLowerCase();

                String id = reader.readLine();

                line = reader.readLine();

                String location = line.trim();

                line = reader.readLine();

                String attempt = line.trim();

                line = reader.readLine();

                String description = "";

                while (!line.equals("END")) {
                    description = description + line + '\n';
                    line = reader.readLine();
                }

                puzzles.put(location, new Puzzle(name, id, description, location, attempt));

                line = reader.readLine();

            }
            reader.close();

            return puzzles;

        } catch (IOException e) {
            System.out.println("File could not be accessed, please try again!");
        }
        return null;
    }

    public static HashMap<String, Monster> createMonsters() {
        try {

            BufferedReader reader = new BufferedReader(new FileReader("Monsters.txt"));
            String line = reader.readLine();
            HashMap<String, Monster> monsters = new HashMap<String, Monster>();

            while (line != null) {
                String name = line;

                String id = reader.readLine();

                line = reader.readLine();

                String[] items = line.split(",");
                for (int i = 0; i < items.length; i++) {
                    items[i] = items[i].trim();
                }

                line = reader.readLine();

                String location = line.trim();

                line = reader.readLine();

                Integer health = Integer.parseInt(line.trim());

                line = reader.readLine();

                Integer attack = Integer.parseInt(line.trim());

                line = reader.readLine();

                Integer lowattack = Integer.parseInt(line.trim());

                line = reader.readLine();

                Integer highattack = Integer.parseInt(line.trim());

                line = reader.readLine();

                Double spawnrate = Double.parseDouble(line.trim());

                line = reader.readLine();

                String description = "";

                while (!line.equals("END")) {
                    description = description + line + '\n';
                    line = reader.readLine();
                }

                monsters.put(location, new Monster(name, id, items, description, location, health, attack, lowattack, highattack, spawnrate));

                line = reader.readLine();

            }
            reader.close();

            return monsters;

        } catch (IOException e) {
            System.out.println("File could not be accessed, please try again!");
        }
        return null;
    }

    public static Player createPlayer() {
        try {

            BufferedReader reader = new BufferedReader(new FileReader("Player.txt"));
            String line = reader.readLine();
            Integer health = 0;
            Integer attack = 0;
            Integer life = 0;
            Player player = new Player(health, attack, life);

            while (line != null && !line.equals("END")) {
                health = Integer.parseInt(line);

                line = reader.readLine();

                attack = Integer.parseInt(line);

                line = reader.readLine();

                life = Integer.parseInt(line);

                player = new Player (health, attack, life);

                line = reader.readLine();

            }
            reader.close();

            return player;

        } catch (IOException e) {
            System.out.println("File could not be accessed, please try again!");
        }
        return null;
    }


}