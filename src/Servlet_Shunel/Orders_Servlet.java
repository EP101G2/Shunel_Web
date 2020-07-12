package Servlet_Shunel;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import DAO.Order_Detail_DAO;
import DAO.Order_Main_DAO;
import DAO_Interface.Oder_Main_DAO_Interface;
import DAO_Interface.Order_Detail_DAO_Interface;
import DAO_Interface.Product_DAO_Interface;
import DAO_Interface.Shopping_Card_DAO_Interdace;

/**
 * Servlet implementation class Orders_Servlet
 */
@WebServlet("/Orders_Servlet")
public class Orders_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String CONTENT_TYPE = "text/html; charset=utf-8";
	Order_Main_DAO order_Main = null;
	Order_Detail_DAO order_Detail_DAO = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Orders_Servlet() {
        super();
        // TODO Auto-generated constructor stub
		
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		BufferedReader br = request.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = "";
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		
		System.out.println("Input:" + jsonIn);
		
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
	
		if (order_Detail_DAO == null) {
			order_Detail_DAO = new Order_Detail_DAO_Interface();
		}
		
		
		if (order_Main == null) {
			order_Main = new Oder_Main_DAO_Interface();
		}

		//
		String action = jsonObject.get("action").getAsString();
		
		
		switch (action) {
		case "getOrderMain": {
			
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + action);
		}
	}

}
