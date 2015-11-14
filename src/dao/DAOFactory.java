package dao;

import dao.interfaceDAO.AccessoryDAO;
import dao.interfaceDAO.ActivityDAO;
import model.Accessory;
import model.Activity;

public abstract class DAOFactory {

	private static DAOFactory daoFactory;

    public DAOFactory() {
    }
    
    public static DAOFactory getInstance(){
    	if(DAOFactory.daoFactory == null){
    		DAOFactorySQL daoFactorysql = new DAOFactorySQL();
    		DAOFactory.daoFactory = daoFactorysql;
    	}
    	return DAOFactory.daoFactory;
    }
    
    public abstract AccessoryDAO getAccessoryDAO();

    public abstract ActivityDAO getActivityDAO();
    
}
