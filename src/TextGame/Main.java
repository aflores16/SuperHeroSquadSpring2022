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
        Integer currentAttempt;
        Boolean doubleatt = false;


        try (Scanner in = new Scanner(System.in)) {
            System.out.println('\n' + "Welcome to Dark Souls" + '\n');
            System.out.println("Type quit or q to quit the game" + '\n');
            System.out.println(rooms.get(player.getLocation()).getName() + " not visited" + '\n');
            System.out.println(rooms.get(player.getLocation()).getDescription());
            System.out.println("Type look or l to retrieve the description of the room");
            flags.add("1");
            Boolean again = true;


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
                            System.out.println('\n' + "BOSS ROOM" + '\n');
                            System.out.println("There appears to be a monster in the room");
                            System.out.println("Type examine or m to examine the monster of the room");

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
                                        System.out.print("Player                HP  " + player.getHealth());
                                        if (player.getArmor() > 0) {
                                            System.out.println("/" + player.getArmor());
                                        } else {
                                            System.out.println();
                                        }
                                        if (!player.getEquipment().isEmpty() && player.getEquipment().get(player.getEquipname()).getMag() > 0) {
                                            System.out.println("                      ATK " + player.getAttack() + '\n');
                                        } else {
                                            System.out.println("                      ATK " + player.getBaseatt() + '\n');
                                        }
                                        System.out.println(mob.getName() + "        HP  " + mob.getHealth());
                                        System.out.println("                      ATK " + mob.getAttack());
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
                                                System.out.println("YOU DIED" + '\n');
                                                System.out.println("Type start or s to start a new game");
                                                System.out.println("Type quit or q to quit the game");
                                                input = in.nextLine();
                                                input = input.toLowerCase();
                                                command = input.split(" ");
                                                if (command[0].equals("start") || command[0].equals("s")) {
                                                    player.getInventory().clear();
                                                    player.getEquipment().clear();
                                                    Main.main(args);

                                                } else if (command[0].equals("quit") || command[0].equals("q")) {
                                                    System.out.println("Thank you for playing!");
                                                    again = false;
                                                }
                                            }
                                        } else if (command[0].equals("shoot") || (command[0].equals("z"))) {
                                            if (!player.getEquipment().isEmpty() && player.getEquipment().get(player.getEquipname()).getMag() > 0) {
                                                player.shoot(rooms);
                                                if (mob.getHealth() <= 0) {
                                                    mflags.add(rooms.get(player.getLocation()).getId());
                                                    System.out.println("GREAT ENEMY FELLED" + '\n');

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
                                                    System.out.println("YOU DIED" + '\n');
                                                    System.out.println("Type start or s to start a new game");
                                                    System.out.println("Type quit or q to quit the game");
                                                    input = in.nextLine();
                                                    input = input.toLowerCase();
                                                    command = input.split(" ");
                                                    if (command[0].equals("start") || command[0].equals("s")) {
                                                        player.getInventory().clear();
                                                        player.getEquipment().clear();
                                                        Main.main(args);

                                                    } else if (command[0].equals("quit") || command[0].equals("q")) {
                                                        System.out.println("Thank you for playing!");
                                                        again = false;
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
                } else if (command[0].equals("quit") || command[0].equals("q")) {
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
}