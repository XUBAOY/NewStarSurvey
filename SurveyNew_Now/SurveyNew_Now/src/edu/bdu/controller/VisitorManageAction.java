package edu.bdu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.bdu.dao.UserDaoImpl;
import edu.bdu.dao.VisitorDaoImpl;
import edu.bdu.entity.User;
import edu.bdu.entity.Visitor;
import edu.bdu.dao.SurveyDaoImpl;
import edu.bdu.entity.Survey;

public class VisitorManageAction extends HttpServlet {

	 /**
     * ��ʼ��
     */
	private  VisitorDaoImpl visitorOp;//�����߲�����
	
	private int visitorCount = 0;//��÷���������
	
	private int pageSize = 15;//һҳ��ʾ10�������ʾ�
	
	private int pageFirstIndex = 0;//Ĭ�ϵ�ǰ��һ�������ʾ�
	
	private int currentPageNum = 1;//Ĭ����ʾ��һҳ
	
	private int pageCount = 0;//�����ʾ���ҳ��
	
	private int pageListSize = 7;//7ҳһ���˵���
	
	private int pageListIndex = 1;//Ĭ����ʾ�ĵ�һ��ҳ��
	
	private int currentPageListNum = 1;//Ĭ����ʾ��һ���˵���
	
	private int pageListCount = 0;//�ܲ˵�������
	
	private int textPageSize = 5;//Ĭ���ı�����ĳ���
	
	private int searchPageSize = 10;//һҳ��ʾʮ��
	
	private int visitDataSize = 18;//������ʾ������ַ���
	
	private int titleSize = 13;//������ʾ������ַ���
	
	private int nameSize = 9;//�û�����ʾ������ַ���

	public VisitorManageAction() {
		super();
	}

	public void destroy() {
		super.destroy(); 
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        doPost(request,response);//����doPost�������д���

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");//���ñ����ʽ
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		int commType = Integer.parseInt(request.getParameter("comType"));//��ò���������
		
		switch(commType)
		{
		case 1:
			
			int pageNum = Integer.parseInt(request.getParameter("pageNum"));//��õ�ǰ����ҳ��
			
			int pageListNum = Integer.parseInt(request.getParameter("pageListNum"));//��õ�ǰ����˵���
			
	        initParameters(pageNum,pageListNum);//�����ʾ�ҳ�Ͳ˵�ҳ��Ϣ��ʼ��
			
			out.println(getPageByNum(pageNum,pageListNum));//��õ����ʾ��б�
			
			out.println(getPageBottomByNum(pageNum,pageListNum));//��ʾ�ײ��ı���
	        
			break;
		case 2:
			
			out.print(getVisitorCount());//�����ͨ����Ա����
			
			break;
		case 3:
				
			String condition = request.getParameter("searchCondition");//�����������
			
			if(!condition.equals(""))
			{
				int pageNum1 = Integer.parseInt(request.getParameter("pageNum"));//��õ�ǰ����ҳ
				
				out.println(getVisitorListByCondition(condition,pageNum1));//����������
			}
			else
			{
				out.println("<div><a href='surveyList.jsp'>��������������Ϊ��!</a></div>");//����������				
			}
			
			break;
		case 4:
			
			String visitorNum = request.getParameter("visitorNumber");//�����������
			
			int visitorNumber = Integer.parseInt(visitorNum);//��÷����߱��
			
	        VisitorDaoImpl visitorOp = new VisitorDaoImpl();//�����߲�����
	           
	        if(visitorOp.deleteVisitor(visitorNumber))//ɾ����������Ϣ
	        {
	           out.println("<script type='text/javascript'>alert('��������Ϣ�ѳɹ�ɾ��!');getVisitorList();getVisitorCount();</script>");
	        }
	        else
	        {
	           out.println("<script type='text/javascript'>alert('��������Ϣ�����쳣û�ܳɹ�ɾ��!');</script>");
	         }
			
			break;
			
		case 5:

			
			break;
			
		default:
			break;
		}

		out.flush();
		out.close();
	}

	//���ݵ�ǰҳ��Ͳ˵�����ȷ����ʾҪ��ʾ�ķ�����
	private String getPageByNum(int pageNum,int pageListNum)
	{
		String results = "";//���÷��ʽ��
		
        initParameters(pageNum,pageListNum);//�����ߺͲ˵�ҳ��Ϣ��ʼ��
		
		this.visitorOp = new VisitorDaoImpl();//ʵ���������߲�����
		
		List visitorList = this.visitorOp.getVisitorList();//��÷������б�
		
		int listNum = this.pageSize;//Ĭ����ʾ�б�����
		
		int lastNum = this.visitorCount % this.pageSize;//������һҳ�����߸���
		
		if(this.pageCount == this.currentPageNum)
		{
		   if(lastNum < listNum)
		   {
			   listNum = lastNum;//�����б�Ϊ��ǰҳ�ķ����߸���
		   }
		}
		
		//�����������պ���ÿҳ����������������
		if(listNum == 0 && this.visitorCount != 0)
		{
			listNum = this.pageSize;//��ʾ�б�����
		}
		
		if(this.visitorCount == 0)
		{
			listNum = 0;
		}
		
		//�����ʾ����
	     results +="<div style='margin-top:10px;margin-bottom:10px;'><table border='0' cellpadding='0' cellspacing='0' class='tableClass'>";
	     results +="<tr>";
	     results +="<td align='center' class='tdHeader'>�����߱��</td>";
	     results +="<td align='center' class='tdHeader'>������ip��ַ</td>";
	     results +="<td align='center' class='tdHeader'>���ʵĵ����ʾ�</td>";
	     results +="<td align='center' class='tdHeader'>�����߻ش�����</td>";
	     results +="<td align='center' class='tdHeader'>����ʱ���¼</td>";
	     results +="<td align='center' class='tdHeader'>����</td>";
	     results +="</tr>";
	     
	     SurveyDaoImpl surveyOp = new SurveyDaoImpl();//�½������ʾ������
	     
		for(int i = this.pageFirstIndex; i < this.pageFirstIndex + listNum; i++)
		{
	          Visitor visitor = (Visitor)visitorList.get(i);//��÷�������Ϣ 
	          
	          results += "<tr class='trContent'>";
	          
	          results += "<td class='tdContent' align='center'>" + visitor.getVisitorNumber() + "</td>";//�����߱��
	          results += "<td class='tdContent' align='center'>" + visitor.getVisitorIP() + "</td>";//������ip��ַ
	          
	          String surveyTitle = surveyOp.getSurvey(visitor.getVisiteSurveyID()).getSurveyTitle();
	          surveyTitle = surveyTitle.length()<= titleSize?surveyTitle:surveyTitle.substring(0,titleSize);
	          results += "<td class='tdContent' align='center'>" + surveyTitle + "</td>";//���ʵĵ����ʾ���
	          
	          String visitorData =  visitor.getVisiteSurveyData();//��ֹ���ֿմ�
	          
	          if(visitorData == null || visitorData.equals(""))
	          {
	            visitorData = "&nbsp;&nbsp;";
	          }
	          
	          visitorData =  visitorData.length() <= visitDataSize?visitorData:visitorData.substring(0,visitDataSize);
	          
	          
	          results += "<td class='tdContent' align='center'>" + visitorData + "</td>";//�����߻ش����Ϣ
	          results += "<td class='tdContent' align='center'>" + visitor.getVisiteDateTime().toString() + "</td>";//�����߷���ʱ��
	          
	          results += "<td class='tdContent' align='center'><a href='javascript:deleteVisitorDataById(" + visitor.getVisitorNumber() + ")'>ɾ��</a></td>";
	          
	          results += "<tr>";
		       
		}
		
	    results +="</table></div>";
		
		results += "<div>������������" + this.visitorCount + " ��������ҳ����" + this.pageCount + " �������ܲ˵�������" + this.pageListCount + "</div>";
		
		return results;
	}

   	//���ݵ�ǰҳ��Ͳ˵���ȷ��Ҫ��ʾ�Ĳ˵�
	private String getPageBottomByNum(int pageNum,int pageListNum)
	{
		
		
		String results = "<div style='height:27px;width:348px;display:inline-block;'>";//������
		
		if(this.currentPageListNum == 1)
		{
			results += "<div style='margin-left:2px;margin-right:2px;height:26px;float:left;display:inline-block;'><img src='../image/uppage1.png'></div>";
		}
		else
		{
	        int listNum1 = pageListNum - 1 < 1?1:pageListNum - 1;//���ҳ��
	        
	        int pageNum1 = (listNum1 - 1) * this.pageListSize + 1;//��ò˵���ʾ��ʼҳ
			
			results += "<div style='cursor:pointer;margin-left:2px;margin-right:2px;height:26px;float:left;display:inline-block;'><img src='../image/uppage.png' onclick='showBtn(" + pageNum1 + "," + listNum1 + ")'></div>";
		}
		
		for(int i = this.pageListIndex; i < this.pageListIndex + this.pageListSize; i++)
		{
			//���ҳ���ǵ�ǰҳ���������һҳ���ɫ��ʾ
			if(i == this.currentPageNum || i > this.pageCount)
			{
				  results += "<div style='margin-left:2px;margin-right:2px;background:url(../image/numpage.png);line-height:26px;height:26px;width:26px;color:#CCCCCC;float:left;display:inline-block;'>" + i + "</div>";
			}
			else
			{
			      results += "<div style='margin-left:2px;margin-right:2px;background:url(../image/numpage.png);line-height:26px;height:26px;width:26px;float:left;display:inline-block;'><a href='javascript:showBtn(" + i + ", " + this.currentPageListNum + ")'>" + i + "</a></div>";
			}
		}
		
		if(pageListNum == this.pageListCount || this.pageListCount == 0)
		{
			results += "<div style='margin-left:2px;margin-right:2px;height:26px;float:left;display:inline-block;'><img src='../image/downpage1.png'></div>";			
		}
		else
		{
	        int listNum2 = pageListNum + 1 > this.pageListCount?this.pageListCount:pageListNum + 1;//���ҳ��
	        
	        int pageNum2 = (listNum2 - 1) * this.pageListSize + 1;//��ò˵���ʾ��ʼҳ
			
			results += "<div style='cursor:pointer;margin-left:2px;margin-right:2px;height:26px;float:left;display:inline-block;'><img src='../image/downpage.png'  onclick='showBtn(" + pageNum2 + "," + listNum2 + ")'></div>";			
		}
		
		results += "</div>";
		
		return results;
	}
	
    //��÷���������
	private int getVisitorCount()
	{
		this.visitorOp = new VisitorDaoImpl();
		
		this.visitorCount = visitorOp.getVisitorList().size();//����û��б�
		
		return this.visitorCount;//���ط���������
	}
	
    //��÷�������ҳ��
	private int getPageCount()
	{
		getVisitorCount();//��õ�ǰ����������
		
		this.pageCount = (int)Math.ceil(this.visitorCount / (double)this.pageSize);//��õ�ǰ��ҳ��
		
		return this.pageCount;//���ص�ǰ��ҳ��
	}
	
	//��÷������ܲ˵���
	private int getPageListCount()
	{
		
		getPageCount();//��õ�ǰ��������ҳ��
		
		this.pageListCount = (int)Math.ceil(this.pageCount / (double)this.pageListSize);//��ò˵�������
		
		return this.pageListCount;//���ص�ǰ�ܲ˵���
	}
	
    //���������ʼ��
	private void initParameters(int pageNum,int pageListNum)
	{
		getPageListCount();//��ʼ��userCount��pageCount��pageListCount�������
		
		this.currentPageNum = pageNum;//���õ�ǰ��ʾҳ��
		
		this.currentPageListNum = pageListNum;//���õ�ǰ��ʾ�˵���
		
		this.pageFirstIndex = (this.currentPageNum - 1) * this.pageSize;//��õ�ǰҳ���еĵ�һ�������ʾ��±�
			
		this.pageListIndex = (this.currentPageListNum - 1) * this.pageListSize + 1;//��õ�ǰ�˵�����ʾ��ҳ��

	}
	
	
	//���ݱ���������������Ϣ
	private String getVisitorListByCondition(String condition,int pageNum1)
	{
		String results = "";//���÷��ʽ��
		
		this.visitorOp = new VisitorDaoImpl();//ʵ���������߲�����
		
		SurveyDaoImpl surveyOp = new SurveyDaoImpl();//ʵ���������ʾ������
		
		Survey conditionS = surveyOp.getServeyByTitle(condition);//ʵ�������������ʾ�
		
		if(conditionS == null)
		{
		  results = "<div>�����ڴ˵����ʾ�!</div>";
		  
		  return results;//���ص����ʾ���
		}
		
		List visitorList = visitorOp.getVisitorList(conditionS.getSurveyID());//��õ����ʾ��б�
		
		int searchPageCount = (int)Math.ceil(visitorList.size() / (double)this.searchPageSize);//��õ�ǰ��ҳ��
		
		int searchPageFirst = (pageNum1 - 1) * this.searchPageSize;//Ĭ����ʾҳ��ǰ�ĵ�һ�������±�
		
		int listSize = this.searchPageSize;//Ĭ����ʾһҳ����
		
		
		if(pageNum1 == searchPageCount)
		{
			int remainNum = visitorList.size() % this.searchPageSize;//������ҳ��������
			
			listSize = remainNum < this.searchPageSize ? remainNum:this.searchPageSize;
		}
		
		if(listSize == 0 && visitorList.size() != 0)
		{
			listSize = this.searchPageSize;//���һҳ�պ���ҳʱ����
		}
		
		if(visitorList.size() == 0)
		{
			results += "<div>��ǰϵͳû��������ҵĵ����ʾ�!</div>";
		}
		else
		{
			results += visitorList.size() + "*";//��¼�����߸���
			//�����ʾ����
		     results +="<div style='margin-top:10px;margin-bottom:10px;'><table border='0' cellpadding='0' cellspacing='0' class='tableClass'>";
		     results +="<tr>";
		     results +="<td align='center' class='tdHeader'>�����߱��</td>";
		     results +="<td align='center' class='tdHeader'>������ip��ַ</td>";
		     results +="<td align='center' class='tdHeader'>���ʵĵ����ʾ�</td>";
		     results +="<td align='center' class='tdHeader'>�����߻ش�����</td>";
		     results +="<td align='center' class='tdHeader'>����ʱ���¼</td>";
		     results +="<td align='center' class='tdHeader'>����</td>";
		     results +="</tr>";
		     
			for(int i = searchPageFirst; i < searchPageFirst + listSize; i++)
			{
		          Visitor visitor = (Visitor)visitorList.get(i);//��÷�������Ϣ 
		          
		          results += "<tr class='trContent'>";
		          
		          results += "<td class='tdContent' align='center'>" + visitor.getVisitorNumber() + "</td>";//�����߱��
		          results += "<td class='tdContent' align='center'>" + visitor.getVisitorIP() + "</td>";//������ip��ַ
		          
		          String surveyTitle = surveyOp.getSurvey(visitor.getVisiteSurveyID()).getSurveyTitle();
		          surveyTitle = surveyTitle.length()<= titleSize?surveyTitle:surveyTitle.substring(0,titleSize);
		          results += "<td class='tdContent' align='center'>" + surveyTitle + "</td>";//���ʵĵ����ʾ���
		          
		          String visitorData =  visitor.getVisiteSurveyData();//��ֹ���ֿմ�
		          
		          if(visitorData == null || visitorData == "")
		          {
		            visitorData = " ";
		          }
		          
		          visitorData =  visitorData.length() <= visitDataSize?visitorData:visitorData.substring(0,visitDataSize);
		          
		          
		          results += "<td class='tdContent' align='center'>" + visitorData + "</td>";//�����߻ش����Ϣ
		          results += "<td class='tdContent' align='center'>" + visitor.getVisiteDateTime().toString() + "</td>";//�����߷���ʱ��
		          
		          results += "<td class='tdContent' align='center'><a href='javascript:deleteVisitorDataById(" + visitor.getVisitorNumber() + ")'>ɾ��</a></td>";
		          
		          results += "<tr>";
			}
			
		    results +="</table></div>";
			
			results += "<div style='width:540px;margin-top:10px;margin-bottom:10px;'>";
			
	        //��ʾ��һҳ��ť
			if(pageNum1 == 1)
			{
				results += "<div style='margin-left:2px;margin-top:10px;margin-bottom:10px;margin-right:2px;height:26px;float:left;display:inline-block;'><img src='../image/uppage1.png'></div>";
			}
			else
			{
		        
		        int pageNum2 = pageNum1 - 1;//��ò˵���ʾ��ʼҳ
				
				results += "<div style='margin-left:2px;margin-top:10px;margin-bottom:10px;margin-right:2px;height:26px;float:left;display:inline-block;cursor:pointer;'><img src='../image/uppage.png' onclick=\"getSearchList(\'" + condition + "\'," + pageNum2 + ")\"></div>";
			}
			
			//��ʾ��һҳ��ť
			if(pageNum1 == searchPageCount)
			{
				results += "<div style='margin-left:2px;margin-top:10px;margin-bottom:10px;margin-right:2px;height:26px;float:left;display:inline-block;'><img src='../image/downpage1.png'></div>";			
			}
			else
			{
		        
		        int pageNum3 = pageNum1 + 1;//��ò˵���ʾ��ʼҳ
				
				results += "<div style='margin-left:2px;margin-top:10px;margin-bottom:10px;margin-right:2px;height:26px;float:left;display:inline-block;cursor:pointer;'><img src='../image/downpage.png' onclick=\"getSearchList(\'" + condition + "\'," + pageNum3 + ")\"></div>";
			}
			
			results += "</div>";//���·�ҳ����
		}
		
		return results;//���ط������б�
	}

	public void init() throws ServletException {
		initParameters(1,1);//��ʼ��������ҳ�Ͳ˵�ҳ
	}

}
