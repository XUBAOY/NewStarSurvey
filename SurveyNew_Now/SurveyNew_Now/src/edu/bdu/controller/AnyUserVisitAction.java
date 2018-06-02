package edu.bdu.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import edu.bdu.dao.SurveyDaoImpl;//��������ʾ������
import edu.bdu.entity.Survey;//��������ʾ�ʵ����
import edu.bdu.entity.User;//�����û���
import edu.bdu.dao.UserDaoImpl;//�����û�������
import edu.bdu.dao.TextItemDaoImpl;//�����ı��ش��������
import edu.bdu.entity.TextItem;//�����ı�ʵ����

public class AnyUserVisitAction extends HttpServlet {

    /**
     * ��ʼ��
     */
	private SurveyDaoImpl surveyOp;//�����ʾ������
	
	private int surveyCount = 0;//��õ����ʾ�����
	
	private int pageSize = 10;//һҳ��ʾ10�������ʾ�
	
	private int pageFirstIndex = 0;//Ĭ�ϵ�ǰ��һ�������ʾ�
	
	private int currentPageNum = 1;//Ĭ����ʾ��һҳ
	
	private int pageCount = 0;//�����ʾ���ҳ��
	
	private int pageListSize = 7;//7ҳһ���˵���
	
	private int pageListIndex = 1;//Ĭ����ʾ�ĵ�һ��ҳ��
	
	private int currentPageListNum = 1;//Ĭ����ʾ��һ���˵���
	
	private int pageListCount = 0;//�ܲ˵�������
	
	private int textPageSize = 5;//Ĭ���ı�����ĳ���
	
	private int searchPageSize = 10;//һҳ��ʾʮ��
	
	public AnyUserVisitAction() {
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
			
	        out.println(getNewListFive());//��õ�ǰ�������������ʾ�
	        
			break;
		case 2:
			
			int pageNum = Integer.parseInt(request.getParameter("pageNum"));//��õ�ǰ����ҳ��
			
			int pageListNum = Integer.parseInt(request.getParameter("pageListNum"));//��õ�ǰ����˵���
			
	        initParameters(pageNum,pageListNum);//�����ʾ�ҳ�Ͳ˵�ҳ��Ϣ��ʼ��
			
			out.println(getPageByNum(pageNum,pageListNum));//��õ����ʾ��б�
			
			out.println(getPageBottomByNum(pageNum,pageListNum));//��ʾ�ײ��ı���
			
			break;
		case 3:
			
	        out.println(getNewListFiveSearch());//��õ�ǰ�������������ʾ�		
	        
			break;
		case 4:
			
			String condition = request.getParameter("searchCondition");//�����������
			
			if(!condition.equals(""))
			{
				int pageNum1 = Integer.parseInt(request.getParameter("pageNum"));//��õ�ǰ����ҳ
				
				out.println(getSurveyListByCondition(condition,pageNum1));//����������
			}
			else
			{
				out.println("<div><a href='surveyList.jsp'>��������������Ϊ��!</a></div>");//����������				
			}
			
			break;
			
		case 5:
			int itemId = Integer.parseInt(request.getParameter("itemid"));//����ı�������
			
			int tPageNum = 1;//����ı�������ʾҳ��
			
			if(request.getParameter("pageNum")!=null)
			{
				tPageNum = Integer.parseInt(request.getParameter("pageNum"));//��õ�ǰҪ��ʾ��ҳ��
				
			}
			
			out.println(getTextList(itemId,tPageNum));//�����ı����б�
			
			break;
			
		default:
			break;
		}
		
		out.flush();
		out.close();
	}
	//����ı�������б�
	private String getTextList(int itemId,int pageNum)
	{
		String results = "";//�ı������б�
		
        TextItemDaoImpl textOp = new TextItemDaoImpl();//���ִ��������
		
        List textList = textOp.getTextListByOwner(itemId);//���ݵ������Ų�ѯ���ִ����б�
        
		int textPageCount = (int)Math.ceil(textList.size() / (double)this.textPageSize);//��õ�ǰ��ҳ��
		
		int textPageFirst = (pageNum - 1) * this.textPageSize;//Ĭ����ʾҳ��ǰ�ĵ�һ�������±�
		
		int listSize = this.textPageSize;//Ĭ����ʾһҳ����
		
        if(textList.size() == 0)
        {
          results += "<div style='width:790px;'>��ǰ��û���˻ش�˵����</div>";
        }
        
		if(pageNum == textPageCount)
		{
			int remainNum = textList.size() % this.textPageSize;//������ҳ��������
			
			listSize = remainNum < this.textPageSize ? remainNum:this.textPageSize;
		}
		
		if(listSize == 0 && textList.size() != 0)
		{
			listSize = this.textPageSize;//���һҳ�պ���ҳʱ����
		}
		
		if(textList.size()== 0)
		{
			listSize = 0;//�����������ڻش��б�
		}
            
        for(int i = textPageFirst; i < textPageFirst + listSize; i++)
        {
           TextItem text = (TextItem)textList.get(i);//���������ʵ��
                
           results += "<div  style='width:800px;line-height:28px;border-bottom:1px solid #CCCC00;padding-left:12px;'>" + getNewString(text.getTextContent(),200) + "</div>";  
        }
        
        //��ʾ��һҳ��ť
		if(pageNum == 1)
		{
			results += "<div style='margin-left:2px;margin-right:2px;height:26px;float:left;display:inline-block;'><img src='image/uppage1.png'></div>";
		}
		else
		{
	        
	        int pageNum1 = pageNum - 1;//��ò˵���ʾ��ʼҳ
			
			results += "<div style='margin-left:2px;margin-right:2px;height:26px;float:left;display:inline-block;cursor:pointer;'><img src='image/uppage.png' onclick='getTextList(" + itemId + "," + pageNum1 + ")'></div>";
		}
		
		//��ʾ��һҳ��ť
		if(pageNum == textPageCount || textPageCount == 0)
		{
			results += "<div style='margin-left:2px;margin-right:2px;height:26px;float:left;display:inline-block;'><img src='image/downpage1.png'></div>";			
		}
		else
		{
	        
	        int pageNum2 = pageNum + 1;//��ò˵���ʾ��ʼҳ
			
			results += "<div style='margin-left:2px;margin-right:2px;height:26px;float:left;display:inline-block;cursor:pointer;'><img src='image/downpage.png' onclick='getTextList(" + itemId + "," + pageNum2 + ")'></div>";
		}
		
		//��ʾ�رհ�ť
		results += "<div style='line-height:26px;color:red;text-decoration:none;'><a href='javascript:textListHidden()'>[�ر�]</a></div>";//�رյ�ǰ��
		
		return results;
	}
	//���ݱ������������ʾ�
	private String getSurveyListByCondition(String condition,int pageNum1)
	{
		String results = "";//���÷��ʽ��
		
		this.surveyOp = new SurveyDaoImpl();//ʵ���������ʾ������
		
		Survey conditionS = new Survey();//ʵ�������������ʾ�
		
		conditionS.setSurveyTitle(condition);//���õ����ʾ����
		
		List surveyList = this.surveyOp.getServeyList(conditionS);//��õ����ʾ��б�
		
		int searchPageCount = (int)Math.ceil(surveyList.size() / (double)this.searchPageSize);//��õ�ǰ��ҳ��
		
		int searchPageFirst = (pageNum1 - 1) * this.searchPageSize;//Ĭ����ʾҳ��ǰ�ĵ�һ�������±�
		
		int listSize = this.searchPageSize;//Ĭ����ʾһҳ����
		
		
		if(pageNum1 == searchPageCount)
		{
			int remainNum = surveyList.size() % this.searchPageSize;//������ҳ��������
			
			listSize = remainNum < this.searchPageSize ? remainNum:this.searchPageSize;
		}
		
		if(listSize == 0 && surveyList.size() != 0)
		{
			listSize = this.searchPageSize;//���һҳ�պ���ҳʱ����
		}
		
		if(surveyList.size() == 0)
		{
			results += "<div>��ǰϵͳû��������ҵĵ����ʾ�!</div>";
		}
		else
		{
			for(int i = searchPageFirst; i < searchPageFirst + listSize; i++)
			{
				Survey survey = (Survey)surveyList.get(i);//��õ����ʾ�ʵ��
				
				UserDaoImpl userOp = new UserDaoImpl();//����û�������
				
				User user = userOp.getUser(survey.getSurveyOwnerID());//��õ�ǰ�����ʾ�ķ�����
				
				results += "<div>" ;
				
				results += "<div style='width:540px;margin-top:5px;margin-bottom:10px;' align='center'><a href='showsurvey.jsp?surveyId=" + survey.getSurveyID() + "'>";
				
				results += getNewString(survey.getSurveyTitle(),70);
				
				results += "</a></div>"; 
				
				results += "<div style='width:540px;margin-top:5px;margin-bottom:10px;padding-left:15px;' align='left'>";
				
				results += getNewString(survey.getSurveyLink(),80);
				
				results += "</div>";
				
				results += "<div style='width:540px;margin-top:10px;margin-bottom:10px;padding-left:15px;padding-bottom:10px;border-bottom:1px dotted #0066CC;' align='left'>";
				
				results += "<span>" + user.getUserName()+ "</span><span style='margin-left:10px;margin-right:10px;'>������</span><span>" + survey.getSurveyCreateDate().toString() + "</span>";
				
				results += "</div>";
				
				results += "</div>";
			}
			
			
			results += "<div style='width:540px;margin-top:10px;margin-bottom:10px;'>";
			
	        //��ʾ��һҳ��ť
			if(pageNum1 == 1)
			{
				results += "<div style='margin-left:2px;margin-top:10px;margin-bottom:10px;margin-right:2px;height:26px;float:left;display:inline-block;'><img src='image/uppage1.png'></div>";
			}
			else
			{
		        
		        int pageNum2 = pageNum1 - 1;//��ò˵���ʾ��ʼҳ
				
				results += "<div style='margin-left:2px;margin-top:10px;margin-bottom:10px;margin-right:2px;height:26px;float:left;display:inline-block;cursor:pointer;'><img src='image/uppage.png' onclick=\"getSearchList(\'" + condition + "\'," + pageNum2 + ")\"></div>";
			}
			
			//��ʾ��һҳ��ť
			if(pageNum1 == searchPageCount)
			{
				results += "<div style='margin-left:2px;margin-top:10px;margin-bottom:10px;margin-right:2px;height:26px;float:left;display:inline-block;'><img src='image/downpage1.png'></div>";			
			}
			else
			{
		        
		        int pageNum3 = pageNum1 + 1;//��ò˵���ʾ��ʼҳ
				
				results += "<div style='margin-left:2px;margin-top:10px;margin-bottom:10px;margin-right:2px;height:26px;float:left;display:inline-block;cursor:pointer;'><img src='image/downpage.png' onclick=\"getSearchList(\'" + condition + "\'," + pageNum3 + ")\"></div>";
			}
			
			results += "</div>";//���·�ҳ����
		}
		
		return results;//���ص����ʾ��б�
	}
	//���ݵ�ǰҳ��Ͳ˵���ȷ��Ҫ��ʾ�Ĳ˵�
	private String getPageBottomByNum(int pageNum,int pageListNum)
	{
		
		
		String results = "<div style='height:27px;width:348px;display:inline-block;'>";//�����ʾ�
		
		if(this.currentPageListNum == 1)
		{
			results += "<div style='margin-left:2px;margin-right:2px;height:26px;float:left;display:inline-block;'><img src='image/uppage1.png'></div>";
		}
		else
		{
	        int listNum1 = pageListNum - 1 < 1?1:pageListNum - 1;//���ҳ��
	        
	        int pageNum1 = (listNum1 - 1) * this.pageListSize + 1;//��ò˵���ʾ��ʼҳ
			
			results += "<div style='cursor:pointer;margin-left:2px;margin-right:2px;height:26px;float:left;display:inline-block;'><img src='image/uppage.png' onclick='showBtn(" + pageNum1 + "," + listNum1 + ")'></div>";
		}
		
		for(int i = this.pageListIndex; i < this.pageListIndex + this.pageListSize; i++)
		{
			//���ҳ���ǵ�ǰҳ���������һҳ���ɫ��ʾ
			if(i == this.currentPageNum || i > this.pageCount)
			{
				  results += "<div style='margin-left:2px;margin-right:2px;background:url(image/numpage.png);line-height:26px;height:26px;width:26px;color:#CCCCCC;float:left;display:inline-block;'>" + i + "</div>";
			}
			else
			{
			      results += "<div style='margin-left:2px;margin-right:2px;background:url(image/numpage.png);line-height:26px;height:26px;width:26px;float:left;display:inline-block;'><a href='javascript:showBtn(" + i + ", " + this.currentPageListNum + ")'>" + i + "</a></div>";
			}
		}
		
		if(pageListNum == this.pageListCount)
		{
			results += "<div style='margin-left:2px;margin-right:2px;height:26px;float:left;display:inline-block;'><img src='image/downpage1.png'></div>";			
		}
		else
		{
	        int listNum2 = pageListNum + 1 > this.pageListCount?this.pageListCount:pageListNum + 1;//���ҳ��
	        
	        int pageNum2 = (listNum2 - 1) * this.pageListSize + 1;//��ò˵���ʾ��ʼҳ
			
			results += "<div style='cursor:pointer;margin-left:2px;margin-right:2px;height:26px;float:left;display:inline-block;'><img src='image/downpage.png'  onclick='showBtn(" + pageNum2 + "," + listNum2 + ")'></div>";			
		}
		
		results += "</div>";
		
		return results;
	}
	
	//���ݵ�ǰҳ��Ͳ˵�����ȷ����ʾҪ��ʾ�ĵ����ʾ�
	private String getPageByNum(int pageNum,int pageListNum)
	{
		String results = "";//���÷��ʽ��
		
        initParameters(pageNum,pageListNum);//�����ʾ�ҳ�Ͳ˵�ҳ��Ϣ��ʼ��
		
		this.surveyOp = new SurveyDaoImpl();//ʵ���������ʾ������
		
		List surveyList = this.surveyOp.getServeyList();//��õ����ʾ��б�
		
		int listNum = this.pageSize;//Ĭ����ʾ�б�����
		
		int lastNum = this.surveyCount % this.pageSize;//������һҳ�����ʾ����
		
		if(this.pageCount == this.currentPageNum)
		{
		   if(lastNum < listNum)
		   {
			   listNum = lastNum;//�����б�Ϊ��ǰҳ�ĵ����ʾ����
		   }
		}
		
		//�����ʾ������պ���ÿҳ�����ʾ�����������
		if(listNum == 0 && this.surveyCount != 0)
		{
			listNum = this.pageSize;//��ʾ�б�����
		}
		
		if(this.surveyCount == 0)
		{
			listNum = 0;
		}
		for(int i = this.pageFirstIndex; i < this.pageFirstIndex + listNum; i++)
		{
			Survey survey = (Survey)surveyList.get(i);//��õ����ʾ�ʵ��
			
			UserDaoImpl userOp = new UserDaoImpl();//����û�������
			
			User user = userOp.getUser(survey.getSurveyOwnerID());//��õ�ǰ�����ʾ�ķ�����
			
			results += "<div>" ;
			
			results += "<div style='width:540px;margin-top:5px;margin-bottom:10px;' align='center'><a href='showsurvey.jsp?surveyId=" + survey.getSurveyID() + "'>";
			
			results += getNewString(survey.getSurveyTitle(),70);
			
			results += "</a></div>"; 
			
			results += "<div style='width:540px;margin-top:5px;margin-bottom:10px;padding-left:15px;' align='left'>";
			
			results += getNewString(survey.getSurveyLink(),80);
			
			results += "</div>";
			
			results += "<div style='width:540px;margin-top:10px;margin-bottom:10px;padding-left:15px;padding-bottom:10px;border-bottom:1px dotted #0066CC;' align='left'>";
			
			results += "<span>" + user.getUserName()+ "</span><span style='margin-left:10px;margin-right:10px;'>������</span><span>" + survey.getSurveyCreateDate().toString() + "</span>";
			
			results += "</div>";
			
			results += "</div>";
		}
		
		results += "<div>�����ʾ�������" + this.surveyCount + " �����ʾ���ҳ����" + this.pageCount + " �����ʾ��ܲ˵�������" + this.pageListCount + "</div>";
		
		return results;
	}
	

	private String getNewListFiveSearch()
	{
		this.surveyOp = new SurveyDaoImpl();//ʵ���������ʾ������
		
		List surveyList = this.surveyOp.getServeyList();//��õ������ʾ�
		
		String results = "";
		
		int currentCount = 5;//�����ʾ�ǰ����
		
		if(currentCount > surveyList.size())
		{
			currentCount = surveyList.size();//��õ�ǰ�����ʾ����
		}
		
		//�����ǰ�����ڵ����ʾ���ʾû�е����ʾ�
		if(surveyList.size() == 0)
		{
		  results = "<div>��ǰ�����ڵ����ʾ�</div>";
		}
		else
		{
			//ѭ����ʾ������
			for(int sIndex = 0; sIndex < currentCount;sIndex++)
			{
				Survey survey = (Survey)surveyList.get(sIndex);//��õ�ǰ�����ʾ�
				
				String titleString = "";
				
				if(survey.getSurveyTitle().length() < 8)
				{
					titleString = survey.getSurveyTitle();//�������
				}
				else
				{
				    titleString = survey.getSurveyTitle().substring(0, 7);	
				}
				
				results += "<div style='margin-top:8px;margin-bottom:8px;'><a href='showsurvey.jsp?surveyId=" + survey.getSurveyID() + "'><span style='margin-left:30px;'>" 
					+ titleString + "</span><span style='margin-left:30px;'>" + 
					survey.getSurveyCreateDate().toString() + "</span></a></div>";//��õ����ʾ�
			}
		}

	  
	  return results;
	}
	
	//��õ�ǰ�������
	private String getNewListFive()
	{
		this.surveyOp = new SurveyDaoImpl();//ʵ���������ʾ������
		
		List surveyList = this.surveyOp.getServeyList();//��õ������ʾ�
		
		String results = "";
		
		int currentCount = 5;//�����ʾ�ǰ����
		
		if(currentCount > surveyList.size())
		{
			currentCount = surveyList.size();//��õ�ǰ�����ʾ����
		}
		
		//�����ǰ�����ڵ����ʾ���ʾû�е����ʾ�
		if(surveyList.size() == 0)
		{
		  results = "<div>��ǰ�����ڵ����ʾ�</div>";
		}
		else
		{
			//ѭ����ʾ������
			for(int sIndex = 0; sIndex < currentCount;sIndex++)
			{
				Survey survey = (Survey)surveyList.get(sIndex);//��õ�ǰ�����ʾ�
				
				String titleString = "";
				
				if(survey.getSurveyTitle().length() < 8)
				{
					titleString = survey.getSurveyTitle();//�������
				}
				else
				{
				    titleString = survey.getSurveyTitle().substring(0, 7);	
				}
				
				results += "<div><a href='showsurvey.jsp?surveyId=" + survey.getSurveyID() + "'><span style='margin-left:30px;'>" 
					+ titleString + "</span><span style='margin-left:30px;'>" + 
					survey.getSurveyCreateDate().toString() + "</span></a></div>";//��õ����ʾ�
			}
		}

	  
	  return results;
	}
	
	//���ָ����С�����з����ַ���
    private String getNewString(String oldString,int size)
    {
    	String newString = "";
    	
    	for(int i = 0; i < oldString.length(); i = i + size)
    	{
    	  int endI = i + size - 1;
    	  
    	  if(endI >= oldString.length())
    	  {
    		  endI = oldString.length();
    	  }
    	  
    	  newString += oldString.substring(i,endI);	
    	  
    	  newString += "<br>";//��ӻ���
    	}
    	
    	return newString;
    }
	//��õ����ʾ�����
	private int getSurveyCount()
	{
		this.surveyOp = new SurveyDaoImpl();
		
		this.surveyCount = this.surveyOp.getServeyList().size();//��õ����ʾ�����
		
		return this.surveyCount;//���ص����ʾ�����
	}
	
	//��õ����ʾ���ҳ��
	private int getPageCount()
	{
		getSurveyCount();//��õ�ǰ�����ʾ�����
		
		this.pageCount = (int)Math.ceil(this.surveyCount / (double)this.pageSize);//��õ�ǰ��ҳ��
		
		return this.pageCount;//���ص�ǰ��ҳ��
	}
	
	//��õ����ʾ��ܲ˵���
	private int getPageListCount()
	{
		
		getPageCount();//��õ�ǰ�����ʾ���ҳ��
		
		this.pageListCount = (int)Math.ceil(this.pageCount / (double)this.pageListSize);//��ò˵�������
		
		return this.pageListCount;//���ص�ǰ�ܲ˵���
	}
	//���������ʼ��
	private void initParameters(int pageNum,int pageListNum)
	{
		getPageListCount();//��ʼ��surveyCount��pageCount��pageListCount�������
		
		this.currentPageNum = pageNum;//���õ�ǰ��ʾҳ��
		
		this.currentPageListNum = pageListNum;//���õ�ǰ��ʾ�˵���
		
		this.pageFirstIndex = (this.currentPageNum - 1) * this.pageSize;//��õ�ǰҳ���еĵ�һ�������ʾ��±�
			
		this.pageListIndex = (this.currentPageListNum - 1) * this.pageListSize + 1;//��õ�ǰ�˵�����ʾ��ҳ��

	}

	public void init() throws ServletException {
		
          initParameters(1,1);//��ʼ�������ʾ�ҳ�Ͳ˵�ҳ

	}

}
