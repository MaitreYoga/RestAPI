package services;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bl.facade.UserFacade;
import dal.MySQLDatabase;
import utilitaries.Rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class HelloWorld
 */
@WebServlet("/Users")
public class Users extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Users() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HashMap<String,String> map = Rest.GetParameters(request);
		if(map.get("id")!=null)
			Rest.AddObjectAsJSONResponse(UserFacade.instance().getUser(Integer.parseInt(map.get("id")),""),response);
		else if(map.get("login")!=null)
			Rest.AddObjectAsJSONResponse(UserFacade.instance().getUser(0,map.get("login")),response);
		else
			Rest.AddObjectAsJSONResponse(UserFacade.instance().getUsers(),response);
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
