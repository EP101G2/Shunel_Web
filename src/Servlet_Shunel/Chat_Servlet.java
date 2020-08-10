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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import Chat.ChatMessage;
import DAO.Chat_DAO;
import DAO_Interface.Chat_DAO_InterFace;

//import DAO_Interface.Chat_DAO_InterFace;

/**
 * Servlet implementation class Chat_Servlet
 */
@WebServlet("/Chat_Servlet")
public class Chat_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String CONTENT_TYPE = "text/html; charset=utf-8";
	Chat_DAO cDao= null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Chat_Servlet() {
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
	}

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

		if (cDao == null) {
			cDao = new Chat_DAO_InterFace();
		}
		byte[] image = null;

		System.out.println("Input:" + jsonIn);
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		String type = jsonObject.get("type").getAsString();
		/*-----------------------------------------------------------------------------------------------------------------------------------------------------------*/

		switch (type) {

		/* 建立聊天室 */
		case "createRoom": {

			int room_no;
			int createID = 0;

			String user_ID = jsonObject.get("user_ID").getAsString();
			String admin_ID = jsonObject.get("admin_Id").getAsString();

			room_no = cDao.selectChatRoom(admin_ID, user_ID);
			System.out.println("room_no:" + room_no);
			if (room_no == 0) {
				room_no = cDao.createRoom(admin_ID, user_ID);
				System.out.println("ID:" + createID);
			}

			writeText(response, String.valueOf(room_no));
			break;
		}
		/* 查詢房間 */
		case "findRoomID": {
			int room_no;
			int createID = 0;

			String user_ID = jsonObject.get("user_ID").getAsString();
			String admin_ID = jsonObject.get("admin_Id").getAsString();

			room_no = cDao.selectChatRoom(admin_ID, user_ID);
			System.out.println("room_no:" + room_no);
			if (room_no == 0) {
				room_no = cDao.createRoom(admin_ID, user_ID);
				System.out.println("1 ID:" + createID);
			}
			writeText(response, String.valueOf(room_no));
			break;
		}

		/* 聊天訊息傳入DB */
		case "createChatID": {
			int chat_ID = 0;

			String messageString = jsonObject.get("msg").getAsString();
			int chatID = jsonObject.get("chatID").getAsInt();
			String receiver = jsonObject.get("receiver").getAsString();
			String sender = jsonObject.get("sender").getAsString();
			String msgtype = jsonObject.get("msgtype").getAsString();

			if (jsonObject.get("imageBase64") != null) {
				String imageBase64 = jsonObject.get("imageBase64").getAsString();
				if (imageBase64 != null && !imageBase64.isEmpty()) {
					image = Base64.getMimeDecoder().decode(imageBase64);
				}
			}

			System.out.println(sender + "\t" + msgtype);

			chat_ID = cDao.insert(chatID, messageString, receiver, sender, msgtype, image);

			writeText(response, String.valueOf(chat_ID));
			break;
		}
		/* 聊天記錄 */
		case "getAll": {
			int chat_ID = jsonObject.get("chat_ID").getAsInt();

			List<ChatMessage> messages = cDao.getAll(chat_ID);
			System.out.println("--------------------------------" + messages.toString());
			writeText(response, gson.toJson(messages));
			break;
		}

		/* 讀取照片 */
		case "getImage": {
			OutputStream os = response.getOutputStream();
			int imageID = jsonObject.get("id").getAsInt();
			int imageSize = jsonObject.get("imageSize").getAsInt();
			byte[] getImage = cDao.getImage(imageID);
			if (getImage != null) {
				getImage = ImageUtil.shrink(getImage, imageSize);
				response.setContentType("image/jpeg");
				response.setContentLength(getImage.length);
				os.write(getImage);
				
			}
			break;
		}

		default:
			throw new IllegalArgumentException("Unexpected value: " + type);
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

	public String ResloveRes(int updateStatus) {
		JsonObject jsonObject = new JsonObject();
		if (updateStatus == 0) {
			jsonObject.addProperty("status", "fail");
		} else {
			jsonObject.addProperty("status", "success");
		}
		return jsonObject.toString();
	}
}
