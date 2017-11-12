package admainWeb.admainService;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import admainDao.AdmainProcuctDao;
import domain.Category;
import domain.Product;
import utils.CommonUtils;
import utils.DataSourceUtils;

public class AdmainProduceService {

	public List<Product> findAllProduct() {
		AdmainProcuctDao dao = new AdmainProcuctDao();
		List<Product> products =null;
		try {
			products = dao.findAllProduct();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}

	public List<Category> findCatalog() {
		AdmainProcuctDao dao = new AdmainProcuctDao();
		List<Category> categorys = null;
		try {
			categorys =  dao.findCatalog();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categorys;
	}

	public void addProduct(Product product) {
		AdmainProcuctDao dao = new AdmainProcuctDao();
		product.setPid(CommonUtils.getUUID());
		product.setPimage("products/1/c_0001.jpg");
		product.setPdate(new Date());
		product.setPdesc("aaa");
		
		try {
			dao.addProduct(product);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void delProduct(String pid) {
		AdmainProcuctDao dao = new AdmainProcuctDao();
		try {
			dao.delProduct(pid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public Product edit(String pid) {
		AdmainProcuctDao dao = new AdmainProcuctDao();
		Product product =null;
		try {
			product = dao.edit(pid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return product;
	}

	public List<Product> queryProduct(String name) {
		AdmainProcuctDao dao = new AdmainProcuctDao();
		List<Product> productList = null;
		try {
			productList = dao.queryProduct(name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return productList;
	}

}
