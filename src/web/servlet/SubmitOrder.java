package web.servlet;

import java.io.IOException;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import domain.Cart;
import domain.CartItem;
import domain.Order;
import domain.OrderItem;
import domain.Product;
import domain.User;
import utils.CommonUtils;
import web.service.ProductService;

@WebServlet("/submitOrder")
public class SubmitOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("User");
		if(user == null){
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return;
		}
		
		
		Order order = new Order();	
		//订单号
		String oid = CommonUtils.getUUID();
		order.setOid(oid);
		//下单时间
		order.setOrderTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		//订单的总金额
		Cart cart = (Cart) session.getAttribute("cart");
		double total = cart.getTotal();
		order.setTotal(total);
		//订单状态
		order.setState(0);
		//收货地址
		order.setAddress(null);
		//收货人
		order.setName(null);
		//收货人电话
		order.setTelephone(null);
		//属于哪个用户
		order.setUser(user);
		//购物项集合map
		Map<String, CartItem> cartItems = cart.getCartItems();
		for(Map.Entry<String, CartItem> entry:cartItems.entrySet()){
			CartItem cartItem = entry.getValue();
			OrderItem orderItem = new OrderItem();
		
			orderItem.setItemid(CommonUtils.getUUID());
			orderItem.setCount(cartItem.getNum());
			orderItem.setSubtotal(cartItem.getsubTotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);
			
			//将该订单项添加到订单项集合
			order.getOrderItems().add(orderItem);
		
		}
		ProductService service = new ProductService();
		try {
			service.submitOrder(order);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		session.setAttribute("order", order);		
		//跳转页面
		response.sendRedirect(request.getContextPath()+"/order_info.jsp");
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
