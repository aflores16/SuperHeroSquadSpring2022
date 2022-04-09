package TextGame;

/**
 * Item		
 * 
 * @author Bouba. C
 * 
 */
public class Item {
	
	//Private data fields defining Item
	private String itemId;
	private String itemName;
	private String location;
	private String itemType;
	private String itemDescription;
	private String itemFeatures;
	private String itemUsage;
	private double itemStrength;
	
	//All args constructor
	public Item(String itemId, String itemName, String location, String itemType, String itemDescription, String itemFeatures, String itemUsage, double itemStrength) {
		this.itemId = itemId;
		this.itemName = itemName;
		this.location =location;
		this.itemType = itemType;
		this.itemDescription = itemDescription;
		this.itemFeatures = itemFeatures;
		this.itemUsage = itemUsage;
		this.itemStrength = itemStrength;	
	}

	//Getters and setters
	/**
	 * @return the itemId
	 */
	public String getItemId() {
		return itemId;
	}

	/**
	 * @param itemId 
	 * the itemId to set
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	/**
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * @param itemName 
	 * the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * @return the itemType
	 */
	public String getItemType() {
		return itemType;
	}

	/**
	 * @param itemType 
	 * the itemType to set
	 */
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	/**
	 * @return the itemFeatures
	 */
	public String getItemFeatures() {
		return itemFeatures;
	}

	/**
	 * @param itemFeatures 
	 * the itemFeatures to set
	 */
	public void setItemFeatures(String itemFeatures) {
		this.itemFeatures = itemFeatures;
	}

	/**
	 * @return the itemUsage
	 */
	public String getItemUsage() {
		return itemUsage;
	}

	/**
	 * @param itemUsage 
	 * the itemUsage to set
	 */
	public void setItemUsage(String itemUsage) {
		this.itemUsage = itemUsage;
	}

	/**
	 * @return the itemStrength
	 */
	public double getItemStrength() {
		return itemStrength;
	}

	/**
	 * @param itemStrength 
	 * the itemStrength to set
	 */
	public void setItemStrength(double itemStrength) {
		this.itemStrength = itemStrength;
	}

	public String getLocation() {
		return location;
	}
	
	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", itemName=" + itemName + ", itemType=" + itemType + ", itemFeatures="
				+ itemFeatures + ", itemUsage=" + itemUsage + ", itemStrength=" + itemStrength + "]";
	}
	
	
}
