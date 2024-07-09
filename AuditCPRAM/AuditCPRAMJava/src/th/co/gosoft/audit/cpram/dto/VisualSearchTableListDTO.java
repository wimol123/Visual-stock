package th.co.gosoft.audit.cpram.dto;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class VisualSearchTableListDTO {
	/* DB Field */
	private String Po;
	private String Item;
	private String Material;
	private String Description;
	private String Plant;
	private String Stock;
	private String Unit;
	private String DeliveryDate;
	private String Total;
	 
	//private Map<String, String> dynamicProperties = new HashMap<>();

   // public void setDynamicProperty(String key, String value) { this.dynamicProperties.put(key, value); }
   // public String getDynamicProperty(String key) { return this.dynamicProperties.get(key); }
   // public Map<String, String> getDynamicProperties() { return dynamicProperties; }

  //  private Map<String, String> dynamicProperties = new HashMap<>();

    // Getters and Setters for the above fields

  //  public void setDynamicProperty(String key, String value) {
  //      this.dynamicProperties.put(key, value);
  //  }

  //  public String getDynamicProperty(String key) {
  //      return this.dynamicProperties.get(key);
  //  }

    private Map<String, String> dynamicProperties = new TreeMap<>();

    public void setDynamicProperty(String key, String value) {
        this.dynamicProperties.put(key, value);
    }

    public String getDynamicProperty(String key) {
        return this.dynamicProperties.get(key);
    }

    public Map<String, String> getDynamicProperties() {
        return dynamicProperties;
    }
    
	public String getPo() {
		return Po;
	}

	public void setPo(String Po) {
		this.Po = Po;
	}
	
	public String getItem() {
		return Item;
	}

	public void setItem(String Item) {
		this.Item = Item;
	}
	
	public String getMaterial() {
		return Material;
	}

	public void setMaterial(String Material) {
		this.Material = Material;
	}
	
	public String getDescription() {
		return Description;
	}

	public void setDescription(String Description) {
		this.Description = Description;
	}
	
	public String getPlant() {
		return Plant;
	}

	public void setPlant(String Plant) {
		this.Plant = Plant;
	}

	public String getStock() {
		return Stock;
	}

	public void setStock(String Stock) {
		this.Stock = Stock;
	}

	public String getUnit() {
		return Unit;
	}

	public void setUnit(String Unit) {
		this.Unit = Unit;
	}

	public String getDeliveryDate() {
		return DeliveryDate;
	}

	public void setDeliveryDate(String DeliveryDate) {
		this.DeliveryDate = DeliveryDate;
	}

	public String getTotal() {
		return Total;
	}

	public void setTotal(String Total) {
		this.Total = Total;
	}
}
