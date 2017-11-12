package web.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.jrockit.jfr.Producer;

import domain.Product;
import web.service.ProductService;

/**
 * Servlet implementation class FindProductByPidServlet
 */
@WebServlet("/findProductByPid")
public class FindProductByPidServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindProductByPidServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductService productService = new ProductService();
		String pid = request.getParameter("pid");
		String currentPage = request.getParameter("currentPage");
		String cid = request.getParameter("cid");
		Product product = productService.findProductByPid(pid);
		request.setAttribute("product", product);
		request.setAttribute("cid", cid);
		request.setAttribute("currentPage", currentPage);
		//��ÿͻ���Я��cookie---���������pids��cookie
		String pids = pid;
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
			for(Cookie cookie : cookies){
				if("pids".equals(cookie.getName())){
					pids = cookie.getValue();
					//1-3-2 ���η�����Ʒpid��8----->8-1-3-2
					//1-3-2 ���η�����Ʒpid��3----->3-1-2
					//1-3-2 ���η�����Ʒpid��2----->2-1-3
					//��pids���һ������
					String[] split = pids.split("-");//{3,1,2}
					List<String> asList = Arrays.asList(split);//[3,1,2]
					LinkedList<String> list = new LinkedList<String>(asList);//[3,1,2]
					//�жϼ������Ƿ���ڵ�ǰpid
					if(list.contains(pid)){
						//������ǰ�鿴��Ʒ��pid
						list.remove(pid);
						list.addFirst(pid);
					}else{
						//��������ǰ�鿴��Ʒ��pid ֱ�ӽ���pid�ŵ�ͷ��
						list.addFirst(pid);
					}
					//��[3,1,2]ת��3-1-2�ַ���
					StringBuffer sb = new StringBuffer();
					for(int i=0;i<list.size()&&i<7;i++){
						sb.append(list.get(i));
						sb.append("-");//3-1-2-
					}
					//ȥ��3-1-2-���-
					pids = sb.substring(0, sb.length()-1);
				}
			}
		}
		

		Cookie cookie_pids = new Cookie("pids",pids);
		response.addCookie(cookie_pids);
		
		request.getRequestDispatcher("/product_info.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
