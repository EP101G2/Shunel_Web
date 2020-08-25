package Servlet_Shunel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
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

import Bean.Promotion;
import DAO.Notice_DAO;
import DAO.Promotion_DAO;
import DAO_Interface.Notice_DAO_Interface;
import DAO_Interface.Promotion_DAO_Interface;

/**
 * Servlet implementation class Promotion_servlet
 */
@WebServlet("/Promotion_Servlet")
public class Promotion_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String CONTENT_TYPE = "text/html; charset=utf-8";
	Promotion_DAO promotion_DAO = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Promotion_Servlet() {
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
		if (promotion_DAO == null) {
			promotion_DAO = new Promotion_DAO_Interface();
			
			List<Promotion> promotions = promotion_DAO.getPromotionAll();
			writeText(response, new Gson().toJson(promotions));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		if (promotion_DAO == null) {
			promotion_DAO = new Promotion_DAO_Interface();
		}

		String action = jsonObject.get("action").getAsString();

		switch (action) {
		case "getpromotionPriceAndDate":
			int producd_id = jsonObject.get("ID").getAsInt();
			Gson gson1 = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			Promotion promotion = promotion_DAO.findById(producd_id);
			writeText(response, gson1.toJson(promotion));
			break;
		
		
		case "getPromotionAll":
			List<Promotion> promotions = promotion_DAO.getPromotionAll();
			writeText(response, gson.toJson(promotions));
			break;
			
		case "getPromotionForNotice":
			List<Promotion> promotionsForNotice = promotion_DAO.getPromotionForNotice();
			System.out.println("\n"+promotionsForNotice.toString()+"\n");
//			QuartzManager.addJob(jobName, jobClass, StartTime, product_id);
			
			writeText(response, gson.toJson(promotionsForNotice));
			break;
			
		case "getImage":
			OutputStream os = response.getOutputStream();
			int id = jsonObject.get("id").getAsInt();
			int imageSize = jsonObject.get("imageSize").getAsInt();
			byte[] image = promotion_DAO.getImage(id);
			if (image != null) {
				image = ImageUtil.shrink(image, imageSize);
				response.setContentType("image/jpeg");
				response.setContentLength(image.length);
				os.write(image);
				break;

			}
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
