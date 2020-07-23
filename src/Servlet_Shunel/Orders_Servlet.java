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
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
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
//	Oder_Main_DAO_Interface orderMainDaoImpliment = null;
//	Order_Detail_DAO_Interface orderDetailDaoImpliment = null;
//	Product_DAO_Interface productDaoImpliment;
	
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
			order_Main_DAO = new Oder_Main_DAO_Interface();
				
		}
		if (order_Detail_DAO == null) {
			order_Detail_DAO = new Order_Detail_DAO_Interface();
		}
//		if (productDaoImpliment == null) {
//			productDaoImpliment = new Product_DAO_Interface();
//		}
//		if (orderMainDaoImpliment== null) {
//			orderMainDaoImpliment = new Oder_Main_DAO_Interface();
//				
//		}
//		if (orderDetailDaoImpliment == null) {
//			orderDetailDaoImpliment = new Order_Detail_DAO_Interface();
//		}
		
		List<Order_Main> order_Mains = order_Main_DAO.getAll();
		List<Order_Detail> order_Details = order_Detail_DAO.getAll();

		writeText(response, new Gson().toJson(order_Mains));
		writeText(response, new Gson().toJson(order_Details));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		BufferedReader br = request.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = "";
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}

		System.out.println("Input:" + jsonIn);
		
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
//		if (product_DAO == null) {
//			product_DAO = new Product_DAO_Interface();
//		}
		if (order_Main_DAO== null) {
			order_Main_DAO = new Oder_Main_DAO_Interface();//? 
		}
		if (order_Detail_DAO==null) {
			order_Detail_DAO = new Order_Detail_DAO_Interface();
		}
		if (product_DAO == null) {
			product_DAO = new Product_DAO_Interface();
		}
		if (order_Main_DAO== null) {
			order_Main_DAO = new Oder_Main_DAO_Interface();//? 
		}
		if (order_Detail_DAO==null) {
			order_Detail_DAO = new Order_Detail_DAO_Interface();
		}
//		if (productDaoImpliment == null) {
//			productDaoImpliment = new Product_DAO_Interface();
//		}
//		if (orderMainDaoImpliment== null) {
//			orderMainDaoImpliment = new Oder_Main_DAO_Interface();//? 
//		}
//		if (orderDetailDaoImpliment==null) {
//			orderDetailDaoImpliment = new Order_Detail_DAO_Interface();
//		}

		String action = jsonObject.get("action").getAsString();
		
		if (action.equals("getAll")) {
			System.out.println("OrdersServlet");
			List<Order_Main> orderMains = order_Main_DAO.getAll();
			List<Order_Detail> orderDetail = order_Detail_DAO.getAll();
			writeText(response, gson.toJson(orderMains));
			writeText(response, gson.toJson(orderDetail));
			return;
		}
//		select by activity
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
		//結帳後修改狀態0->1		
		case "changeOrderStatus":{
			int count = 0;
			int orderMainID = jsonObject.get("orderID").getAsInt();
			count = order_Main_DAO.updataOrder(orderMainID);
			writeText(response, String.valueOf(count));
			break;
		}
		
//		get shortOrderList for orderListFragment
		case "getOrderMainShort": {
			String user_id = jsonObject.get("id").getAsString();
			List<Order_Main> orderShortMainMainList = order_Main_DAO.getShortOrderMains(user_id);
			writeText(response, gson.toJson(orderShortMainMainList));
			break;
		}
		
//		select by status
		case "status":{
			int status = jsonObject.get("status").getAsInt();
			if (status == 0) {
				int order_Mains = order_Main_DAO.getStatus(0);
//				List<Order_Main> order_Mains = order_Main_DAO.getStatus(0);
				writeText(response, gson.toJson(order_Mains));
			}else if(status == 1){
				int order_Mains = order_Main_DAO.getStatus(1);
//				List<Order_Main> order_Mains = order_Main_DAO.getStatus(1);
				writeText(response, gson.toJson(order_Mains));
			}else if(status == 2){
				int order_Mains = order_Main_DAO.getStatus(2);
//				List<Order_Main> order_Mains = order_Main_DAO.getStatus(2);
				writeText(response, gson.toJson(order_Mains));
			}else if(status == 3){
				int order_Mains = order_Main_DAO.getStatus(3);
//				List<Order_Main> order_Mains = order_Main_DAO.getStatus(3);
				writeText(response, gson.toJson(order_Mains));
			}else if(status == 4){
				int order_Mains = order_Main_DAO.getStatus(4);
//				List<Order_Main> order_Mains = order_Main_DAO.getStatus(4);
				writeText(response, gson.toJson(order_Mains));
			}
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + action);
		}
	}
	
	private void writeText(HttpServletResponse response, String outText) throws IOException {
		// TODO Auto-generated method stub
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		out.print(outText);
		// 將輸出資料列印出來除錯用
		System.out.println("output: " + outText);
	}
}
