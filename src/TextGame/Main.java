package TextGame;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Player player = Read.createPlayer();

        HashMap<String, Item> items = Read.createItems();
        HashMap<String, Puzzle> puzzles = Read.createPuzzles();
        HashMap<String, Monster> monsters = Read.createMonsters();
        HashMap<String, Room> rooms = Read.createRooms(items, puzzles, monsters);
        ArrayList <String> flags = new ArrayList <String>();
        ArrayList <String> pflags = new ArrayList <String>();
        ArrayList <String> mflags = new ArrayList <String>();
        ArrayList <String> visitedRooms = new ArrayList <String>();
        Integer currentAttempt;
        Boolean doubleatt = false;


        try (Scanner in = new Scanner(System.in)) {
            System.out.println('\n' + "Enter your codename:" + '\n');
            String codename = in.nextLine();
            codename = codename.toUpperCase();
            System.out.println("You snuck aboard and landed in the");
            System.out.println("hallway on the first deck.");
            System.out.println("Find your target " + codename + "...\n");
            System.out.println(rooms.get(player.getLocation()).getName() + " not visited" + '\n');
            System.out.println(rooms.get(player.getLocation()).getDescription());
            System.out.println("Type look or l to retrieve the description of the room");
            flags.add("0");


            Boolean again = true;

            GAME:
            while (again) {
                String input = in.nextLine();
                input = input.toLowerCase();
                String[] command = input.split(" ");

                if (command[0].equals("get") || (command[0].equals("g"))) {

                    if (command.length >= 2) {
                        String temp = "";

                        for (int i = 1; i < command.length; i++) {
                            temp = temp + command[i] + " ";
                        }
                        temp = temp.trim();

                        player.add(temp, rooms);


                    } else {
                        System.out.println("Item not found/doesn't exist, please try again!" + '\n');
                    }

                } else if (command[0].equals("drop") || (command[0].equals("d"))) {

                    if (command.length >= 2) {
                        String temp = "";

                        for (int i = 1; i < command.length; i++) {
                            temp = temp + command[i] + " ";
                        }
                        temp = temp.trim();
                        player.drop(temp, rooms);


                    } else {
                        System.out.println("Item not found/doesn't exist, please try again!" + '\n');
                    }

                } else if (command[0].equals("unlock") || (command[0].equals("c"))) {

                    if (command.length == 1) {

                        player.unlock(rooms);

                    } else {
                        System.out.println("Item not found/doesn't exist, please try again!" + '\n');
                    }

                } else if (command[0].equals("reload") || (command[0].equals("r"))) {

                    if (command.length == 1) {

                        player.reload();
                    } else {
                        System.out.println("Ammo not found/doesn't exist, please try again!" + '\n');
                    }

                } else if (command[0].equals("equip") || (command[0].equals("x"))) {

                    if (command.length >= 2) {
                        String temp = "";

                        for (int i = 1; i < command.length; i++) {
                            temp = temp + command[i] + " ";
                        }
                        temp = temp.trim();

                        player.equip(temp);
                    } else {
                        System.out.println("Item not found/doesn't exist, please try again!" + '\n');
                    }

                } else if (command[0].equals("unequip") || (command[0].equals("u"))) {

                    if (command.length == 1) {

                        player.unequip();
                    } else {
                        System.out.println("Item not found/doesn't exist, please try again!" + '\n');
                    }

                }else if (command[0].equals("inspect") || (command[0].equals("i"))) {

                    if (command.length >= 2) {
                        String temp = "";

                        for (int i = 1; i < command.length; i++) {
                            temp = temp + command[i] + " ";
                        }
                        temp = temp.trim();
                        Item it = items.get(temp);
                        it.inspect();
                        
                    } else {
                        System.out.println("Item not found/doesn't exist, please try again!" + '\n');
                    }

                } else if (command[0].equals("wear") || (command[0].equals("f"))) {

                    if (command.length == 1) {

                        player.wear();
                    } else {
                        System.out.println("Item not found/doesn't exist, please try again!" + '\n');
                    }

                } else if (command[0].equals("heal") || (command[0].equals("h"))) {

                    if (command.length >= 2) {
                        String temp = "";

                        for (int i = 1; i < command.length; i++) {
                            temp = temp +command[i] + " ";
                        }
                        temp = temp.trim();

                        player.heal(temp);
                    } else {
                        System.out.println("Item not found/doesn't exist, please try again!" + '\n');
                    }

                } else if (command[0].equals("look") || (command[0].equals("l"))) {

                    if (command.length >= 2) {
                        String temp = "";

                        for (int i = 1; i < command.length; i++) {
                            temp = temp + command[i] + " ";
                        }
                        temp = temp.trim();
                        System.out.print(rooms.get(player.getLocation()).getName());
                        visitedRooms.add(rooms.get(player.getLocation()).getName());

                        if (!flags.contains(rooms.get(player.getLocation()).getId())) {
                            System.out.println(" not visited" + '\n');
                            flags.add(rooms.get(player.getLocation()).getId());
                        } else {
                            System.out.println(" visited" + '\n');

                        }
                        if (player.getEquipment().containsKey(temp)) {
                            player.looke(temp);
                        } else {
                            player.look(temp);
                        }
                    } else if (command.length == 1) {
                        System.out.print(rooms.get(player.getLocation()).getName());

                        if (!flags.contains(rooms.get(player.getLocation()).getId())) {
                            System.out.println(" not visited" + '\n');
                            flags.add(rooms.get(player.getLocation()).getId());
                        } else {
                            System.out.println(" visited" + '\n');

                        }
                        player.look(rooms);
                    } else {
                        System.out.println("Item not found/doesn't exist, please try again!" + '\n');
                    }

                } else if (command[0].equals("journal") || (command[0].equals("j"))) {

                    player.getJournal();

                } else if (command[0].equals("backpack") || (command[0].equals("b"))) {

                    player.getBackpack();

                } else if (command[0].equals("north") || command[0].equals("south") || command[0].equals("east") || command[0].equals("west") ||
                        command[0].equals("n") || command[0].equals("s") || command[0].equals("e") || command[0].equals("w")) {

                    player.move(command[0], rooms);

                    if (rooms.get(player.getLocation()).getPuzzle().containsKey(player.getLocation())) {
                        currentAttempt = Integer.parseInt(rooms.get(player.getLocation()).getPuzzle().get(player.getLocation()).getAttempt());
                        while (currentAttempt > 0 && !pflags.contains(rooms.get(player.getLocation()).getId())) {
                            System.out.println(rooms.get(player.getLocation()).getPuzzle().get(player.getLocation()).getDescription());
                            System.out.println("Current attempt left: " + currentAttempt + '\n');
                            String Solution = rooms.get(player.getLocation()).getPuzzle().get(player.getLocation()).getName();
                            input = in.nextLine();
                            input = input.toLowerCase();
                            command = input.split(" ");
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
                        }else {
                            System.out.println(" visited" + '\n');
                        }
                        System.out.println(rooms.get(player.getLocation()).getDescription());
                        System.out.println("Type look or l to retrieve the description of the room");
                    }else{
                        System.out.print(rooms.get(player.getLocation()).getName());

                        if (!flags.contains(rooms.get(player.getLocation()).getId())) {
                            System.out.println(" not visited" + '\n');
                            flags.add(rooms.get(player.getLocation()).getId());
                        }else {
                            System.out.println(" visited" + '\n');

                        }
                        System.out.println(rooms.get(player.getLocation()).getDescription());
                        System.out.println("Type look or l to retrieve the description of the room");
                    }

                    if (rooms.get(player.getLocation()).getMonster().containsKey(player.getLocation())) {

                        Monster mob = rooms.get(player.getLocation()).getMonster().get(player.getLocation());

                        int min = mob.getLowattack();
                        int max = mob.getHighattack();
                        mob.setAttack(min + (int)(Math.random() * ((max - min) + 1 )));

                        while (!mflags.contains(rooms.get(player.getLocation()).getId())) {
                            System.out.println(codename + " is fighting " + mob.getName() + ".");
                            System.out.println(mob.getDescription() );

                            input = in.nextLine();
                            input = input.toLowerCase();
                            command = input.split(" ");
                            if (command[0].equals("examine") || command[0].equals("m")) {
                                System.out.println(mob.getName() + '\n');
                                System.out.println(mob.getDescription() + "The attack damage of monster is " + mob.getAttack());
                                System.out.println("Type battle or b to battle the monster");
                                System.out.println("Type ignore or i to ignore the monster");
                                input = in.nextLine();
                                input = input.toLowerCase();
                                command = input.split(" ");
                                if (command[0].equals("battle") || command[0].equals("b")) {
                                    while (!(player.getHealth() <= 0 || mob.getHealth() <= 0)) {
                                        System.out.print("HP " + player.getHealth() + "\t\t\t" + codename);
                                        if (player.getArmor() > 0) {
                                            System.out.println("/" + player.getArmor() + "\t\t\t" + codename);
                                        } else {
                                            System.out.println();
                                        }
                                        if (!player.getEquipment().isEmpty() && player.getEquipment().get(player.getEquipname()).getMag() > 0) {
                                            System.out.println("ATK " + player.getAttack() + '\n');
                                        } else {
                                            System.out.println("ATK " + player.getBaseatt() + '\n');
                                        }
                                        System.out.println("HP " + mob.getHealth() + "\t\t\t" + mob.getName());
                                        System.out.println("ATK " + mob.getAttack());
                                        if (!player.getEquipment().isEmpty() && player.getEquipment().get(player.getEquipname()).getMag() > 0) {
                                            System.out.println("Type shoot or z to shoot the monster");
                                        }
                                        if (!player.getEquipment().isEmpty() && player.containInventory("ammo") && player.getEquipment().get(player.getEquipname()).getMag() <  player.getEquipment().get(player.getEquipname()).getMaxmag()) {
                                            System.out.println("Type reload or r to shoot the monster");
                                        }
                                        System.out.println("Type attack or a to attack the monster");
                                        System.out.println("Type heal or h to heal the player");
                                        input = in.nextLine();
                                        input = input.toLowerCase();
                                        command = input.split(" ");
                                        if (command[0].equals("attack") || command[0].equals("a")) {
                                            player.attack(rooms);
                                            if (mob.getHealth() <= 0) {
                                                mflags.add(rooms.get(player.getLocation()).getId());
                                                System.out.println("GREAT ENEMY FELLED" + '\n');
                                                for (int i = 0; i < mob.getItems().length; i++) {
                                                    Room room = rooms.get(player.getLocation());
                                                    System.out.print(mob.getItems()[i] + ", ");
                                                    if (room.getInventory().containsKey(mob.getItems()[i])) {
                                                        room.getInventory().get(mob.getItems()[i]).setMobloot("0");
                                                        room.getInventory().get(mob.getItems()[i]).setRoomloot("1");
                                                    }
                                                }
                                                System.out.println("dropped in the room."  + '\n');

                                                System.out.print(rooms.get(player.getLocation()).getName());

                                                if (!flags.contains(rooms.get(player.getLocation()).getId())) {
                                                    System.out.println(" not visited" + '\n');
                                                    flags.add(rooms.get(player.getLocation()).getId());
                                                }else {
                                                    System.out.println(" visited" + '\n');
                                                }
                                                System.out.println(rooms.get(player.getLocation()).getDescription());
                                                System.out.println("Type look or l to retrieve the description of the room");

                                            } else if (player.getHealth() <= 0) {
                                                player.setLife(player.getLife() - 1);
                                                System.out.println("YOU DIED" + '\n');
                                                System.out.println("Life: " + player.getLife());
                                                if (player.getLife() > 0) {
                                                    System.out.println("Type continue or c to continue this game");
                                                }
                                                System.out.println("Type start or s to start a new game");
                                                System.out.println("Type quit or q to quit the game");
                                                input = in.nextLine();
                                                input = input.toLowerCase();
                                                command = input.split(" ");
                                                if (command[0].equals("continue") || command[0].equals("c")) {
                                                    if (player.getLife() > 0) {
                                                        player.setLocation("0");
                                                        player.setHealth(player.getMaxhealth());
                                                        System.out.print(rooms.get(player.getLocation()).getName());
                                                        if (!flags.contains(rooms.get(player.getLocation()).getId())) {
                                                            System.out.println(" not visited" + '\n');
                                                            flags.add(rooms.get(player.getLocation()).getId());
                                                        }else {
                                                            System.out.println(" visited" + '\n');
                                                        }
                                                        System.out.println(rooms.get(player.getLocation()).getDescription());
                                                        System.out.println("Type look or l to retrieve the description of the room");
                                                        continue GAME;
                                                    } else {
                                                        System.out.println("Invalid command");
                                                    }

                                                } else if (command[0].equals("start") || command[0].equals("s")) {
                                                    player.getInventory().clear();
                                                    player.getEquipment().clear();
                                                    Main.main(args);

                                                } else if (command[0].equals("quit") || command[0].equals("q")) {
                                                    System.out.println("Thank you for playing!");
                                                    again = false;
                                                } else {
                                                    System.out.println("Invalid command");
                                                }
                                            }
                                        } else if (command[0].equals("shoot") || (command[0].equals("z"))) {
                                            if (!player.getEquipment().isEmpty() && player.getEquipment().get(player.getEquipname()).getMag() > 0) {
                                                player.shoot(rooms);
                                                if (mob.getHealth() <= 0) {
                                                    mflags.add(rooms.get(player.getLocation()).getId());
                                                    System.out.println("GREAT ENEMY FELLED" + '\n');
                                                    for (int i = 0; i < mob.getItems().length; i++) {
                                                        Room room = rooms.get(player.getLocation());
                                                        System.out.print(mob.getItems()[i] + ", ");
                                                        if (room.getInventory().containsKey(mob.getItems()[i])) {
                                                            room.getInventory().get(mob.getItems()[i]).setMobloot("0");
                                                            room.getInventory().get(mob.getItems()[i]).setRoomloot("1");
                                                        }
                                                    }
                                                    System.out.println("dropped in the room."  + '\n');

                                                    System.out.print(rooms.get(player.getLocation()).getName());

                                                    if (!flags.contains(rooms.get(player.getLocation()).getId())) {
                                                        System.out.println(" not visited" + '\n');
                                                        flags.add(rooms.get(player.getLocation()).getId());
                                                    }else {
                                                        System.out.println(" visited" + '\n');
                                                    }
                                                    System.out.println(rooms.get(player.getLocation()).getDescription());
                                                    System.out.println("Type look or l to retrieve the description of the room");

                                                } else if (player.getHealth() <= 0) {
                                                    player.setLife(player.getLife() - 1);
                                                    System.out.println("YOU DIED" + '\n');
                                                    System.out.println("Life: " + player.getLife());
                                                    if (player.getLife() > 0) {
                                                        System.out.println("Type continue or c to continue this game");
                                                    }
                                                    System.out.println("Type start or s to start a new game");
                                                    System.out.println("Type quit or q to quit the game");
                                                    input = in.nextLine();
                                                    input = input.toLowerCase();
                                                    command = input.split(" ");
                                                    if (command[0].equals("continue") || command[0].equals("c")) {
                                                        if (player.getLife() > 0) {
                                                            player.setLocation("0");
                                                            player.setHealth(player.getMaxhealth());
                                                            System.out.print(rooms.get(player.getLocation()).getName());
                                                            if (!flags.contains(rooms.get(player.getLocation()).getId())) {
                                                                System.out.println(" not visited" + '\n');
                                                                flags.add(rooms.get(player.getLocation()).getId());
                                                            }else {
                                                                System.out.println(" visited" + '\n');
                                                            }
                                                            System.out.println(rooms.get(player.getLocation()).getDescription());
                                                            System.out.println("Type look or l to retrieve the description of the room");
                                                            continue GAME;
                                                        } else {
                                                            System.out.println("Invalid command");
                                                        }

                                                    } else if (command[0].equals("start") || command[0].equals("s")) {
                                                        player.getInventory().clear();
                                                        player.getEquipment().clear();
                                                        Main.main(args);

                                                    } else if (command[0].equals("quit") || command[0].equals("q")) {
                                                        System.out.println("Thank you for playing!");
                                                        again = false;
                                                    } else {
                                                        System.out.println("Invalid command");
                                                    }
                                                }
                                            } else {
                                                System.out.println("The mag is empty. Please use the reload command before shooting.");
                                            }

                                        } else if (command[0].equals("reload") || (command[0].equals("r"))) {

                                            if (command.length == 1) {

                                                player.reload();
                                            } else {
                                                System.out.println("Ammo not found/doesn't exist, please try again!" + '\n');
                                            }

                                        } else if (command[0].equals("equip") || (command[0].equals("x"))) {

                                            if (command.length >= 2) {
                                                String temp = "";

                                                for (int i = 1; i < command.length; i++) {
                                                    temp = temp + command[i] + " ";
                                                }
                                                temp = temp.trim();

                                                player.equip(temp);
                                            } else {
                                                System.out.println("Item not found/doesn't exist, please try again!" + '\n');
                                            }

                                        } else if (command[0].equals("unequip") || (command[0].equals("u"))) {

                                            if (command.length == 1) {

                                                player.unequip();
                                            } else {
                                                System.out.println("Item not found/doesn't exist, please try again!" + '\n');
                                            }

                                        } else if (command[0].equals("wear") || (command[0].equals("f"))) {

                                            if (command.length == 1) {

                                                player.wear();
                                            } else {
                                                System.out.println("Item not found/doesn't exist, please try again!" + '\n');
                                            }

                                        } else if (command[0].equals("heal") || (command[0].equals("h"))) {

                                            if (command.length >= 2) {
                                                String temp = "";

                                                for (int i = 1; i < command.length; i++) {
                                                    temp = temp +command[i] + " ";
                                                }
                                                temp = temp.trim();

                                                player.heal(temp);
                                            } else {
                                                System.out.println("Item not found/doesn't exist, please try again!" + '\n');
                                            }

                                        } else if (command[0].equals("look") || (command[0].equals("l"))) {

                                            if (command.length >= 2) {
                                                String temp = "";

                                                for (int i = 1; i < command.length; i++) {
                                                    temp = temp + command[i] + " ";
                                                }
                                                temp = temp.trim();
                                                System.out.print(rooms.get(player.getLocation()).getName());

                                                if (!flags.contains(rooms.get(player.getLocation()).getId())) {
                                                    System.out.println(" not visited" + '\n');
                                                    flags.add(rooms.get(player.getLocation()).getId());
                                                }else {
                                                    System.out.println(" visited" + '\n');

                                                }
                                                if (player.getEquipment().containsKey(temp)) {
                                                    player.looke(temp);
                                                }else {
                                                    player.look(temp);
                                                }

                                            } else if (command.length == 1) {
                                                System.out.print(rooms.get(player.getLocation()).getName());

                                                if (!flags.contains(rooms.get(player.getLocation()).getId())) {
                                                    System.out.println(" not visited" + '\n');
                                                    flags.add(rooms.get(player.getLocation()).getId());
                                                }else {
                                                    System.out.println(" visited" + '\n');

                                                }
                                                player.look(rooms);
                                            } else {
                                                System.out.println("Item not found/doesn't exist, please try again!" + '\n');
                                            }
                                        } else if (command[0].equals("backpack") || (command[0].equals("b"))) {

                                        player.getBackpack();

                                        } else {
                                            System.out.println("Item not found/doesn't exist, please try again!" + '\n');
                                        }

                                    }

                                } else if (command[0].equals("ignore") || command[0].equals("i")) {

                                    System.out.print(rooms.get(player.getLocation()).getName());

                                    if (!flags.contains(rooms.get(player.getLocation()).getId())) {
                                        System.out.println(" not visited" + '\n');
                                        flags.add(rooms.get(player.getLocation()).getId());
                                    }else {
                                        System.out.println(" visited" + '\n');
                                    }
                                    System.out.println(rooms.get(player.getLocation()).getDescription());
                                    System.out.println("Type look or l to retrieve the description of the room");
                                    break;

                                } else {
                                    System.out.println("No such command, please try again");
                                }
                            } else {
                                System.out.println("No such command, please try again");
                            }
                        }
                    }
                } else if (command[0].equals("help") || command[0].equals("h")) {
                	System.out.println("---------------------Command Menu---------------------------");
					System.out.println("Look: This will show the description of the current room.");
					System.out.println("Navigation: North, South, West, East is used to travel between rooms");
					System.out.println("Unlock: unlocks door if key is present in inventory");
					System.out.println("Drop: This will drop an item in your current inventory");
					System.out.println("Get: This will pick up any item in current room");
					System.out.println("Inspect : this will look at an item description");
					System.out.println("Equip: Allows to equip items to player hands");
					System.out.println("Unequip: put item back in their inventory");
					System.out.println("Reload: reload guns");
					System.out.println("Examine: Examine monster to encounter battle");
					System.out.println("Battle: Fight the monster to battle");
					System.out.println("Ignore: Ignore monster to avoid battle");
					System.out.println("Attack: Attack the monster in a battle");
					System.out.println("Heal : This will heal player health ");
					System.out.println("Wear: equip armor items to increase defense");
					System.out.println("Save: This will save the game");
					System.out.println("Quit: This will quit the game without saving the game");
                } else if (command[0].equals("save") || command[0].equals("sa")) {
                   save(visitedRooms, player, rooms);

                }
                else if (command[0].equals("quit") || command[0].equals("q")) {
                    System.out.println("Thank you for playing!");
                    again = false;

                } else {
                    System.out.println("No such command, please try again");
                }
                if (player.getLocation().equals(String.valueOf(rooms.size()))) {
                    if (player.containInventory("handle of the bell") && player.containInventory("body of the bell")) {
                        System.out.println('\n' + "Congratulation. The gate has opened upon the ring of the bell." + '\n'
                                + "A vast kingdom can be seen ahead.");
                        System.out.println("You finished the game! Thank you for playing!");
                        System.exit(0);
                    } else {
                        System.out.println('\n' + "The gate is locked ahead. The ring of a bell is the key to raise the gate.");
                    }
                }
            }
        }catch(NullPointerException e) {
            System.out.println("Item not found/doesn't exist, please try again!" + '\n');

        }
    }

    public static void save(ArrayList<String> visitedRooms, Player player, HashMap<String, Room> rooms ) {
    	   try {
               FileOutputStream fos = new FileOutputStream("Save.bat");
               ObjectOutput object = new ObjectOutputStream(fos);

               //Save Visited rooms
               object.writeObject("Visited Rooms");
               object.writeObject(visitedRooms);

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