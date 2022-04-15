package TextGame;

public class Monster {
    private String monsterName_;
	private String roomName_;
    private int health_;
    private int lowAttack_ ;
	private int highAttack_;
    private String location_;
    private String description_;
    private int itemID_;
    private double chanceOfFood;
    private String foodName_;
    private double chanceOfKey_;
    private String keyName_;

    public Monster(int id, String location, String name, int hp,int lowAttk, int highAttk, double keyChance,
	String keyName, double foodChance, String foodName, String description){
		itemID_ = id;
		location_=location;
		monsterName_ = name;
		health_ = hp;
		lowAttack_ = lowAttk;
		highAttack_ = highAttk;
		chanceOfKey_ = keyChance;
		keyName_ =  keyName;
		chanceOfFood = foodChance;
		foodName_ = foodName;
		description_ =description;
    }

    public String getMonsterName_() {
        return monsterName_;
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

    public int getItemID_() {
        return itemID_;
    }

    public double getChanceOfFood_() {
        return chanceOfFood;
    }
	
	 public String getKeyName() {
        return keyName_;
    }
    public String getRoomName_(){ return roomName_;}

 
    public String getFoodName_() {
        return foodName_;
    }

    public String getKeyName_() {
        return keyName_;
    }
}
