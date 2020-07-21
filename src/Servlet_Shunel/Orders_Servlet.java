package Servlet_Shunel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import DAO.Order_Detail_DAO;
import DAO.Order_Main_DAO;
import DAO_Interface.Oder_Main_DAO_Interface;
import DAO_Interface.Order_Detail_DAO_Interface;
import DAO_Interface.Product_DAO_Interface;
import DAO_Interface.Shopping_Card_DAO_Interdace;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

import Bean.Order_Detail;
import Bean.Order_Main;
import DAO.Order_Detail_DAO;
import DAO.Order_Main_DAO;
import DAO.Product_DAO;
import DAO_Interface.Oder_Main_DAO_Interface;
import DAO_Interface.Order_Detail_DAO_Interface;
import DAO_Interface.Product_DAO_Interface;

///**
// * Servlet implementation class Orders_Servlet
// */
@WebServlet("/Orders_Servlet")
public class Orders_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String TAG = "TAG_Orders_Servlet";
	private final static String CONTENT_TYPE = "text/html; charset=utf-8";
	Order_Main_DAO order_Main_DAO = null;
	Order_Detail_DAO order_Detail_DAO = null;
	Product_DAO product_DAO = null;

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
		if (product_DAO == null) {
			product_DAO = new Product_DAO_Interface();
		}
		if (order_Main_DAO== null) {
			order_Main_DAO = new Oder_Main_DAO_Interface(); //?{
				
		}
		if (order_Detail_DAO == null) {
			order_Detail_DAO = new Order_Detail_DAO_Interface();
		}
		List<Order_Main> order_Mains = order_Main_DAO.getAll();
		List<Order_Detail> order_Details = order_Detail_DAO.getAll();
		

		writeText(response, new Gson().toJson(order_Mains));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

//		doGet(request, response);

		request.setCharacterEncoding("UTF-8");
		
//		Gson gson = new Gson();
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		BufferedReader br = request.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = "";
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}

		System.out.println("Input:" + jsonIn);
		
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		if (product_DAO == null) {
			product_DAO = new Product_DAO_Interface();
		}
		if (order_Main_DAO== null) {
			order_Main_DAO = new Oder_Main_DAO_Interface();//? 
		}
		if (order_Detail_DAO==null) {
			order_Detail_DAO = new Order_Detail_DAO_Interface();
		}

		String action = jsonObject.get("action").getAsString();
		
		if (action.equals("getAll")) {
			System.out.println("111111112222");
			List<Order_Main> orderDetail = order_Main_DAO.getAll();
			writeText(response, gson.toJson(orderDetail));
			return;
		}
		
		switch (action) {
		case "getOrderMain": {
			int orderMainID = jsonObject.get("orderID").getAsInt();
			break;
		}
		case "getOrderDetail":{
			int orderDetailID = jsonObject.get("orderID").getAsInt();
			break;
		}
		case "getImage":{
			OutputStream os = response.getOutputStream();
			int id = jsonObject.get("id").getAsInt();
			int imageSize = jsonObject.get("imageSize").getAsInt();
			byte[] image = product_DAO.getImage(id);
			if (image != null) {
				image = ImageUtil.shrink(image, imageSize);
				response.setContentType("image/jpeg");
				response.setContentLength(image.length);
				os.write(image);

			}
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + action);
		}

//		int status = jsonObject.get("status").getAsInt();
//		
//		switch (status) {
//		case 0: {
//			List<Order_Main> order_Mains = order_Main_DAO.getStatus(0);
//			writeText(response, gson.toJson(order_Mains));
//			break;
//		}
//		case 1: {
//			List<Order_Main> order_Mains = order_Main_DAO.getStatus(1);
//			writeText(response, gson.toJson(order_Mains));
//			break;
//		}case 2: {
//			List<Order_Main> order_Mains = order_Main_DAO.getStatus(2);
//			writeText(response, gson.toJson(order_Mains));
//			break;
//		}case 3: {
//			List<Order_Main> order_Mains = order_Main_DAO.getStatus(3);
//			writeText(response, gson.toJson(order_Mains));
//			break;
//		}case 4: {
//			List<Order_Main> order_Mains = order_Main_DAO.getStatus(4);
//			writeText(response, gson.toJson(order_Mains));
//			break;
//		}
//		default:
//			throw new IllegalArgumentException("Unexpected value: " + status);
//		}
	}
	
//	need to add here: insert image
	

	private void writeText(HttpServletResponse response, String outText) throws IOException {
		// TODO Auto-generated method stub
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		out.print(outText);
		// 將輸出資料列印出來除錯用
		System.out.println("output: " + outText);
	}

}
