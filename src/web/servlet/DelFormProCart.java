package web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




import domain.Cart;
import domain.CartItem;


@WebServlet("/delFormProCart")
public class DelFormProCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���Ҫɾ����Ʒ��pid
		String pid = request.getParameter("pid");
		//��ûỰ
		HttpSession session = request.getSession();
		//��ù��ﳵ
		Cart cart = (Cart)session.getAttribute("cart");
		//�˴��пշ�ֹɾ��ǰ�Ự����
		if(cart !=null){
			//�����Ʒ�б�
			Map<String, CartItem> cartitems = cart.getCartItems();
			//�ܼۼ�ȥҪɾ����Ʒ�۸񣨷���removeǰ������remove����ô����
			cart.setTotal(cart.getTotal()-cartitems.get(pid).getsubTotal());
			//ɾ����Ʒ
			cartitems.remove(pid);
			//���½��б���빺�ﳵ
			cart.setCartItems(cartitems);
			
			
			
		}
		session.setAttribute("cart", cart);
		response.sendRedirect(request.getContextPath()+"/cart.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
