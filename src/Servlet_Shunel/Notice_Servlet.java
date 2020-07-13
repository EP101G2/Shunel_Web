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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import Bean.Notice;
import Bean.Product;
import Bean.Shopping_Cart;
import DAO.Notice_DAO;
import DAO_Interface.Notice_DAO_Interface;
import DAO_Interface.Product_DAO_Interface;
import DAO_Interface.Shopping_Card_DAO_Interdace;

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

		List<Notice> notices = notice_DAO.getNoticeAll();
		writeText(response, new Gson().toJson(notices));

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
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

		if (action.equals("getNoticeAll")) {
			List<Notice> notices = notice_DAO.getNoticeAll();
			writeText(response, gson.toJson(notices));
		} else if (action.equals("getSaleAll")) {
			List<Notice> saleNotices = notice_DAO.getSaleAll();
			writeText(response, gson.toJson(saleNotices));
		}else if (action.equals("getQAAll")) {
			List<Notice> qANotices = notice_DAO.getQAAll();
			writeText(response, gson.toJson(qANotices));
		}else if (action.equals("getSystemAll")) {
			List<Notice> systemNotices = notice_DAO.getSystemAll();
			writeText(response, gson.toJson(systemNotices));
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
