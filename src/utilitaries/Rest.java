package utilitaries;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

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
    public static HashMap<String,String> GetHeaders(HttpServletRequest request)
    {
    	HashMap<String,String> map = new HashMap<String,String>(); 
    	Enumeration<String> headerNames = request.getHeaderNames();
    	String headerName, headerValue;
		while (headerNames.hasMoreElements()) {
			headerName = headerNames.nextElement().toLowerCase();
			headerValue = request.getHeader(headerName);
			map.put(headerName,headerValue);
		}
		return map;
    }
}