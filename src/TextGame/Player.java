package TextGame;

import java.io.Serializable;
import java.util.*;

public class Player implements Serializable  {

    private static String location_;
    private static String equipname_;
    private static String keyname_;
    private static Map<String, Item> inventory_ = new HashMap<String, Item>();
    private static Map<String, Item> equipment_ = new HashMap<String, Item>();
    private static Map<String, Puzzle> journal_ = new HashMap<String, Puzzle>();
    private static Integer health_;
    private static Integer attack_;
    private static Integer life_;
    private static Integer armor_;
    private static Integer maxhealth_;
    private static Integer baseatt_;

    public Player(Integer health, Integer attack, Integer life) {
        health_ = health;
        attack_ = attack;
        life_ = life;
        location_ = "0";
        maxhealth_ = health;
        baseatt_ = attack;
        armor_ = 0;
    }
    public String getLocation() {
        return location_;
    }

    public String getEquipname() {
        return equipname_;
    }

    public String getKeyname() {
        return keyname_;
    }

    public Integer getHealth() {
        return health_;
    }

    public Integer getAttack() {
        return attack_;
    }

    public Integer getLife() {
        return life_;
    }

    public Integer getArmor() {
        return armor_;
    }

    public Integer getMaxhealth() {
        return maxhealth_;
    }

    public Integer getBaseatt() {
        return baseatt_;
    }

    public void setLocation(String location_) {this.location_ = location_;

    }

    public void setKeyname(String keyname_) {
        this.keyname_ = keyname_;
    }

    public void setHealth(Integer health_) {
        this.health_ = health_;
    }

    public void setAttack(Integer attack_) {
        this.attack_ = attack_;
    }

    public void setLife(Integer life_) {
        this.life_ = life_;
    }

    public void setArmor(Integer armor_) {
        this.armor_ = armor_;
    }

    public void attack(HashMap<String, Room> rooms) {
        Monster mob = rooms.get(location_).getMonster().get(location_);
        if (getArmor() > 0) {
            if (getArmor() < mob.getAttack()) {
                int dif = mob.getAttack() - getArmor();
                health_ = getHealth() - dif;
            } else {
                armor_ = getArmor() - mob.getAttack();
            }
        } else {
            health_ = getHealth() - mob.getAttack();
        }
        mob.setHealth(mob.getHealth() - baseatt_);
    }

    public void shoot(HashMap<String, Room> rooms) {
        Monster mob = rooms.get(location_).getMonster().get(location_);
        if (getArmor() > 0) {
            if (getArmor() < mob.getAttack()) {
                int dif = mob.getAttack() - getArmor();
                health_ = getHealth() - dif;
            } else {
                armor_ = getArmor() - mob.getAttack();
            }
        } else {
            health_ = getHealth() - mob.getAttack();
        }
        mob.setHealth(mob.getHealth() - getAttack());
        equipment_.get(equipname_).setMag(equipment_.get(equipname_).getMag() - 1);
    }

    public void reload() {
        String ammo = "universal ammo";
        Item temp = null;

        if (inventory_.containsKey(ammo)) {
            temp = inventory_.get(ammo);
            if ((equipment_.get(equipname_).getMaxmag() - equipment_.get(equipname_).getMag()) >= temp.getQuantity()) {
                temp.setQuantity(0);
                inventory_.remove(ammo);
                equipment_.get(equipname_).setMag(equipment_.get(equipname_).getMag() + temp.getQuantity());
                System.out.println("Current mag:" + equipment_.get(equipname_).getMag());
                System.out.println("Current ammo:" + temp.getQuantity());
            } else {
                int diff = equipment_.get(equipname_).getMaxmag() - equipment_.get(equipname_).getMag();
                temp.setQuantity(temp.getQuantity() - diff);
                equipment_.get(equipname_).setMag(equipment_.get(equipname_).getMag() + diff);
                System.out.println("Current mag:" + equipment_.get(equipname_).getMag());
                System.out.println("Current ammo:" + temp.getQuantity());
            }
            System.out.println("Gun has been reloaded.");
            System.out.println('\n');
        } else {
            System.out.println("Sorry, ammo is not in your inventory." + '\n');
        }
    }


    public void heal(String item) {

        if (inventory_.containsKey(item)) {
            if (inventory_.get(item).getType().contains("Food")) {
                double heal = (((double)getMaxhealth()) * inventory_.get(item).getValue());
                if (getMaxhealth() < (getHealth() + (int)heal)) {
                    System.out.println("Health of the player was healed by " + (getMaxhealth() - getHealth()));
                    System.out.println("Player was successfully healed");
                    health_ = getMaxhealth();

                } else {
                    System.out.println("Health of the player was healed by " + (int)heal);
                    System.out.println("Player was successfully healed");
                    health_ = getHealth() + (int)heal;
                }
                if (inventory_.get(item).getQuantity() == 1) {
                    inventory_.remove(item);
                } else {
                    inventory_.get(item).setQuantity(inventory_.get(item).getQuantity() - 1);
                }
            } else {
                System.out.println("Sorry, " + item + " is not a Food type." + '\n');
            }
        } else {
            System.out.println("Sorry, " + item + " is not in your inventory." + '\n');
        }
    }

    public void wear() {
        String armor = "kevlar vest";

        if (inventory_.containsKey(armor)) {
            double buff = (((double)getMaxhealth()) * inventory_.get(armor).getValue());
            armor_ = (int)buff;
            System.out.println("Armor of the player increased to " + getArmor());
            System.out.println(armor + " was successfully worn");
            if (inventory_.get(armor).getQuantity() == 1) {
                inventory_.remove(armor);
            } else {
                inventory_.get(armor).setQuantity(inventory_.get(armor).getQuantity() - 1);
            }
        } else {
            System.out.println("Sorry, " + armor + " is not in your inventory." + '\n');
        }
    }

    public void equip(String item) {
        Item temp = null;

        if (inventory_.containsKey(item) && (inventory_.get(item).getMag() > 0) && !inventory_.get(item).getAttack().equals(0) && equipment_.isEmpty()) {
            temp = inventory_.get(item);

            inventory_.remove(item);
            equipment_.put(temp.getName(), temp);
            equipname_ = item;
            setAttack(getAttack() + equipment_.get(item).getAttack());
            System.out.println("Attack damage of the player increased to " + getAttack());
            System.out.println(item + " was successfully equipped");
            System.out.println("Type look <item name> to retrieve the description of the equipment and attack value" + '\n');
        } else if (inventory_.containsKey(item) && (inventory_.get(item).getMag() == 0) && !inventory_.get(item).getAttack().equals(0) && equipment_.isEmpty()) {
            temp = inventory_.get(item);

            inventory_.remove(item);
            equipment_.put(temp.getName(), temp);
            equipname_ = item;
            System.out.println("Attack damage of the player increased to " + getAttack());
            System.out.println(item + " was successfully equipped");
            System.out.println("Type look <item name> to retrieve the description of the equipment and attack value" + '\n');
        } else {
            System.out.println("Sorry, " + item + " is unable to be equipped" + '\n');
        }
    }

    public void unequip() {
        Item temp = null;

        if (equipment_.containsKey(equipname_)) {
            temp = equipment_.get(equipname_);
            equipment_.remove(equipname_);

            getInventory().put(temp.getName(), temp);
            setAttack(baseatt_);
            System.out.println("Attack damage of the player decreased to " + getAttack());
            System.out.println(equipname_ + " was successfully unequipped");
            System.out.println('\n');
        } else {
            System.out.println("Sorry, " + equipname_ + " is not in your equipment." + '\n');
        }
    }

    public void unlock(HashMap<String, Room> rooms) {
        Room current = rooms.get(location_);

        String[] temp = current.getNeighbors();

        for (int i = 0; i < temp.length; i++) {
            if (temp[i].contains("/")) {
                String[] key = temp[i].split("/");
                setKeyname(key[1]);

            }
        }

        if (inventory_.containsKey(getKeyname())) {
            if (current.getNeighbors()[0].contains("/")) {
                int end = current.getNeighbors()[0].indexOf("/");
                String[] neighbors = {current.getNeighbors()[0].substring(0,end), current.getNeighbors()[1], current.getNeighbors()[2], current.getNeighbors()[3]};
                current.setNeighbors_(neighbors);
                System.out.println("North was unlocked.");
                journal_.remove(getKeyname());
                System.out.println(getKeyname() + " was successfully removed from journal");
                inventory_.remove(getKeyname());
            } else if (current.getNeighbors()[1].contains("/")) {
                int end = current.getNeighbors()[1].indexOf("/");
                String[] neighbors = {current.getNeighbors()[0], current.getNeighbors()[1].substring(0,end), current.getNeighbors()[2], current.getNeighbors()[3]};
                current.setNeighbors_(neighbors);
                System.out.println("South was unlocked.");
                journal_.remove(getKeyname());
                System.out.println(getKeyname() + " was successfully removed from journal");
                inventory_.remove(getKeyname());
            } else if (current.getNeighbors()[2].contains("/")) {
                int end = current.getNeighbors()[2].indexOf("/");
                String[] neighbors = {current.getNeighbors()[0], current.getNeighbors()[1], current.getNeighbors()[2].substring(0,end), current.getNeighbors()[3]};
                current.setNeighbors_(neighbors);
                System.out.println("East was unlocked.");
                journal_.remove(getKeyname());
                System.out.println(getKeyname() + " was successfully removed from journal");
                inventory_.remove(getKeyname());
            } else if (current.getNeighbors()[3].contains("/")) {
                int end = current.getNeighbors()[3].indexOf("/");
                String[] neighbors = {current.getNeighbors()[0], current.getNeighbors()[1], current.getNeighbors()[2], current.getNeighbors()[3].substring(0,end)};
                current.setNeighbors_(neighbors);
                System.out.println("West was unlocked.");
                journal_.remove(getKeyname());
                System.out.println(getKeyname() + " was successfully removed from journal");
                inventory_.remove(getKeyname());
            } else {
                System.out.println("Sorry, none of the doors are locked, try again!");
            }


        } else {
            System.out.println("Sorry, the unlock command is unavailable, try again!");

        }
    }

    public void move(String direction, HashMap<String, Room> rooms) {
        direction = direction.toLowerCase();
        Room current = rooms.get(location_);

        String[] temp = current.getNeighbors();

        if (direction.equals("north") || direction.equals("n") || direction.contains("no")) {
            if (!temp[0].equals("-")) {
                if (temp[0].contains("/")) {
                    System.out.println("Sorry, this way is locked, try again!" + '\n');
                } else {
                    location_ = temp[0];
                }
            } else {
                System.out.println("Sorry, cannot go this way, try again!" + '\n');
            }
        } else if (direction.equals("south") || direction.equals("s") || direction.contains("so")) {
            if (!temp[1].equals("-")) {
                if (temp[1].contains("/")) {
                    System.out.println("Sorry, this way is locked, try again!" + '\n');
                } else {
                    location_ = temp[1];
                }
            } else {
                System.out.println("Sorry, cannot go this way, try again!" + '\n');
            }
        } else if (direction.equals("east") || direction.equals("e") || direction.contains("e")) {
            if (!temp[2].equals("-")) {
                if (temp[2].contains("/")) {
                    System.out.println("Sorry, this way is locked, try again!" + '\n');
                } else {
                    location_ = temp[2];
                }
            } else {
                System.out.println("Sorry, cannot go this way, try again!" + '\n');
            }
        } else if (direction.equals("west") || direction.equals("w") || direction.contains("wes")) {
            if (!temp[3].equals("-")) {
                if (temp[3].contains("/")) {
                    System.out.println("Sorry, this way is locked, try again!" + '\n');
                } else {
                    location_ = temp[3];
                }
            } else {
                System.out.println("Sorry, cannot go this way, try again!" + '\n');
            }
        } else {
            System.out.println("Sorry, not valid direction, try again!" + '\n');
        }

    }

    public void add(String item, HashMap<String, Room> rooms) {

        Room current = rooms.get(location_);

        Item temp = null;

        if (current.getInventory().containsKey(item)) {
            temp = current.getInventory().get(item);
            if (temp.getRoomloot().equals("1")) {
                if (rooms.get(location_).getPuzzle().get(location_).getName().equals(item) && rooms.get(location_).getPuzzle().get(location_).getPuzzle().equals("0")) {
                    journal_.put(item, rooms.get(location_).getPuzzle().get(location_));
                    System.out.println(item + " was successfully added to journal");
                    current.getInventory().remove(item);
                    inventory_.put(temp.getName(), temp);
                    System.out.println(item + " was successfully added inventory");
                    System.out.println("Type inspect <item name> to retrieve the description of the item");
                } else {
                    current.getInventory().remove(item);
                    inventory_.put(temp.getName(), temp);
                    System.out.println(item + " was successfully added");
                    System.out.println("Type inspect <item name> to retrieve the description of the item");
                }
            } else {
                System.out.println("Sorry, " + item + " is not in the room." + '\n');
            }
        } else {
            System.out.println("Sorry, " + item + " is not in the room." + '\n');
        }
    }

    public void addall(HashMap<String, Room> rooms) {

        Room current = rooms.get(location_);

        Item temp = null;

        if (!current.getInventory().isEmpty()) {
            inventory_.putAll(current.getInventory());
            System.out.println("All the items was successfully added inventory");
            System.out.println("Type inspect <item name> to retrieve the description of the item");
        } else {
            System.out.println("There is no items in the room");
        }
    }

    public void drop(String item, HashMap<String, Room> rooms) {
        Item temp = null;

        if (inventory_.containsKey(item)) {
            temp = inventory_.get(item);
            if (temp.getType().equals("Key")) {
                journal_.remove(item);
                System.out.println(item + " was successfully removed from journal");
                inventory_.remove(item);

                Room current = rooms.get(location_);

                current.getInventory().put(temp.getName(), temp);
                System.out.println(item + " was successfully dropped from inventory");
            } else {
                inventory_.remove(item);

                Room current = rooms.get(location_);

                current.getInventory().put(temp.getName(), temp);
                System.out.println(item + " was successfully dropped");
            }
        } else {
            System.out.println("Sorry, " + item + " is not in your inventory." + '\n');
        }
    }

    public Map<String, Item> getInventory() {
        return inventory_;
    }

    public Map<String, Item> getEquipment() {
        return equipment_;
    }

    public Boolean containInventory(String itemName) { return inventory_.containsKey(itemName); }

    public void getBackpack() {
        if (equipment_.isEmpty()) {
            System.out.println("Equipment is empty" + '\n');
        } else {
            System.out.println("Equipment:");
            for (Map.Entry<String, Item> elt : equipment_.entrySet()) {
                System.out.println(elt.getKey() + " " + elt.getValue().getMag());
            }
            System.out.println();
        }

        if (inventory_.isEmpty()) {
            System.out.println("Inventory is empty");
        } else {
            System.out.println("Inventory:");
            for (Map.Entry<String, Item> elt : inventory_.entrySet()) {
                System.out.println(elt.getKey() + " " +elt.getValue().getQuantity());
            }
        }
    }

    public void getJournal() {
        if (journal_.isEmpty()) {
            System.out.println("Journal is empty" + '\n');
        } else {
            System.out.println("Journal:");
            for (Map.Entry<String, Puzzle> elt : journal_.entrySet()) {
                System.out.println(elt.getKey());
                System.out.println(elt.getValue().getDescription());
            }
        }
    }

    public void look(String item) {
        if (inventory_.containsKey(item)) {
            System.out.println(inventory_.get(item).getDescription() + '\n');
        } else {
            System.out.println("Item not found/doesn't exist, please try again!" + '\n');
        }

    }

    public void looke(String item) {
        if (equipment_.containsKey(item)) {
            System.out.println(equipment_.get(item).getDescription());
            System.out.println("The attack damage of the weapon is " + equipment_.get(item).getAttack() + '\n');
        } else {
            System.out.println("Item not found/doesn't exist, please try again!" + '\n');
        }

    }

    public void look(HashMap<String, Room> rooms) {
        rooms.get(location_).look();
        System.out.println("Type get <item name> to add items to backpack or type drop <item name> to remove items from backpack" + '\n');
    }


}
