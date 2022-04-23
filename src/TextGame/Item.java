package TextGame;


public class Item {
    private String name_;
    private String id_;
    private String description_;
    private String location_;
    private String type_;
    private Integer roomloot_;
    private Integer mobloot_;
    private Integer shoploot_;
    private Integer quantity_;
    private Integer attack_;
    private Integer mag_;
    private Integer maxmag_;
    private Double value_;
    private Double spawnrate_;

    public Item(String name, String id, String description, String location, String type, Integer roomloot, Integer mobloot,
                Integer shoploot, Integer quantity, Integer attack, Integer mag, Double value, Double spawnrate) {
        name_ = name;
        id_ = id;
        description_ = description;
        location_ = location;
        type_ = type;
        roomloot_ = roomloot;
        mobloot_ = mobloot;
        shoploot_ = shoploot;
        quantity_ = quantity;
        attack_ = attack;
        mag_ = mag;
        maxmag_ = mag;
        value_ = value;
        spawnrate_ = spawnrate;
    }

    public void look() {
        System.out.println(description_);
    }

    public String getName() {
        return name_;
    }

    public String getId() {
        return id_;
    }

    public String getDescription() {
        return description_;
    }

    public String getLocation() {
        return location_;
    }

    public String getType() {
        return type_;
    }

    public Integer getRoomloot() {
        return roomloot_;
    }

    public Integer getMobloot() {
        return mobloot_;
    }

    public Integer getShoploot() {
        return shoploot_;
    }

    public Integer getQuantity() {
        return quantity_;
    }

    public Integer getAttack() {
        return attack_;
    }

    public Integer getMag() {
        return mag_;
    }

    public Integer getMaxmag() {
        return maxmag_;
    }

    public Double getValue() {
        return value_;
    }

    public Double getSpawnrate() {
        return spawnrate_;
    }

    public void setRoomloot(Integer roomloot_) {
        this.roomloot_ = roomloot_;
    }

    public void setMobloot(Integer mobloot_) {
        this.mobloot_ = mobloot_;
    }

    public void setShoploot(Integer shoploot_) {
        this.shoploot_ = shoploot_;
    }

    public void setQuantity(Integer quantity_) {
        this.quantity_ = quantity_;
    }

    public void setMag(Integer mag_) {
        this.mag_ = mag_;
    }
    
    public void inspect() {
    	System.out.println(this.description_);
    }
}
