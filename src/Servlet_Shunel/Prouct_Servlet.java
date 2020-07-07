package Servlet_Shunel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
//import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import Bean.Order_Detail;
import Bean.Order_Main;
import Bean.Product;
import Bean.Shopping_Cart;
import Bean.User_Account;
import DAO.Order_Detail_DAO;
import DAO.Product_DAO;
import DAO.Shopping_Card_DAO;
import DAO_Interface.Order_Detail_DAO_Interface;
import DAO_Interface.Prouct_DAO_Interface;
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
			product_DAO = new Prouct_DAO_Interface();
		}
		List<Product> proucts = product_DAO.getAll();

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
			product_DAO = new Prouct_DAO_Interface();
		}
		if (order_Detail_DAO == null) {
			order_Detail_DAO = new Order_Detail_DAO_Interface();
		}
		if (shopping_Card_DAO == null) {
			shopping_Card_DAO =new Shopping_Card_DAO_Interdace() {
			};
		}
		

		//
		String action = jsonObject.get("action").getAsString();

		switch (action) {
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

		case "addShop": {
			String ID_Json = jsonObject.get("addID").getAsString();
			System.out.println("ID_Json = " + ID_Json);
			Shopping_Cart shopping_Cart = gson.fromJson(ID_Json, Shopping_Cart.class);
//			User_Account user_Account = gson.fromJson("", classOfT)
			int count = 0;
//			int user_Account =produc,,, 
//			int product =product_DAO.findById(prouct_id);
//			Product product = new Product(product_ID, product_Name, product_Color, product_Price, product_Ditail, product_Category_ID, product_Status) 
			
			
			count = shopping_Card_DAO.insert(shopping_Cart);
//			byte[] image = null;
			// 檢查是否有上傳圖片
//			if (jsonObject.get("imageBase64") != null) {
//				String imageBase64 = jsonObject.get("imageBase64").getAsString();
//				if (imageBase64 != null && !imageBase64.isEmpty()) {
//					image = Base64.getMimeDecoder().decode(imageBase64);
//				}
//			}
//			int count = 0;
//			if (action.equals("spotInsert")) {
//				count = product.insert(spot, image);
//			} else if (action.equals("spotUpdate")) {
//				count = spotDao.update(spot, image);
//			}
			writeText(response, String.valueOf(count));
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
