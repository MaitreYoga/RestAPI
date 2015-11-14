package dao.interfaceDAO;

import java.util.List;
import model.User;

public interface UserDAO {

	public void create(User user);

	public List<User> findAll();
	
	public User findById( int id);

	public void update(User user);

	public void delete(int id);
}

