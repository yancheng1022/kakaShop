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
		//获得要删除商品的pid
		String pid = request.getParameter("pid");
		//获得会话
		HttpSession session = request.getSession();
		//获得购物车
		Cart cart = (Cart)session.getAttribute("cart");
		//此处判空防止删除前会话结束
		if(cart !=null){
			//获得商品列表
			Map<String, CartItem> cartitems = cart.getCartItems();
			//总价减去要删除商品价格（放在remove前，否则remove了怎么减）
			cart.setTotal(cart.getTotal()-cartitems.get(pid).getsubTotal());
			//删除商品
			cartitems.remove(pid);
			//重新将列表加入购物车
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
