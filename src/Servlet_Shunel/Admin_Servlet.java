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
import com.google.gson.JsonObject;

import Bean.Admin;
import Bean.User_Account;
import DAO.Admin_DAO;
import DAO.Uesr_Account_DAO;
import DAO_Interface.Admin_DAO_Interface;
import DAO_Interface.Uesr_Account_DAO_Interface;


@WebServlet("/Admin")
public class Admin_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String CONTENT_TYPE = "text/html; charset=utf-8";
	Admin_DAO admin_DAO = null;
	Admin admin = null;
	private List<Admin> admins;
   

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		if (admin_DAO == null) {
			admin_DAO = new Admin_DAO_Interface();

		}
		List<Admin> admins = admin_DAO.getAll();
		writeText(response, new Gson().toJson(admins));

	}

	
	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		BufferedReader br = request.getReader(); // request就是android接收的東西（ＪＳＯＮ ＳＴＲＩＮＧ）
		StringBuilder ServletJsonIn = new StringBuilder();
		String line = "";
		while ((line = br.readLine()) != null) {
			ServletJsonIn.append(line);
		}
		// 將輸入資料列印出來除錯用
		System.out.println("input: " + ServletJsonIn);
		
		JsonObject jsonObject = gson.fromJson(ServletJsonIn.toString(), JsonObject.class);// 字串轉JsonObject物件
		if (admin_DAO == null) {
			admin_DAO = new Admin_DAO_Interface();
		}

		String action = jsonObject.get("action").getAsString();
		
		switch (action) {
		case "getLogin": {
			int admin_ID = jsonObject.get("id").getAsInt();
			String admin_Password = jsonObject.get("password").getAsString();

			JsonObject jsonLoginResult = new JsonObject();
			Admin_DAO admin_DAO = new Admin_DAO_Interface();
			Admin admin = admin_DAO.login(admin_ID);
			
			if (admin == null) {
				jsonLoginResult.addProperty("result", "fail");
				jsonLoginResult.addProperty("message", "查無此帳號");

			} else {
				if (admin.getAdmin_User_Password().equals(admin_Password)) {
					jsonLoginResult.addProperty("result", "success");
					jsonLoginResult.addProperty("user", gson.toJson(admin)); // 包了兩層
				} else {
					jsonLoginResult.addProperty("result", "fail");
				}
			}
			writeText(response, jsonLoginResult.toString());

			System.out.println("output: " + jsonLoginResult);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + action);
		}
		
		
	}

	
	
	private void writeText(HttpServletResponse response, String outText) throws IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter printWriter = response.getWriter();
		printWriter.print(outText);
		// 將輸出資料列印出來除錯用
		// System.out.println("output: " + outText);
	}
}