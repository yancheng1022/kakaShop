package web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import domain.User;
import utils.MailUtils;
import web.service.UserService;


@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		Map<String, String[]> properties = request.getParameterMap();
		User user = new User();
		
			try {
				BeanUtils.populate(user, properties);
			} catch (IllegalAccessException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			//ע��ҳ��û�еĵ���д
			user.setUid(utils.CommonUtils.getUUID());
			user.setTelephone(null);
			user.setState(0);
			String activeCode = utils.CommonUtils.getUUID();
			user.setCode(activeCode);
			
			//��user���ݸ�service��
			UserService service = new UserService();
			boolean isSuccess = service.register(user);
			
			//�ж��Ƿ�ע��ɹ�
			if(isSuccess){
				String emailMsg = "��ϲ����ע��ɹ����������������֤����"+"<a href='http://localhost:8080/kakaShop/active?activeCode="+activeCode+"'>"
						+"http://localhost:8080/kakaShop/active?activeCode="+activeCode+"</a>";
				try {
					MailUtils.sendMail(user.getEmail(), emailMsg);
					
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				response.sendRedirect(request.getContextPath()+"/registerSuccess.jsp");
			}else {
				response.sendRedirect(request.getContextPath()+"/registerFail.jsp");
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
