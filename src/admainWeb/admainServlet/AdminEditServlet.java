package admainWeb.admainServlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admainWeb.admainService.AdmainProduceService;
import domain.Category;
import domain.Product;
import web.service.ProductService;

/**
 * Servlet implementation class AdminEditServlet
 */
@WebServlet("/adminEditServlet")
public class AdminEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdmainProduceService service = new AdmainProduceService();
		String pid = request.getParameter("pid");
		Product product = service.edit(pid);
		List<Category> categorys = service.findCatalog();
		request.setAttribute("categorys", categorys);
		request.setAttribute("Product", product);
		request.getRequestDispatcher("/admin/product/edit.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
