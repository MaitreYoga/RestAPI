package persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLDatabase
{	
	////Connection informations////
	private final String url = "jdbc:mysql://zenlounge.c5v651hpjsww.us-west-2.rds.amazonaws.com:3306/ZenLounge";
	private final String login = "MaitreYoga";
    private final String pwd = "maythe4th";
    
    public static final int ErrorCode = -1;
    
    private Connection connexion = null;
    
    ////Singleton pattern////
    private static MySQLDatabase db;
        
    private MySQLDatabase(){
    	
    }
    
    public static MySQLDatabase getInstance(){
    	if(db == null)
    		db = new MySQLDatabase();
    	return db;
    }
    
    ////Standard database methods////
    public boolean open() {
    	/* Si la connexion n'existe pas, on l'ouvre */
    	if (connexion == null) {
	    	/* Chargement du driver */
			try {
			    Class.forName( "com.mysql.jdbc.Driver" );
			} catch ( ClassNotFoundException e ) {
			    /* Gérer les éventuelles erreurs ici. */
			}
			
			/* Ouverture de la connexion */
	    	try {
			    connexion = DriverManager.getConnection( url, login, pwd );
			    return true;
			} catch ( SQLException e ) {
			    System.err.println(e.getMessage());
			    return false;
			}
    	}
    	else
    		return true;
    }
    public void close() {
	    if (connexion != null)
	        try {
	            /* Fermeture de la connexion */
	            connexion.close();
	        } catch ( SQLException ignore ) {
	            /* Si une erreur survient lors de la fermeture, il suffit de l'ignorer. */
	        }
    }
    public ResultSet selectRequest(String request) {
    	open();
    	
    	/* Exécution d'une requête de lecture */
    	ResultSet resultat = null;
    	Statement statement = null;
		
    	try {
			statement = connexion.createStatement();
			resultat = statement.executeQuery(request);
		} catch (SQLException e1) {
			System.err.println(request);
			e1.printStackTrace();
		}
    	
    	return resultat;
    }
    public int insertRequest(String request) {
    	
    	int status = 0;
    	
    	/* Si la connexion n'existe pas, on l'ouvre */
    	if (connexion == null)
    		open();
    	
    	/* Exécution d'une requête d'écriture */
		
    	try {
    		PreparedStatement ps = connexion.prepareStatement(request, java.sql.Statement.RETURN_GENERATED_KEYS);
    		ps.executeUpdate();
    		ResultSet rs = ps.getGeneratedKeys();
    		if (rs != null && rs.next()) {
    		    return rs.getInt(1);
    		}
		} catch (SQLException e1) {
			System.err.println(request);
			e1.printStackTrace();
			return ErrorCode;
		}
    	return status;
    }
    
    public int deleteRequest(String request) {
    	
    	int status = 0;
    	
    	/* Si la connexion n'existe pas, on l'ouvre */
    	if (connexion == null)
    		open();
    	
    	/* Exécution d'une requête d'écriture */
    	Statement statement = null;
		
    	try {
			statement = connexion.createStatement();
			status = statement.executeUpdate(request);
		} catch (SQLException e1) {
			System.err.println(request);
			e1.printStackTrace();
			status = ErrorCode;
		}
    	return status;
    }

	public int updateRequest(String request) {
    	
    	int status = 0;
    	
    	/* Si la connexion n'existe pas, on l'ouvre */
    	if (connexion == null)
    		open();
    	
    	/* Exécution d'une requête d'écriture */
    	Statement statement = null;
		
    	try {
			statement = connexion.createStatement();
			status = statement.executeUpdate(request);
		} catch (SQLException e1) {
			System.err.println(request);
			e1.printStackTrace();
			status = ErrorCode;
		}
    	return status;
	}
}