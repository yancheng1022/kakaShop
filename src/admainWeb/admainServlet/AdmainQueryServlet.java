package admainWeb.admainServlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import admainWeb.admainService.AdmainProduceService;
import domain.Product;

/**
 * Servlet implementation class AdmainQueryServlet
 */
@WebServlet("/admainQueryServlet")
public class AdmainQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("query");
		 
		 AdmainProduceService service = new AdmainProduceService();
		 List<Product> productList = service.queryProduct(name);
		
		 request.setAttribute("products", productList);
		 //response.sendRedirect(request.getContextPath()+"/admin/product/list.jsp");
		 request.getRequestDispatcher("/admin/product/list.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
