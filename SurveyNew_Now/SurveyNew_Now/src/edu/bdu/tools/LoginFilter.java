package edu.bdu.tools;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * ����ʱ�䣺2011��8��24��
 * @author xin lei hu
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
		
		HttpServletResponse response = (HttpServletResponse)sResponse;//�����Ӧ����
		
		HttpSession session = request.getSession();
		
		Object user = session.getAttribute("user");//��õ�ǰ�û���Ϣ
		
		if(user == null && !(user instanceof edu.bdu.entity.User))
		{
			
	       response.sendRedirect("../login.jsp");//�����ǰsession��������ת����½ҳ
         
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
