package dao;


import java.util.List;
import model.Accessory;

public interface AccessoryDAO {

	public void create(Accessory accessory);

	public List<Accessory> findAll();
	
	public Accessory findById( int id);

	public void update(Accessory accessory);

	public void delete(int id);
}

