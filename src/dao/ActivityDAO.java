package dao;

import java.util.List;
import model.Activity;

public interface ActivityDAO {

	public void create(Activity activity);

	public List<Activity> findAll();
	
	public Activity findById( int id);

	public void update(Activity activity);

	public void delete(int id);
}

