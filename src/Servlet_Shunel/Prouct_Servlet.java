package Servlet_Shunel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
//import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;
//import com.sun.tools.classfile.Opcode.Set;
import com.google.gson.reflect.TypeToken;

import Bean.Order_Detail;
import Bean.Order_Main;
import Bean.Product;
import Bean.Shopping_Cart;
import Bean.User_Account;
import DAO.Order_Detail_DAO;
import DAO.Order_Main_DAO;
import DAO.Product_DAO;
import DAO.Shopping_Card_DAO;
import DAO_Interface.Oder_Main_DAO_Interface;
import DAO_Interface.Order_Detail_DAO_Interface;
import DAO_Interface.Product_DAO_Interface;
import DAO_Interface.Shopping_Card_DAO_Interdace;



//import idv.ron.server.spots.Spot;

/**
 * Servlet implementation class Prouct_Servlet
 */
@WebServlet("/Prouct_Servlet")
public class Prouct_Servlet extends HttpServlet {
	private static final String TAG = "TAG_Prouct_Servlet";
	private static final long serialVersionUID = 1L;
	private final static String CONTENT_TYPE = "text/html; charset=utf-8";
	Product_DAO product_DAO = null;
	Order_Detail_DAO order_Detail_DAO = null;
	Shopping_Card_DAO shopping_Card_DAO = null;
	Order_Main_DAO order_Main = null;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Prouct_Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		if (product_DAO == null) {
			product_DAO = new Product_DAO_Interface();
		}

		if (shopping_Card_DAO == null) {
			shopping_Card_DAO = new Shopping_Card_DAO_Interdace() {
			};
		}

		if (order_Main == null) {
			order_Main = new Oder_Main_DAO_Interface();
			
		}
		
		
		List<Product> proucts = product_DAO.getAll();
		List<Shopping_Cart> shopping_Carts = shopping_Card_DAO.getAll();

		writeText(response, new Gson().toJson(proucts));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		if (product_DAO == null) {
			product_DAO = new Product_DAO_Interface();
		}
		if (order_Detail_DAO == null) {
			order_Detail_DAO = new Order_Detail_DAO_Interface();
		}
		if (shopping_Card_DAO == null) {
			shopping_Card_DAO = new Shopping_Card_DAO_Interdace() {	
				};
			}
		if(order_Main == null) {
			order_Main = new Oder_Main_DAO_Interface();
		}
		

		//
		String action = jsonObject.get("action").getAsString();

		switch (action) {
		
		case "getCategoryProduct": {
			int category_id = jsonObject.get("category_id").getAsInt();
			List<Product> proucts = product_DAO.getCategoryProduct(category_id);
			writeText(response, gson.toJson(proucts));
			break;
		}
		
		
		case "getSaleProduct": {
			List<Product> proucts = product_DAO.getSaleProduct();
			writeText(response, gson.toJson(proucts));
			break;
		}
		
		
		case "getAll": {
			List<Product> proucts = product_DAO.getAll();
			writeText(response, gson.toJson(proucts));
			break;
		}

//		case "getImage": {
//			OutputStream os = response.getOutputStream();
//			int id = jsonObject.get("id").getAsInt();
//			int imageSize = jsonObject.get("imageSize").getAsInt();
//			byte[] image = product_DAO.getImage(id);
//			if (image != null) {
//				image = ImageUtil.shrink(image, imageSize);
//				response.setContentType("image/jpeg");
//				response.setContentLength(image.length);
//				os.write(image);
//			}
		// break;
//		}

		case "getImage": {
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
		
		case "getCategoryImage":{
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

		case "addShop": {

			String ID_Json = jsonObject.get("ProductID").getAsString();
			System.out.println("ID_Json = " + ID_Json);
			
			Shopping_Cart shopping_Cart = gson.fromJson(ID_Json, Shopping_Cart.class);
			System.out.println("shopping_Cart"+shopping_Cart.getAccount_ID());
			int count = 0;
			count = shopping_Card_DAO.insert(shopping_Cart);
			writeText(response, String.valueOf(count));
			break;
		}

		case "getAllShop": {

			String jsonAllShop=jsonObject.get("id").getAsString();
			List<Shopping_Cart> shopping_Carts = shopping_Card_DAO.getAll(jsonAllShop);
			writeText(response, gson.toJson(shopping_Carts));
			break;

		}
		
		case "shopDelete":{
			int id =jsonObject.get("shopId").getAsInt();
//			String id =jsonObject.get("shopId").getAsString();
			int count = shopping_Card_DAO.delete(id);
			writeText(response, String.valueOf(count));
			break;
		}
		
		case "addOrderMain":{
			String order_ID = jsonObject.get("OrderID").getAsString();
			String order_Details = jsonObject.get("OrderDetail").getAsString();
			Order_Detail oDetails = null;
			System.out.println("OrderID = " + order_ID);
			System.out.println("OrderDetail = " + order_Details);
			
			
			Type collectionType = new TypeToken<List<Shopping_Cart>>() {
			}.getType();
			List<Shopping_Cart> myBookList = gson.fromJson(order_Details, collectionType);
			for (Shopping_Cart book : myBookList) {
				book.show();
			}
			
			Order_Main order_Main = gson.fromJson(order_ID, Order_Main.class);
			JsonArray jsonArray = gson.fromJson(order_Details, JsonArray.class);
//			Shopping_Cart oDetail = gson.fromJson(order_Details, Shopping_Cart.class);
//			System.out.println("Order_Main="+order_Main.getOrder_ID());
			int orderid = 0;
			int orderdetail = 0;
			orderid = this.order_Main.insert(order_Main);
			System.out.print("----------------test155555555555----------------------");

			
			for (JsonElement element : jsonArray) {
				JsonObject obj = element.getAsJsonObject();
				int order_id = orderid;
				int product = obj.get("product_ID").getAsInt();
				int amount = obj.get("amount").getAsInt();
				String color = obj.get("color").getAsString();
				int price = obj.get("price").getAsInt();
				
				
				oDetails = new Order_Detail(order_id, amount, product, price, color);
				orderdetail = order_Detail_DAO.insert(oDetails);
			}	
			writeText(response, String.valueOf(orderid)+String.valueOf(orderdetail));
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
