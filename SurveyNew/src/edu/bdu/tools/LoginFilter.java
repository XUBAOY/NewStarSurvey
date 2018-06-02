package edu.bdu.tools;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * ����ʱ�䣺2011��8��24��
 * @author lenov
 *
 */
public class LoginFilter implements Filter{
	
	private FilterConfig fc;
    /**
     * �����û��������жϷ������Ƿ��ǵ����ʾ�Ĺ�����
     */
	public void doFilter(ServletRequest sRequest, ServletResponse sResponse, FilterChain chain)
	throws IOException,ServletException{
	
		HttpServletRequest request = (HttpServletRequest)sRequest;//����û�������Ϣ
		
		HttpSession session = request.getSession();
		
		Object user = session.getAttribute("user");//��õ�ǰ�û���Ϣ
		
		if(user == null && !(user instanceof edu.bdu.entity.User))
		{
			RequestDispatcher rd = request.getRequestDispatcher("../login.jsp");//�����½ҳ
		    
			rd.forward(request,sResponse);//���󴫵�
		}
		else
		{
			chain.doFilter(sRequest, sResponse);//�����û�����
		}
	}
	
	/**
	 * ��ʼ��
	 */
	public void init(FilterConfig fc) throws ServletException{
		this.fc = fc;
	}
	/**
	 * ����
	 */
	public void destroy(){
		this.fc = null;
	}
	
}
