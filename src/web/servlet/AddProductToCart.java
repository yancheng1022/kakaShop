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
		//获得要放到购物车里商品的pid
		String pid = request.getParameter("pid");
		//获得该商品的购买数量
		int buyNum = Integer.parseInt(request.getParameter("buyNum"));
		//获得product对象
		Product product = productService.findProductByPid(pid);
		//计算小计
		double subTotal = product.getShop_price()*buyNum;
		
		//封装cartItem
		CartItem item = new CartItem();
		item.setProduct(product);
		item.setNum(buyNum);
		item.setsubTotal(subTotal);
		
		//获得购物车
		//判断是否session已经存在购物车
		HttpSession session = request.getSession();
		Cart cart = (Cart)session.getAttribute("cart");	
			
		if(cart == null){
			cart = new Cart();
		}
		
		//将购物项放入车中
		double newSubtotal =0.0;
		Map<String, CartItem> cartItems = cart.getCartItems();
		if(cartItems.containsKey(pid)){
			//获得购物车中该商品条目
			CartItem cartItem = cartItems.get(pid);
			//获得购物车中该商品数量
			int oldBuyNum = cartItem.getNum();
			//原来的商品数量与现在相加
			oldBuyNum += buyNum;
			//设置新的商品数量
			cartItem.setNum(oldBuyNum);			
			//获取原来的小计
			//double oldsubtotal = cartItem.getsubTotal();
			//新加入物品的小计
			newSubtotal = oldBuyNum*product.getShop_price();
			//新的小计
			cartItem.setsubTotal(newSubtotal);
			cart.setCartItems(cartItems);
		
		}else{
			cart.getCartItems().put(product.getPid(),item);
			newSubtotal = buyNum*product.getShop_price();
		}
		
		//计算总计
		double total = cart.getTotal()+subTotal;
		cart.setTotal(total);
		//将购物车再次放回session
		session.setAttribute("cart", cart);		
		//直接重定向到购物车页面
		response.sendRedirect(request.getContextPath()+"/cart.jsp");
		//如果转发，request.getRequestDispatcher("/cart.jsp").forward(request, response);
		//在购物车页面刷新时会金额一直增加
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
