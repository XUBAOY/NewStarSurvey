package edu.bdu.controller;

import edu.bdu.dao.*;
import edu.bdu.entity.*;
import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * ����ʱ�䣺2011��8��23��
 * @author lenov
 *
 */

public class SurveyVisitAction extends HttpServlet {
	
	private SurveyDaoImpl surveyOp;//�����ʾ������
	
	private ItemDaoImpl itemOp;//�����������
	
	private RadioItemDaoImpl radioOp;//��ѡ�������
	
	private CheckboxItemDaoImpl checkOp;//��ѡ�������
	
	private TextItemDaoImpl textOp;//�����������
	
	private VisitorDaoImpl visitorOp;//�����߲�����
   
	
    /**
     * 
     */
	public SurveyVisitAction() {
		super();
	}
    /**
     * 
     */
	public void destroy() {
		super.destroy();
	}

	/**
	 * 
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        doPost(request ,response);//�����ʷ�ʽΪdoGet��������doPost����
	}

	/**
	 * 
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		
		if(request.getParameter("surveyid") != null)
		{
			int surveyid = Integer.parseInt(request.getParameter("surveyid"));//��õ����ʾ�ı��
			
			List items = itemOp.getItemList(surveyid);//���ݵ����ʾ�ı�Ų�ѯ�������б�
			
			String visitorData = "";//�����ߴ�
			
			boolean flag = false;//û�е���������������ǲ��Ǳ�������
			
			for(int i = 0; i < items.size(); i++)
			{
				Item item = (Item)items.get(i);//��õ�����
				
				switch(item.getItemType())
				{
				  case 0:
					  String radioStr = "radio" + item.getItemID();//��ѡ���õ��������ַ���
					  
					  if(request.getParameter(radioStr) != null)
					  {
						  int radioid = Integer.parseInt(request.getParameter(radioStr));//���ѡ�������
						  
						  RadioItem radio = radioOp.getRadio(radioid);//��ô���
						  
						  if(radio != null)
						  {
							 int count = radio.getSelectCount();//���ѡȡ�Ĵ���
							 
							 count = count + 1;
							 
							 radio.setSelectCount(count);//�����µĵ������
							 
							 
							 if(radioOp.updateRadioItem(radio))
						     {
								 String radioV = item.getItemID() + "#" + radio.getRadioID()+ "###";//��ô���
								 
								 visitorData += radioV;//���浽��ʱ�ַ�����
								 
								 flag = true;//�����������
						     }
						  }
					  }
					 
					  break;
					
				  case 1:
					  
					  String checkStr = "checkbox" + item.getItemID();//��ѡ���õ��������ַ���
					  
					  if(request.getParameterValues(checkStr) != null)
					  {
						  String[] checkItems = request.getParameterValues(checkStr);
						  
						 for(int ci = 0; ci < checkItems.length; ci++)
						 {
						  int checkid = Integer.parseInt(checkItems[ci]);//���ѡ�������
						  
						  CheckboxItem checkbox = checkOp.getCheckbox(checkid);//��ô���
						  
						  if(checkbox != null)
						  {
							 int count = checkbox.getSelectCount();//���ѡȡ�Ĵ���
							 
							 count = count + 1;
							 
							 checkbox.setSelectCount(count);//�����µĵ������
							 
							 
							 if(checkOp.updateCheckboxItem(checkbox))
						     {
								 String checkV = item.getItemID() + "#" + checkbox.getCheckboxID()+ "###";//��ô���
								 
								 visitorData += checkV;//���浽��ʱ�ַ�����
								 
								 flag = true;//�����������
						     }
						  }
						 }
					  }
					 
					  break;
					  
					  
				  case 2:
					  
                      String textStr = "text" + item.getItemID();//�������õ��������ַ���
					  
					  if(request.getParameter(textStr) != null)
					  {
						String textContent = request.getParameter(textStr);//��ûش�����
						
						TextItem text = new TextItem();//ʵ�������ִ���
						
						text.setTextCaption("�����߻ش�����");//���������Ĭ��Ϊ��
						
						text.setTextOwnerID(item.getItemID());//���õ�������
						
						text.setTextContent(textContent);//���ûش��ߵ�����
						
						if(textOp.InsertTextItem(text))
						 {
							 flag = true;//�����������
						 }
					  }
					  
					  break;
				}
			}
			
			if(flag)
			{
				Visitor visitor = new Visitor();//ʵ����������
				
				String ipStr = request.getRemoteAddr();//��÷�����ip��ַ
				
				visitor.setVisitorIP(ipStr);//����ip�ַ�����ַ
				
				visitor.setVisiteSurveyID(surveyid);//���õ����ʾ����
				
				visitor.setVisiteSurveyData(visitorData);//���÷����߻ش�ѡ����ѡ���
				
				if(visitorOp.InsertVisitor(visitor))
				{
					//ServletContext sc = getServletContext();//��õ�ǰservlet������
					
					//RequestDispatcher rd = sc.getRequestDispatcher("/surveyList.jsp"); //�����ҳ��
					
					response.sendRedirect("showresult.jsp?surveyid=" + surveyid);
					
					
				}
				else
				{
					out.println("�����ύ�������쳣�����飡");
				}
				
				
			}
			else
			{
				out.println("�����ύ�������⣬���飡");
			}
		}
		else
		{
		   out.println("�������ݳ��ִ������飡");
		}

		
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * 
	 */
	public void init() throws ServletException {
		
		this.surveyOp = new SurveyDaoImpl();//ʵ���������ʾ�
		
		this.itemOp = new ItemDaoImpl();//ʵ���������������
		
		this.radioOp = new RadioItemDaoImpl();//ʵ������ѡ�������
		
		this.checkOp = new CheckboxItemDaoImpl();//ʵ������ѡ�������
		
		this.textOp = new TextItemDaoImpl();//ʵ���������������
		
		this.visitorOp = new VisitorDaoImpl();//ʵ���������߲�����
		
	}

}
