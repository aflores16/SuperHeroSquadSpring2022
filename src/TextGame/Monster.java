package TextGame;

public class Monster {
    private String monsterName_;
	private String roomName_;
    private int health_;
    private int lowAttack_ ;
	private int highAttack_;
    private String location_;
    private String description_;
    private String itemID_;
    private double chanceOfFood;
    private String foodName_;
    private double chanceOfKey_;
    private String keyName_;

    public Monster(int id, String location, String name, int hp,int lowAttk, int highAttk, keyChance,
	keyName,foodChance,foodName,description){
		itemID_ = id;
		location_=location;
		monsterName_ = name;
		health_ = health;
		lowAttack_ = lowAttk;
		highAttack_ = highAttk;
		chanceOfKey_ = keyChance;
		keyName_ =  keyName;
		chanceOfFood = foodChance;
		foodName_ = foodName;
		description_ =description;
    }

    public String getMonsterName_() {
        return name_;
    }

    public int getHealth_() {
        return health_;
    }

    public int getLowAttack_() {
        return lowAttack_;
    }
	
	public int getHighAttack_() {
        return highAttack_;
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

    public double getChanceOfFood_() {
        return chanceOfFood_;
    }
	
	 public String getKeyName_() {
        return keyName_;
    }

 
    public double getFoodName_() {
        return foodName__;
    }

    public String getKeyName_() {
        return keyName_;
    }
}
