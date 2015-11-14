package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the accessory database table.
 * 
 */
@Entity
@NamedQuery(name="Accessory.findAll", query="SELECT a FROM Accessory a")
public class Accessory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String description;

	//bi-directional many-to-one association to Accessoryavailability
	@OneToMany(mappedBy="accessory")
	private List<Accessoryavailability> accessoryavailabilities;

	public Accessory() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Accessoryavailability> getAccessoryavailabilities() {
		return this.accessoryavailabilities;
	}

	public void setAccessoryavailabilities(List<Accessoryavailability> accessoryavailabilities) {
		this.accessoryavailabilities = accessoryavailabilities;
	}

	public Accessoryavailability addAccessoryavailability(Accessoryavailability accessoryavailability) {
		getAccessoryavailabilities().add(accessoryavailability);
		accessoryavailability.setAccessory(this);

		return accessoryavailability;
	}

	public Accessoryavailability removeAccessoryavailability(Accessoryavailability accessoryavailability) {
		getAccessoryavailabilities().remove(accessoryavailability);
		accessoryavailability.setAccessory(null);

		return accessoryavailability;
	}

}