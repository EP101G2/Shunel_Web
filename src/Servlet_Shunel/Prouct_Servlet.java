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
import DAO.Like_DAO;
import DAO.Notice_DAO;
import DAO.Order_Detail_DAO;
import DAO.Order_Main_DAO;
import DAO.Product_DAO;
import DAO.Shopping_Card_DAO;
import DAO_Interface.Like_DAO_Interface;
import DAO_Interface.Notice_DAO_Interface;
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
	Like_DAO like_DAO = null;
	Notice_DAO notice_DAO  = null;

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
		FirebaseCloudMsg.getInstance().FCMsendMsg("dyAIWKsYBuI:APA91bEMz8rQT17XMQeA1CIjSGUYmt3qLQKXkYePoLcPB3zXA-VIFQZFvl40FrEs8hTTmsMdB1tVISnvxm87cmJp9SxhngPcsBMdLl4yIF-nJKtu41aTM84BP-41eUvKpkZN9ETHTtdO", "測試", "這也是測試");
		
		
//		FirebaseCloudMsg.getInstance().FCMsendMsg("chA6q9_2Rkk:APA91bHL42P6eBvENabCMbwSIe0u_wF7HkkSQqJ9MNyY_BkFhSiv322eRgHVNSGSkLnX4eHLpSUZgM0hSqkm4mtRvElQ63VUR3FFee3QN_lt_UQ7sxiCYO8wIJEwnsDFI7IGbwlqN_Di", "title", "msg");		
		if (like_DAO == null) {
			like_DAO = new Like_DAO_Interface();
		}
		if (product_DAO == null) {
			product_DAO = new Product_DAO_Interface();
		}

		if (shopping_Card_DAO == null) {
			shopping_Card_DAO = new Shopping_Card_DAO_Interdace() {
			};
		}

		if (order_Main == null) {
//			order_Main = new Ord
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
		if (like_DAO == null) {
			like_DAO = new Like_DAO_Interface();
		}

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
		if (order_Main == null) {
			order_Main = new Oder_Main_DAO_Interface();
		}
		
		if (notice_DAO == null) {
			notice_DAO = new Notice_DAO_Interface();
		}
		

		//
		String action = jsonObject.get("action").getAsString();

		switch (action) {

		case "deleteLike": {
			String account_id = jsonObject.get("account_id").getAsString();
			int product_id = jsonObject.get("product_id").getAsInt();
			System.out.println("這是刪除追蹤product" + account_id + product_id);

			int count = like_DAO.deleteLike(account_id, product_id);
			System.out.println("=====count=====" + count);
			writeText(response, String.valueOf(count));
			break;
		}
		case "insertLike": {
			String account_id = jsonObject.get("account_id").getAsString();
			int product_id = jsonObject.get("product_id").getAsInt();
			System.out.println("這是加入追蹤product" + account_id + product_id);

			int count = like_DAO.insertLike(account_id, product_id);
			System.out.println("=====count=====" + count);
			writeText(response, String.valueOf(count));

			break;
		}

		case "searchLike": {
			String account_id = jsonObject.get("account_id").getAsString();
			int product_id = jsonObject.get("product_id").getAsInt();
			System.out.println("這是product" + account_id + product_id);

			Product product = like_DAO.searchLike(account_id, product_id);
			System.out.println("這是product" + product);
			JsonObject jsonObject2 = new JsonObject();
			if (product == null) {

				jsonObject2.addProperty("follow", "null");

			} else {

				jsonObject2.addProperty("follow", "success");
			}
			writeText(response, jsonObject2.toString());
			break;
		}

		case "getLikeProduct": {
			String user_id = jsonObject.get("id").getAsString();
			List<Product> proucts = product_DAO.getLikeProduct(user_id);
			writeText(response, gson.toJson(proucts));
			break;

		}

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
		
		
		
		case "getTOP5Product": {
			List<Product> proucts = product_DAO.getTOP5Product();
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
 
				response.setContentType("image/jpeg");
				response.setContentLength(image.length);
				os.write(image);

			}
			break;
		}

		case "getCategoryImage": {
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
			System.out.println("shopping_Cart" + shopping_Cart.getAccount_ID());
			int count = 0;
			count = shopping_Card_DAO.insert(shopping_Cart);
			writeText(response, String.valueOf(count));
			break;
		}

		case "getAllShop": {

			String jsonAllShop = jsonObject.get("id").getAsString();
			List<Shopping_Cart> shopping_Carts = shopping_Card_DAO.getAll(jsonAllShop);
			writeText(response, gson.toJson(shopping_Carts));
			break;

		}

		case "shopDelete": {
			int id = jsonObject.get("shopId").getAsInt();
//			String id =jsonObject.get("shopId").getAsString();
			int count = shopping_Card_DAO.delete(id);
			writeText(response, String.valueOf(count));
			break;
		}

		case "shopAfterDelete": {
			int count = 0;
			Shopping_Cart shopping_Cart = null;
			String shopAD = jsonObject.get("shopAD").getAsString();
			Type collectionType = new TypeToken<List<Shopping_Cart>>() {
			}.getType();
			List<Shopping_Cart> shopping_Carts = gson.fromJson(shopAD, collectionType);
			for (Shopping_Cart book : shopping_Carts) {
				book.show();
			}
			JsonArray jsonArray = gson.fromJson(shopAD, JsonArray.class);
			for (JsonElement element : jsonArray) {
				JsonObject obj = element.getAsJsonObject();
				String account_ID = obj.get("account_ID").getAsString();
				int product_ID = obj.get("product_ID").getAsInt();
				String product_Name = obj.get("product_Name").getAsString();
				int amount = obj.get("amount").getAsInt();
				String color = obj.get("color").getAsString();
				int price = obj.get("price").getAsInt();

				shopping_Cart = new Shopping_Cart(account_ID, product_ID, product_Name, amount, color, price);
				count = shopping_Card_DAO.delete(shopping_Cart);
			}
			writeText(response, String.valueOf(count));

			break;
		}

		case "findById": {
			int id = jsonObject.get("PRODUCT_Id").getAsInt();
			Product product = product_DAO.findById(id);
			writeText(response,new Gson().toJson(product));
			
			break;

		}

//		case "Main_Recriver": {
//			int count = 0;
//			String orderJson = jsonObject.get("orderupdate").getAsString();
//			System.out.println("orderJson = " + orderJson);
//			Order_Main oM = gson.fromJson(orderJson, Order_Main.class);
//			count = order_Main.update(oM);
//			
//			writeText(response, String.valueOf(count));
//			break;
//		}

		case "addOrderMain": {
			String order_ID = jsonObject.get("OrderID").getAsString();
			String order_Details = jsonObject.get("OrderDetail").getAsString();
			Order_Detail oDetails = null;
//			System.out.println("========"+order_ID);
			
			Type collectionType = new TypeToken<List<Shopping_Cart>>() {
			}.getType();
			List<Shopping_Cart> myBookList = gson.fromJson(order_Details, collectionType);
			for (Shopping_Cart book : myBookList) {
				book.show();
			}

			Order_Main order_Main = gson.fromJson(order_ID, Order_Main.class);
//			System.out.println(order_Main.getAccount_ID()+"123456789");
			JsonArray jsonArray = gson.fromJson(order_Details, JsonArray.class);

			int orderid = 0;
			int orderdetail = 0;
			int notice =0;
			orderid = this.order_Main.insert(order_Main);

			String title = "您的訂單已成立";
			String content ="您的訂單已成立，訂單編號為";

//			System.out.println("orderid======================="+orderid);

			notice = notice_DAO.putGoodNotice(orderid);
//			System.out.println("notice======================="+notice);
			
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

			writeText(response, String.valueOf(orderid));
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
