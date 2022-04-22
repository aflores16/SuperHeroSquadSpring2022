package TextGame;

import java.util.*;

public class Player {

    private static String location_;
    private static String equipname_;
    private static Map<String, Item> inventory_ = new HashMap<String, Item>();
    private static Map<String, Item> equipment_ = new HashMap<String, Item>();
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
        location_ = "1";
        maxhealth_ = health;
        baseatt_ = attack;
        armor_ = 0;
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
        String ammo = "ammo";
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
        String armor = "armor";

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
            System.out.println("Type look <item name> to retrieve the description of the equipment and attack value");
            System.out.println('\n');
        } else if (inventory_.containsKey(item) && (inventory_.get(item).getMag() == 0) && !inventory_.get(item).getAttack().equals(0) && equipment_.isEmpty()) {
            temp = inventory_.get(item);

            inventory_.remove(item);
            equipment_.put(temp.getName(), temp);
            equipname_ = item;
            System.out.println("Attack damage of the player increased to " + getAttack());
            System.out.println(item + " was successfully equipped");
            System.out.println("Type look <item name> to retrieve the description of the equipment and attack value");
            System.out.println('\n');
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

    public void move(String direction, HashMap<String, Room> rooms) {
        direction = direction.toLowerCase();
        Room current = rooms.get(location_);

        String[] temp = current.getNeighbors();

        if (direction.equals("north") || direction.equals("n")) {
            if (!temp[0].equals("-")) {
                location_ = temp[0];
            } else {
                System.out.println("Sorry, cannot go this way, try again!" + '\n');
            }
        } else if (direction.equals("south") || direction.equals("s")) {
            if (!temp[1].equals("-")) {
                location_ = temp[1];
            } else {
                System.out.println("Sorry, cannot go this way, try again!" + '\n');
            }
        } else if (direction.equals("east") || direction.equals("e")) {
            if (!temp[2].equals("-")) {
                location_ = temp[2];
            } else {
                System.out.println("Sorry, cannot go this way, try again!" + '\n');
            }
        } else if (direction.equals("west") || direction.equals("w")) {
            if (!temp[3].equals("-")) {
                location_ = temp[3];
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

            current.getInventory().remove(item);
            inventory_.put(temp.getName(), temp);
            System.out.println(item + " was successfully added");
            System.out.println("Type inspect <item name> to retrieve the description of the item");
        } else {
            System.out.println("Sorry, " + item + " is not in the room." + '\n');
        }
    }

    public void drop(String item, HashMap<String, Room> rooms) {
        Item temp = null;

        if (inventory_.containsKey(item)) {
            temp = inventory_.get(item);
            inventory_.remove(item);

            Room current = rooms.get(location_);

            current.getInventory().put(temp.getName(), temp);
            System.out.println(item + " was successfully dropped");
        } else {
            System.out.println("Sorry, " + item + " is not in your inventory." + '\n');
        }
    }

    public String getLocation() {
        return location_;
    }

    public String getEquipname() {
        return equipname_;
    }

    public String getInventory_() {
        return inventory_.get(getInventory_()).getId();
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
