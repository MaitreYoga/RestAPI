package services;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bl.facade.EventFacade;
import utilitaries.Rest;

/**
 * Servlet implementation class activitycategories
 */
@WebServlet("/activitycategories")
public class activitycategories extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public activitycategories() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String,String> map = Rest.GetParameters(request);
		//if(map.get("id")!=null)
		//	Rest.AddObjectAsJSONResponse(UserFacade.instance().getUser(Integer.parseInt(map.get("id")),""),response);
		//if(map.get("id")!=null)
			Rest.AddObjectAsJSONResponse(EventFacade.instance().getAllActivityCategories(),response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String,String> map = Rest.GetParameters(request);
		EventFacade.instance().createActivityCategory(map.get("name"));
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
