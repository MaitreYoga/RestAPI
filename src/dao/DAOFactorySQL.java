package dao;

import dao.interfaceDAO.AccessoryDAO;
import dao.interfaceDAO.ActivityDAO;
import dao.sql.AccessoryDAOSQL;
import dao.sql.ActivityDAOSQL;
import model.Accessory;
import model.Activity;

public class DAOFactorySQL extends DAOFactory{
    
    public DAOFactorySQL() {
        super(); 
    }
    
    public AccessoryDAO getAccessoryDAO(){
    	return  (AccessoryDAO) new AccessoryDAOSQL();
    }
    
    public ActivityDAO getActivityDAO(){
    	return (ActivityDAO) new ActivityDAOSQL();
    }
   }
