package Servlet_Shunel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
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
import com.google.rpc.Status;

import DAO.Notice_DAO;
import DAO.Order_Detail_DAO;
import DAO.Order_Main_DAO;
import DAO_Interface.Notice_DAO_Interface;
import DAO_Interface.Oder_Main_DAO_Interface;
import DAO_Interface.Order_Detail_DAO_Interface;
import DAO_Interface.Product_DAO_Interface;
import DAO_Interface.Shopping_Card_DAO_Interdace;
import DAO_Interface.Uesr_Account_DAO_Interface;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

import Bean.Notice;
import Bean.Order_Detail;
import Bean.Order_Main;
import Bean.User_Account;
import Bean.orderStatistics;
import DAO.Order_Detail_DAO;
import DAO.Order_Main_DAO;
import DAO.Product_DAO;
import DAO.Uesr_Account_DAO;
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
	Notice_DAO notice_DAO = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Orders_Servlet() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
		if (product_DAO == null) {
			product_DAO = new Product_DAO_Interface();
		}
		if (order_Main_DAO == null) {
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

////	to fix the duplicate case error: Using an enum, they will be properly initialized in the order in which they've been placed in the enum.
//	public enum actionSelection{
//		getOrderMain, getOrderDetail, getImage, changeOrderStatus, getOrderMainShort, getOrderDetailShort, getOrdersForManage, changeOrderStatus, 
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		if (order_Main_DAO == null) {
			order_Main_DAO = new Oder_Main_DAO_Interface();// ?
		}
		if (order_Detail_DAO == null) {
			order_Detail_DAO = new Order_Detail_DAO_Interface();
		}
		if (product_DAO == null) {
			product_DAO = new Product_DAO_Interface();
		}
		if (order_Main_DAO == null) {
			order_Main_DAO = new Oder_Main_DAO_Interface();// ?
		}
		if (order_Detail_DAO == null) {
			order_Detail_DAO = new Order_Detail_DAO_Interface();
		}
		if (notice_DAO == null) {
			notice_DAO = new Notice_DAO_Interface();
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
			int orderID = jsonObject.get("orderID").getAsInt();
			JsonObject jsonGetOrderMain = new JsonObject();
			Order_Main_DAO orderMainDao = new Oder_Main_DAO_Interface();
			Order_Main orderMain = orderMainDao.getOrderMain(orderID);
			
			if (orderMain == null) {
				jsonGetOrderMain.addProperty("result", "fail");
				jsonGetOrderMain.addProperty("message", "查無此帳號");
			}else {
				if (orderMain.getOrder_ID() == orderID) {
					jsonGetOrderMain.addProperty("result", "success");
					jsonGetOrderMain.addProperty("orderMain", gson.toJson(orderMain));
				}else {
					jsonGetOrderMain.addProperty("result", "fail");
				}
			}
			writeText(response, gson.toJson(jsonGetOrderMain));
			System.out.println("--getOrderMain-->"+jsonGetOrderMain);
			break;
		}
		case "getOrderDetail": {
			int orderDetailID = jsonObject.get("orderID").getAsInt();
			writeText(response, gson.toJson(orderDetailID));
			break;
		}
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
		// 結帳後修改狀態0->1
		// 發送結帳通過通知
		case "changeOrderStatus": {
			int count = 0;
			int orderid = jsonObject.get("OrderID").getAsInt();
			String oM = jsonObject.get("oM").getAsString();
			Order_Main oMain = new Gson().fromJson(oM, Order_Main.class);
			System.out.println(orderid);
			int changePriceNotice;
			String token;
			Notice sendFirebase;
			changePriceNotice = notice_DAO.sendGoodsPriceNotice(orderid);
			token = notice_DAO.getOneTokenFromOrderMain(String.valueOf(orderid));
			sendFirebase = notice_DAO.TitleAndDetail(1, String.valueOf(orderid));
//			System.out.println(sendFirebase + "====sF=====");
//			String title = sendFirebase.getNotice_Title();
//			String msg = sendFirebase.getNotice_Content();
//			System.out.println(title + "====T=====");
//			System.out.println(msg + "====MSG=====");
//			FirebaseCloudMsg.getInstance().FCMsendMsg(token, title, msg, 1);
			count = order_Main_DAO.updataOrder(orderid,oMain);
			writeText(response, String.valueOf(count));
			break;
		}

//		get OrderList for orderListFragment
		case "getOrderMains": {
			 List<Order_Main> orderShortMainMainList1;
			int status = jsonObject.get("status").getAsInt();
			int status1 = 0;
			String account_ID = jsonObject.get("Account_ID").getAsString();
			if (status ==0 && status1==0) {
//				status = jsonObject.get("status").getAsInt();
				status1 = jsonObject.get("status1").getAsInt();
			}else {
				status = jsonObject.get("status").getAsInt();
			}
			
			List<Order_Main> orderShortMainMainList;
			System.out.print("input accountId & status: "+account_ID+", "+status);
			 orderShortMainMainList = order_Main_DAO.getOrderMains(account_ID, status);
			 if (status1 != 0 ) {
				 orderShortMainMainList1 = order_Main_DAO.getOrderMains(account_ID, status1);
				 orderShortMainMainList.addAll( orderShortMainMainList1);
			}
			 
			 
			 
			writeText(response, gson.toJson(orderShortMainMainList));
			break;
		}
////		get short order detail list insert here(banned!!
//		case "getOrderDetailShort": {
//			int order_ID = jsonObject.get("Order_ID").getAsInt(); // check if "Order_ID" matches the "Order_ID" in client
//			List<Order_Main> orderShortDetailList = order_Main_DAO.getShortOrderDetails(order_ID);
//			writeText(response, gson.toJson(orderShortDetailList));
//		}  
////		get product id by order id
//		case "getProductForOrders": {
//			int Order_ID = jsonObject.get("order_Id").getAsInt();
//			System.out.print("getProductForOrders where orderId: "+Order_ID);
//			List<Order_Detail> orderedProductList = order_Detail_DAO.getProductForOrders(Order_ID);
//			writeText(response, gson.toJson(orderedProductList));
//			break;
//		}//banned!!
		
//		get ordered product by order id(back and front)
		case "getOrderedProducts": {
			int Order_ID = jsonObject.get("order_Id").getAsInt();
			System.out.println("get order id from client ->"+Order_ID);
			List<Order_Detail> orderedProductList = order_Detail_DAO.getOrderedProducts(Order_ID);
			writeText(response, gson.toJson(orderedProductList));
			break;
		}

//		get short order management list data
		case "getOrdersForManage": {
			List<Order_Main> orderManageList = order_Main_DAO.getOrdersForManage();
			writeText(response, gson.toJson(orderManageList));
			System.out.println("---getOrdersForManage---" + orderManageList);
			break;
		}//ok

//		change on order status
		case "updateStatus": {
			int count = 0;
//			get order id and new status
			int orderId = jsonObject.get("orderId").getAsInt();
			int status = jsonObject.get("status").getAsInt();
			count = order_Main_DAO.updateStatus(orderId, status);
			switch (status) {
			case 2:
				int changeOrderNStatus = notice_DAO.sendGoodsNotice(String.valueOf(orderId));
				String token = notice_DAO.getOneTokenFromOrderMain(String.valueOf(orderId));
				Notice sendFirebase = notice_DAO.TitleAndDetail(3, String.valueOf(orderId));
				System.out.println(sendFirebase + "====sF=====");
				String title = sendFirebase.getNotice_Title();
				String msg = sendFirebase.getNotice_Content();
				System.out.println(title + "====T=====");
				System.out.println(msg + "====MSG=====");
				FirebaseCloudMsg.getInstance().FCMsendMsg(token, title, msg, 1);
			}
			System.out.println(orderId + "->" + status);
//			Order_Main order_Main = gson.fromJson(status, Order_Main.class);
//			Order_Main_DAO order_Main_DAO = new Oder_Main_DAO_Interface();
			System.out.print("---changeOrdersStatus---");
			writeText(response, String.valueOf(count));
			break;
		}

//		update receiver data
		case "update": {
			System.out.print("---updateOrdersReceiverData---");
			String receiver = jsonObject.get("Receiver").getAsString();
			Order_Main orderMain = gson.fromJson(receiver, Order_Main.class); // 左邊放ＪＳＯＮ格是自串，右邊放定義他要轉成何種類別物件
			Order_Main_DAO order_Main_DAO = new Oder_Main_DAO_Interface();
			; // 先實體ＤＡＯ才可已用

			int count = order_Main_DAO.update(orderMain);

			writeText(response, String.valueOf(count));
			break;
		}

//		select/show by status: delete this if not needed!
		case "status": {
			int status = jsonObject.get("status").getAsInt();
			if (status == 0) {
				int order_Mains = order_Main_DAO.getStatus(0);
//				List<Order_Main> order_Mains = order_Main_DAO.getStatus(0);
				writeText(response, gson.toJson(order_Mains));
			} else if (status == 1) {
				int order_Mains = order_Main_DAO.getStatus(1);
//				List<Order_Main> order_Mains = order_Main_DAO.getStatus(1);
				writeText(response, gson.toJson(order_Mains));
			} else if (status == 2) {
				int order_Mains = order_Main_DAO.getStatus(2);
//				List<Order_Main> order_Mains = order_Main_DAO.getStatus(2);
				writeText(response, gson.toJson(order_Mains));
			} else if (status == 3) {
				int order_Mains = order_Main_DAO.getStatus(3);
//				List<Order_Main> order_Mains = order_Main_DAO.getStatus(3);
				writeText(response, gson.toJson(order_Mains));
			} else if (status == 4) {
				int order_Mains = order_Main_DAO.getStatus(4);
//				List<Order_Main> order_Mains = order_Main_DAO.getStatus(4);
				writeText(response, gson.toJson(order_Mains));
			} else if (status == 5) {
				int order_Mains = order_Main_DAO.getStatus(5);
//				List<Order_Main> order_Mains = order_Main_DAO.getStatus(4);
				writeText(response, gson.toJson(order_Mains));
			}
			break;
		}
		
		
		/*訂單數ㄌㄤ統計*/
		case "getStatistics":{
			Gson gsonTime = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			List<orderStatistics> oStatistics = null;
			
			String date1 = jsonObject.get("date1").getAsString();
			String date2 = jsonObject.get("date2").getAsString();
			
			Timestamp dateTime1=StrtoTimestamp(date1);
			Timestamp dateTime2=StrtoTimestamp(date2);
			
			oStatistics = order_Main_DAO.getStatistics(dateTime1, dateTime2);
			
			writeText(response, gsonTime.toJson(oStatistics));
			
			break;
			
		}
		
		default:
			throw new IllegalArgumentException("Unexpected value: " + action);
		}
	}

	private Timestamp StrtoTimestamp(String date1) {
	// TODO Auto-generated method stub
		
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		try {
		ts = Timestamp.valueOf(date1);
		System.out.println(ts);
		} catch (Exception e) {
		e.printStackTrace();
		} 
		
		return ts;
	
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
