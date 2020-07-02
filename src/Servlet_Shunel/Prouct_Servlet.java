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

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import Bean.Prouct;
import DAO.Prouct_DAO;
import DAO_Interface.Prouct_DAO_Interface;

/**
 * Servlet implementation class Prouct_Servlet
 */
@WebServlet("/Prouct_Servlet")
public class Prouct_Servlet extends HttpServlet {
	private static final String TAG="TAG_Prouct_Servlet";
	private static final long serialVersionUID = 1L;
	private final static String CONTENT_TYPE = "text/html; charset=utf-8";
	Prouct_DAO prouct_DAO=null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Prouct_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		if (prouct_DAO==null) {
			prouct_DAO=new Prouct_DAO_Interface();
		}
		List<Prouct> proucts =prouct_DAO.getAll();
		
		writeText(response, new Gson().toJson(proucts));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		Gson gson = new Gson();
		BufferedReader br = request.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = "";
		while((line=br.readLine())!=null) {
			jsonIn.append(line);
		}
		
		System.out.println("Input:"+jsonIn);
		
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		if (prouct_DAO==null) {
			prouct_DAO=new Prouct_DAO_Interface();
		}
		
		//
		String action = jsonObject.get("action").getAsString();
		
		switch (action) {
		case "getAll": {
			List<Prouct> proucts = prouct_DAO.getAll();
			writeText(response,gson.toJson(proucts));
			
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + action);
		}
		
		
		
	}

	private void writeText(HttpServletResponse response,String outText) throws IOException {
		// TODO Auto-generated method stub
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		out.print(outText);
		// 將輸出資料列印出來除錯用
		 System.out.println("output: " + outText);
		
	}

}
