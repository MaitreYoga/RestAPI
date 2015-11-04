package utilitaries;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class Json {
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
}