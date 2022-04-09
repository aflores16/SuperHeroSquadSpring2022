package TextGame;

public class Monster {
    private String name_;
    private int health_;
    private int attack_ ;
    private String location_;
    private String description_;
    private String itemID_;
    private double chance_;
    private String item_;
    private double chanceOfKey_;
    private String keyName_;

    public Monster( String name, int health, int attack, String location, String description, String itemID,
            double chance, String item, double chanceOfKey, String keyName ){
        name_ = name;
        health_ = health;
        attack_ = attack;
        location_ =location;
        description_ = description;
        itemID_ = itemID;
        chance_ = chance;
        item_ = item;
        chanceOfKey_ = chanceOfKey;
        keyName_ = keyName;
    }

    public String getName_() {
        return name_;
    }

    public int getHealth_() {
        return health_;
    }

    public int getAttack_() {
        return attack_;
    }

    public String getLocation_() {
        return location_;
    }

    public String getDescription_() {
        return description_;
    }

    public String getItemID_() {
        return itemID_;
    }

    public double getChance_() {
        return chance_;
    }

    public String getItem_() {
        return item_;
    }

    public double getChanceOfKey_() {
        return chanceOfKey_;
    }

    public String getKeyName_() {
        return keyName_;
    }
}
