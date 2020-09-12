package Servlet_Shunel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.reflect.TypeToken;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import Bean.Notice;
import Bean.Product;
import Bean.Promotion;
import Bean.Shopping_Cart;
import DAO.Notice_DAO;
import DAO.Promotion_DAO;
import DAO_Interface.Notice_DAO_Interface;
import DAO_Interface.Product_DAO_Interface;
import DAO_Interface.Promotion_DAO_Interface;
import DAO_Interface.Shopping_Card_DAO_Interdace;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Servlet implementation class Notice_Servlet
 */
@WebServlet("/Notice_Servlet")
public class Notice_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String CONTENT_TYPE = "text/html; charset=utf-8";
	Notice_DAO notice_DAO = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Notice_Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (notice_DAO == null) {
			notice_DAO = new Notice_DAO_Interface();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	/*
	 * 
	 * 要使用的人要注意的分類 pageFlag:0 = "促銷訊息"; 1= "提問通知"; 2= "系統訊息"; 3= "貨態追蹤"; 4= "上架通知";
	 * 
	 * 
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		BufferedReader br = request.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.print("input:" + jsonIn);

		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		if (notice_DAO == null) {
			notice_DAO = new Notice_DAO_Interface();
		}

		String action = jsonObject.get("action").getAsString();
		List<Notice> notices;
		String getAccount_ID;
		notices = null;

		switch (action) {
		case "delete":
			String getDeleteString = "";
			getDeleteString = jsonObject.get("delete").getAsString();
			Type listType = new TypeToken<List<Notice>>() {
			}.getType();
			notices = gson.fromJson(getDeleteString, listType);
			int getDelete = 0;
			for (Notice notice : notices) {
				getDelete = notice_DAO.delete(notice.getNotice_ID());
				getDelete += getDelete;
			}
//			 if(getDelete !=  notices.size() ) {
//				 
//			 }
			writeText(response, String.valueOf(getDelete));

			break;
		case "getNoticeAll":
			getAccount_ID = "";
			getAccount_ID = jsonObject.get("account_ID").getAsString();
			notices = notice_DAO.getNoticeAll(getAccount_ID);
			writeText(response, gson.toJson(notices));
			break;
		case "update":
			String result = jsonObject.get("notice").getAsString();
			Notice notice = gson.fromJson(result, Notice.class);
			int update = notice_DAO.update(notice);
			writeText(response, String.valueOf(update));
			break;

		case "getSaleAll":
			List<Notice> saleNotices = notice_DAO.getSaleAll();
			writeText(response, gson.toJson(saleNotices));
			break;

		case "getGoodAll":
			getAccount_ID = "";
			getAccount_ID = jsonObject.get("account_ID").getAsString();
			List<Notice> goodNotices = notice_DAO.getGoodsAll(getAccount_ID);
			writeText(response, gson.toJson(goodNotices));
			break;

		case "getSystemAll":
			List<Notice> systemNotices = notice_DAO.getSystemAll();
			writeText(response, gson.toJson(systemNotices));
			break;

		case "getLastSaleN":
			Notice lastSaleN = notice_DAO.getLastSaleN();
			writeText(response, gson.toJson(lastSaleN));
			break;

		case "getLastGoodN":
			getAccount_ID = "";
			getAccount_ID = jsonObject.get("account_ID").getAsString();
			Notice lastGoodN = notice_DAO.getLastGoodN(getAccount_ID);
			writeText(response, gson.toJson(lastGoodN));
			break;

		case "getLastSystemN":
			Notice lastSystemN = notice_DAO.getLastSystemN();
			writeText(response, gson.toJson(lastSystemN));
			break;

		case "sendSaleN":

			int productType = jsonObject.get("productType").getAsInt();
			String newSaleT = jsonObject.get("title").getAsString();
			String newSaleD = jsonObject.get("msg").getAsString();
			int countSaleN;
			if (productType == 0) {
				countSaleN = notice_DAO.sendSaleN(newSaleT, newSaleD);
				System.out.println("=====count=====" + countSaleN);

				try {
					FirebaseCloudMsg.getInstance().FCMsendMsgMuti(notice_DAO.getToken(), newSaleT, newSaleD, 0);

				} catch (FirebaseMessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
					
				}

			} else {
				//針對單一商品推
				countSaleN = notice_DAO.sendSaleNAndProduct(newSaleT, newSaleD, productType);
			}
			writeText(response, String.valueOf(countSaleN));
			break;

		case "sendSystemN":
			String newSystemT = jsonObject.get("title").getAsString();
			String newSystemD = jsonObject.get("msg").getAsString();
			int countSystemN = notice_DAO.sendSystemN(newSystemT, newSystemD);
			System.out.println("=====count=====" + countSystemN);
			writeText(response, String.valueOf(countSystemN));
			try {
				FirebaseCloudMsg.getInstance().FCMsendMsgMuti(notice_DAO.getToken(), newSystemT, newSystemD, 2);
			} catch (FirebaseMessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		case "sendOrderN":
			String newChatT = jsonObject.get("title").getAsString();
			String newChatD = jsonObject.get("msg").getAsString();
			String order_ID = jsonObject.get("id").getAsString();
			int countChatN = notice_DAO.sendSystemN(newChatT, newChatD);

			System.out.println("=====count=====" + countChatN);
			writeText(response, String.valueOf(countChatN));
			FirebaseCloudMsg.getInstance().FCMsendMsg(notice_DAO.getOneTokenFromOrderMain(order_ID), newChatT, newChatD,
					1);
			break;
		}

	}

	private void writeText(HttpServletResponse response, String outText) throws IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		out.print(outText);
		// 將輸出資料列印出來除錯用
		System.out.println("output: " + outText);
	}

}
