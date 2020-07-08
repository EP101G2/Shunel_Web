package Servlet_Shunel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import Bean.User_Account;
import DAO.Uesr_Account_DAO;
import DAO_Interface.Uesr_Account_DAO_Interface;

@WebServlet("/Uesr_Account_Servlet")
public class Uesr_Account_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String CONTENT_TYPE = "text/html; charset=utf-8";
	Uesr_Account_DAO account_DAO = null;
	User_Account user_Account = null;
	private List<User_Account> user_Accounts;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		if (account_DAO == null) {
			account_DAO = new Uesr_Account_DAO_Interface();
		}
		List<User_Account> user_Accounts = account_DAO.getAll();
		writeText(response, new Gson().toJson(user_Accounts));

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
		BufferedReader br = request.getReader();  //request就是ＡＮＤＲＯＩＤ街收的東西（ＪＳＯＮ ＳＴＲＩＮＧ）
		StringBuilder ServletJsonIn = new StringBuilder();
		String line = "";
		while ((line = br.readLine()) != null) {
			ServletJsonIn.append(line);
		}
		// 將輸入資料列印出來除錯用
		System.out.println("input: " + ServletJsonIn);
		
		JsonObject jsonObject = gson.fromJson(ServletJsonIn.toString(), JsonObject.class);// 字串轉JsonObject物件
		if (account_DAO == null) {
			account_DAO = new Uesr_Account_DAO_Interface();
		}
		
		
		String action = jsonObject.get("action").getAsString();
		String user_Account = jsonObject.get("id").getAsString();
		String user_passwordString = jsonObject.get("password").getAsString();
		
		switch (action) {
		case "getLogin": {
			JsonObject jsonLoginResult = new JsonObject();
			Uesr_Account_DAO user_Account_DAO = new Uesr_Account_DAO_Interface();
			User_Account user = user_Account_DAO.login(user_Account);

			if (user == null) {
				jsonLoginResult.addProperty("result", "fail");

				jsonLoginResult.addProperty("message", "查無此帳號");

			} else {
				if (user.getAccount_Password().equals(user_passwordString)) {
					jsonLoginResult.addProperty("result", "success");
					jsonLoginResult.addProperty("user", gson.toJson(user));  //包了兩層
				} else {
					jsonLoginResult.addProperty("result", "fail");
				}
			}
			writeText(response, jsonLoginResult.toString());


		
			System.out.println("output: "+jsonLoginResult);
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
