package web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import domain.Order;
import domain.OrderItem;
import domain.Product;
import domain.User;
import web.service.ProductService;

/**
 * Servlet implementation class MyOrder
 */
@WebServlet("/myOrder")
public class MyOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("User");
		if(user == null){
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return;
		
		}
		//查询用户的所有的订单信息
		ProductService service = new ProductService();
		List<Order> orders = service.finaAllOrders(user.getUid());
		
		//查询该订单的所有订单项
		if(orders !=null){
			for(Order order:orders){
				String oid = order.getOid();
				List<Map<String, Object>> orderMapListItems = service.findAllOrderItem(oid);
				//将orderMapListItems  转换成 oederItems
				for(Map<String,Object> map:orderMapListItems){
					//从map取出count subTotal 封装到orderItem
					 OrderItem item = new OrderItem(); 
					 Product product = new Product();
					 try {
						BeanUtils.populate(item, map);
						 BeanUtils.populate(product,map);
						 item.setProduct(product);
						 //item封装到order的orderitemList中
						 order.getOrderItems().add(item);
					} catch (IllegalAccessException | InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				
				}
			
			}
		
		}
		//orderlist封装完整了
		request.setAttribute("orderList", orders);
		request.getRequestDispatcher("/order_list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
