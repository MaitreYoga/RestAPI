package services;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bl.facade.UserFacade;
import utilitaries.Message;
import utilitaries.Rest;

/**
 * Servlet implementation class HelloWorld
 */
@WebServlet("/users")
public class Users extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Users() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String,String> headers = Rest.GetHeaders(request);
		
		if (UserFacade.instance().verifyToken(headers.get("login"),headers.get("token"))) {
			HashMap<String,String> parameters = Rest.GetParameters(request);
			if(parameters.get("id")!=null)
				Rest.AddObjectAsJSONResponse(UserFacade.instance().getUser(Integer.parseInt(parameters.get("id")),""),response);
			else if(parameters.get("login")!=null)
				Rest.AddObjectAsJSONResponse(UserFacade.instance().getUser(0,parameters.get("login")),response);
			else
				Rest.AddObjectAsJSONResponse(UserFacade.instance().getUsers(),response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String,String> map = Rest.GetParameters(request);
		String message = UserFacade.instance().handleSubscribe(
				map.get("login"),
				map.get("hashpwd"),
				map.get("firstname"),
				map.get("lastname"),
				map.get("phonenumber"),
				map.get("mailadress"),
				map.get("number"),
				map.get("name"),
				map.get("postalcode"),
				map.get("town"),
				false,
				null);
		
		if(message == null)
		{
			Rest.AddObjectAsJSONResponse(new Message("Inscription completed !",null),response);
		}
		else
		{
			Rest.AddObjectAsJSONResponse(new Message(null,message),response);
		}
	}

}
