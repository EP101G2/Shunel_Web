package Servlet_Shunel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
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
import com.google.gson.JsonObject;

import Bean.Notice;
import Bean.User_Account;
import DAO.Uesr_Account_DAO;
import DAO_Interface.Uesr_Account_DAO_Interface;

@WebServlet("/User_Account_Servlet")
public class User_Account_Servlet extends HttpServlet {
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
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		int b = 0;
//		Gson gson = new Gson();
		BufferedReader br = request.getReader(); // request就是android接收的東西（ＪＳＯＮ ＳＴＲＩＮＧ）
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

		switch (action) {

		case "getLogin": {
			String user_Account = jsonObject.get("id").getAsString();
			String user_Password = jsonObject.get("password").getAsString();
			String token = jsonObject.get("getToken").getAsString();
			JsonObject jsonLoginResult = new JsonObject();
			Uesr_Account_DAO user_Account_DAO = new Uesr_Account_DAO_Interface();
			User_Account user = user_Account_DAO.login(user_Account);// 這邊user是去ＤＡＯ拿到Login的
			
			// int delete(int user_Account_ID););
			if (user == null) {
				jsonLoginResult.addProperty("result", "fail");
				jsonLoginResult.addProperty("message", "查無此帳號");

			} else {
				if (user.getAccount_Password().equals(user_Password)) {
					jsonLoginResult.addProperty("result", "success");
					jsonLoginResult.addProperty("user", gson.toJson(user)); // 包了兩層
					if (!token.equals(user.getTOKEN())) {
						 b = user_Account_DAO.updateToken(user.getAccount_ID(), token);//更新Token
						} // 包了三層
				} else {
					jsonLoginResult.addProperty("result", "fail");
				}
			}
			writeText(response, jsonLoginResult.toString());

			System.out.println("output: " + jsonLoginResult);
			break;
		}
		case "Register": {
			String user = jsonObject.get("user").getAsString();
			User_Account user_Account2 = gson.fromJson(user, User_Account.class); // 左邊放ＪＳＯＮ格是自串，右邊放定義他要轉成何種類別物件
			Uesr_Account_DAO user_Account_DAO = new Uesr_Account_DAO_Interface(); // 先實體ＤＡＯ才可已用
			System.out.println("44444444444444445");
			int count = user_Account_DAO.insert(user_Account2);

			writeText(response, String.valueOf(count));
			break;

		}
		case "Update": {
			String user = jsonObject.get("user").getAsString();
			User_Account user_Account3 = gson.fromJson(user, User_Account.class); // 左邊放ＪＳＯＮ格是自串，右邊放定義他要轉成何種類別物件
			Uesr_Account_DAO user_Account_DAO = new Uesr_Account_DAO_Interface(); // 先實體ＤＡＯ才可已用

			byte[] image = null;
			// 檢查是否有上傳圖片
			if (jsonObject.get("imageBase64") != null) {
				String imageBase64 = jsonObject.get("imageBase64").getAsString();
				if (imageBase64 != null && !imageBase64.isEmpty()) {
					image = Base64.getMimeDecoder().decode(imageBase64);
				}
			}
			int count = user_Account_DAO.update(user_Account3, image);

			writeText(response, String.valueOf(count));
			break;

		}
		case "UpdatePw": {
			String user = jsonObject.get("user").getAsString();
			User_Account user_Account = gson.fromJson(user, User_Account.class); // 左邊放ＪＳＯＮ格是自串，右邊放定義他要轉成何種類別物件
			account_DAO = new Uesr_Account_DAO_Interface(); // 先實體ＤＡＯ才可已用

			int count = account_DAO.update(user_Account);

			writeText(response, String.valueOf(count));
			break;

		}
		case "UpdateNewPw": {

			String phone = jsonObject.get("phone").getAsString();
			String password = jsonObject.get("password").getAsString();

			int count = account_DAO.update(phone, password);
			System.out.println("33333333333333333333333333" + count);
			writeText(response, String.valueOf(count));
			break;

		}
		case "getImage": {
			OutputStream os = response.getOutputStream();
			String id = jsonObject.get("id").getAsString();
			int imageSize = jsonObject.get("imageSize").getAsInt();
			byte[] image = account_DAO.getImage(id);
			if (image != null) {
				image = ImageUtil.shrink(image, imageSize);
				response.setContentType("image/jpeg");
				response.setContentLength(image.length);
				os.write(image);
			}
			break;
		}

		case "getAll": {

			List<User_Account> user_Accounts = account_DAO.getAll();
			 
			writeText(response, gson.toJson(user_Accounts));
           System.out.println("------------"+user_Accounts);
			break;
		}

		case "google": {
			String result = "";
			String user = jsonObject.get("user").getAsString();

			User_Account uaFromAndroid = gson.fromJson(user, User_Account.class);
			// 字串 要解析成的類型 就變成ＵＳＥＲＡＣＣＯＵＮＴ的物件
			String id = uaFromAndroid.getAccount_ID();
			User_Account uaFromDB = account_DAO.login(id);

			JsonObject jsonObject2 = new JsonObject();
			if (uaFromDB != null) {
				if (!uaFromAndroid.getTOKEN().equals(uaFromDB.getTOKEN())) { // 如果註冊過會去安著拿ＴＯＫＥＮ去跟ＤＢ做比對
					b = new Uesr_Account_DAO_Interface().updateToken(uaFromAndroid.getAccount_ID(),
							uaFromAndroid.getTOKEN());// 更新Token
				}
				jsonObject2.addProperty("User", gson.toJson(uaFromDB)); // 包了兩層
				result = jsonObject2.toString();
			} else { // 沒註冊過跑的地方
				uaFromAndroid.setAccount_Phone(""); // 送去ＤＡＯ
				uaFromAndroid.setAccount_Password("");
				uaFromAndroid.setAccount_Address("");
				int count = account_DAO.insert(uaFromAndroid);
				if (count == 1) {
					jsonObject2.addProperty("User", gson.toJson(uaFromAndroid)); // 包了兩層
					result = jsonObject2.toString();
				} else {
					result = String.valueOf(count);
				}
			}

			writeText(response, result);

		}
			break;
			
		default:
			throw new IllegalArgumentException("Unexpected value: " + action);
		}
	}

	private void writeText(HttpServletResponse response, String outText) throws IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter printWriter = response.getWriter();
		printWriter.print(outText);
		// 將輸出資料列印出來除錯用
		 System.out.println("output: " + outText);
	}

}
