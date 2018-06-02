package edu.bdu.tools;

import javax.servlet.Filter;
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
import edu.bdu.dao.UserDaoImpl;
import edu.bdu.entity.User;

public class OperatorFilter implements Filter {
	
	private FilterConfig fc;
	
    UserDaoImpl userOper;//����û����ݿ���
    /**
     * �����û��������жϷ������Ƿ��ǵ����ʾ�Ĺ�����
     */
	public void doFilter(ServletRequest sRequest, ServletResponse sResponse, FilterChain chain)
	throws IOException,ServletException{
	   
		HttpServletRequest request = (HttpServletRequest)sRequest;//����û�������Ϣ
		
		HttpServletResponse response = (HttpServletResponse)sResponse;//�����Ӧ����
		
		userOper = new UserDaoImpl();//ʵ�����û�������
		
		
		HttpSession session = request.getSession();
		
		Object user = session.getAttribute("user");//��õ�ǰ�û���Ϣ
		
		if(user == null && !(user instanceof edu.bdu.entity.User))
		{
			PrintWriter out1 = response.getWriter();//���response�ı������
			
			out1.print("usererror");//��ǰ�û���ݲ��Ϸ�
		}
		else
		{
		  User userCheck = (User)user;//���û�ת�����û����� 
		  
		  if(userOper.loginCheck(userCheck) != null && userCheck.getUserType() == 1)
		  {
			chain.doFilter(sRequest, sResponse);//�����֤�ɹ������ǳ�������Ա��ݺ󴫵��û�����
		  }
		  else
		  {
		    PrintWriter out2 = response.getWriter();//���response�ı������ 
		    
		    out2.print("usererror");//�û������֤����
		  }
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
