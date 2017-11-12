package admainWeb.admainServlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;



import admainWeb.admainService.AdmainProduceService;
import domain.Product;
import domain.User;

/**
 * Servlet implementation class AdmainAddProdouct
 */
@WebServlet("/admainAddProduct")
public class AdmainAddProdouct extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdmainProduceService service = new AdmainProduceService();
		request.setCharacterEncoding("utf-8");
		Map<String, String[]> properties = request.getParameterMap();
		Product product = new Product();
		try {
			BeanUtils.populate(product, properties);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		service.addProduct(product);
		response.sendRedirect(request.getContextPath()+"/admainProductList");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
