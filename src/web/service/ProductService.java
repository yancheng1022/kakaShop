package web.service;

import java.sql.SQLException;



import java.util.List;
import java.util.Map;

import admainDao.AdmainProcuctDao;
import dao.ProductDao;
import domain.Category;
import domain.Order;
import domain.OrderItem;
import domain.PageBean;
import domain.Product;
import utils.DataSourceUtils;

public class ProductService {

	//���������Ʒ
	public List<Product> findHotProductList() {
		ProductDao dao = new ProductDao();
		List<Product> hotProductList = null;
		try {
			hotProductList =  dao.findHotProduct();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hotProductList;
	}

	
	
	//���������Ʒ
	

	

	public List<Product> findNewProductList() {
		ProductDao dao = new ProductDao();
		List<Product> newProductList = null;
		try {
			newProductList =  dao.findNewProduct();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newProductList;
	}



	public List<Category> findAllCategory() {
		ProductDao dao = new ProductDao();
		List<Category> category = null;
		try {
			category = dao.findAllCategory();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return category;
	}

	
	
	public PageBean findProductListByCid(String cid,int currentPage, int currentCount) {
		ProductDao dao = new ProductDao();
		//��װһ��pageBean.����web��
		PageBean<Product> pageBean = new PageBean<Product>();
		
		
		
		//��װ��ǰҳ
		pageBean.setCurrentPage(currentPage);
		//��װÿҳ��ʾ������
		pageBean.setCurrentCount(currentCount);
		// ��װ������
		int totalCount = 0;
		try {
			totalCount = dao.getCount(cid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pageBean.setTotalCount(totalCount);
		//4.��װ��ҳ��
		int totalPage = (int)Math.ceil(1.0*totalCount/currentCount);
		pageBean.setTotalPage(totalPage);
		
		//5.��ǰҳ��ʾ������
		int index = (currentPage-1)*currentCount;
		List<Product> list = null;
		try {
			list = dao.findProductByPage(cid,index,currentCount);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pageBean.setList(list);
		return pageBean;
	}



	public Product findProductByPid(String pid) {
		ProductDao dao = new ProductDao();
		Product product = null;
		try {
			product = dao.findProductByPid(pid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return product;
	}


	//�ύ����
	public void submitOrder(Order order) throws SQLException {
		ProductDao dao = new ProductDao();
		
		try {
			DataSourceUtils.startTransaction();
			
			dao.addOrders(order);
			dao.addOrderItem(order);
			
		} catch (Exception e) {
			DataSourceUtils.rollback();
			e.printStackTrace();
		}finally{
			DataSourceUtils.commitAndRelease();
		}
		
	}



	public void updateOrderAddr(Order order) {
		ProductDao dao = new ProductDao();
		try {
			dao.updateOrderAddr(order);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	public void updateOrderState(String r6_Order) {
		ProductDao dao = new ProductDao();
		try {
			dao.updateOrderState(r6_Order);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	public List<Order> finaAllOrders(String uid) {
		ProductDao dao = new ProductDao();
		List<Order> orderList =null;
		try {
			orderList = dao.findAllOrders(uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderList;
	}



	public List<Map<String, Object>> findAllOrderItem(String oid) {
		ProductDao dao = new ProductDao();
		List<Map<String, Object>> orderItemList =null;
		try {
			orderItemList = dao.findAllOrderItem(oid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderItemList;
	}



	public void updateProduct(Product product) {
		AdmainProcuctDao dao = new AdmainProcuctDao();
		try {
			dao.updateProduct(product);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
