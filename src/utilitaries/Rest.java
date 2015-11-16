package utilitaries;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bl.facade.UserFacade;
import dal.product.generic.User;

public class Rest {
    public static void AddObjectAsJSONResponse(Object o, HttpServletResponse response)
    {
		response.setContentType("application/json");
		PrintWriter out = null;
		try
		{
			out = response.getWriter();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String json = gson.toJson(o);
		out.print(json);
		out.flush();
    }
    public static HashMap<String,String> GetParameters(HttpServletRequest request)
    {
    	HashMap<String,String> map = new HashMap<String,String>(); 
    	Enumeration<String> parameterNames = request.getParameterNames();
    	String paramName;
		while (parameterNames.hasMoreElements()) {
			paramName = parameterNames.nextElement().toLowerCase();
			String[] paramValues = request.getParameterValues(paramName);
			map.put(paramName,paramValues[0]);
			//System.out.println(paramName+" -- "+paramValues[0]);
		}
		return map;
    }
    
    public static boolean verifyToken(HttpServletRequest request) {
		String token=request.getHeader("token");
		String login = request.getHeader("login");
    	User u = UserFacade.instance().getUser(0, login);

		System.out.println(token);
		System.out.println(u.getConnectionToken());
    	if (token!= null && u.getConnectionToken().equals(token)) {
    		System.out.println("ok");
    		return true;
    	} else {
    		System.out.println("ko");
    		return false;
    	}
    }
}