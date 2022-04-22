package TextGame;


public class Monster {
    private String name_;
    private String id_;
    private String[] items_;
    private String description_;
    private String location_;
    private Integer health_;
    private Integer attack_;
    private Integer lowattack_;
    private Integer highattack_;
    private Double spawnrate_;


    public Monster(String name, String id, String[] items, String description, String location, Integer health, Integer attack, Integer lowattack, Integer highattack, Double spawnrate) {
        name_ = name;
        id_ = id;
        items_ = items;
        description_ = description;
        location_ = location;
        health_ = health;
        attack_ = attack;
        lowattack_ = lowattack;
        highattack_ = highattack;
        spawnrate_ = spawnrate;
    }


    public void look() {
        System.out.println(description_);
    }


    public String getName () {
        return name_;
    }

    public String getId () {
        return id_;
    }

    public String[] getItems () {
        return items_;
    }


    public String getLocation () {
        return location_;
    }


    public String getDescription () {
        return description_;
    }

    public Integer getHealth () {
        return health_;
    }

    public Integer getAttack () {
        return attack_;
    }

    public Integer getLowattack () {
        return lowattack_;
    }

    public Integer getHighattack () {
        return highattack_;
    }

    public Double getSpawnrate () {
        return spawnrate_;
    }

    public void setHealth(Integer health_) {
        this.health_ = health_;
    }

    public void setAttack(Integer attack_) {
        this.attack_ = attack_;
    }


}
