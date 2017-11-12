package web.servlet;

import java.io.IOException;

import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Category;
import domain.Product;
import web.service.ProductService;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/indexservlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductService service = new ProductService();
		
		
		
		//准备热门商品
		List<Product> hotProductList = service.findHotProductList();
		request.setAttribute("hotProductList", hotProductList);
		
		//准备最新商品
		List<Product> newProductList = service.findNewProductList();
		request.setAttribute("newProductList", newProductList);
		
		//准备菜单类别
		List<Category> categoryList = service.findAllCategory();
		request.setAttribute("categoryList", categoryList);
	
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
