package web.servlet;

import java.io.IOException;
import java.net.URI;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Cart;
import domain.CartItem;
import domain.Product;

import web.service.ProductService;

/**
 * Servlet implementation class AddProductToCart
 */
@WebServlet("/addProductToCart")
public class AddProductToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductService productService = new ProductService();
		//���Ҫ�ŵ����ﳵ����Ʒ��pid
		String pid = request.getParameter("pid");
		//��ø���Ʒ�Ĺ�������
		int buyNum = Integer.parseInt(request.getParameter("buyNum"));
		//���product����
		Product product = productService.findProductByPid(pid);
		//����С��
		double subTotal = product.getShop_price()*buyNum;
		
		//��װcartItem
		CartItem item = new CartItem();
		item.setProduct(product);
		item.setNum(buyNum);
		item.setsubTotal(subTotal);
		
		//��ù��ﳵ
		//�ж��Ƿ�session�Ѿ����ڹ��ﳵ
		HttpSession session = request.getSession();
		Cart cart = (Cart)session.getAttribute("cart");	
			
		if(cart == null){
			cart = new Cart();
		}
		
		//����������복��
		double newSubtotal =0.0;
		Map<String, CartItem> cartItems = cart.getCartItems();
		if(cartItems.containsKey(pid)){
			//��ù��ﳵ�и���Ʒ��Ŀ
			CartItem cartItem = cartItems.get(pid);
			//��ù��ﳵ�и���Ʒ����
			int oldBuyNum = cartItem.getNum();
			//ԭ������Ʒ�������������
			oldBuyNum += buyNum;
			//�����µ���Ʒ����
			cartItem.setNum(oldBuyNum);			
			//��ȡԭ����С��
			//double oldsubtotal = cartItem.getsubTotal();
			//�¼�����Ʒ��С��
			newSubtotal = oldBuyNum*product.getShop_price();
			//�µ�С��
			cartItem.setsubTotal(newSubtotal);
			cart.setCartItems(cartItems);
		
		}else{
			cart.getCartItems().put(product.getPid(),item);
			newSubtotal = buyNum*product.getShop_price();
		}
		
		//�����ܼ�
		double total = cart.getTotal()+subTotal;
		cart.setTotal(total);
		//�����ﳵ�ٴηŻ�session
		session.setAttribute("cart", cart);		
		//ֱ���ض��򵽹��ﳵҳ��
		response.sendRedirect(request.getContextPath()+"/cart.jsp");
		//���ת����request.getRequestDispatcher("/cart.jsp").forward(request, response);
		//�ڹ��ﳵҳ��ˢ��ʱ����һֱ����
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
