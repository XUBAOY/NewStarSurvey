package edu.bdu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.bdu.dao.CheckboxItemDaoImpl;
import edu.bdu.dao.ItemDaoImpl;
import edu.bdu.dao.RadioItemDaoImpl;
import edu.bdu.dao.SurveyDaoImpl;
import edu.bdu.dao.TextItemDaoImpl;
import edu.bdu.dao.UserDaoImpl;
import edu.bdu.entity.CheckboxItem;
import edu.bdu.entity.Item;
import edu.bdu.entity.RadioItem;
import edu.bdu.entity.Survey;
import edu.bdu.entity.User;
import edu.bdu.tools.OperatorTools;

public class UserSurveyManageAction extends HttpServlet {

    /**
     * ��ʼ��
     */
	private SurveyDaoImpl surveyOp;//�����ʾ������
	
	private int surveyCount = 0;//��õ����ʾ�����
	
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
	
	private int titleSize = 18;//������ʾ������ַ���
	
	private int linkSize = 18;//������ʾ������ַ���
	
	private int nameSize = 9;//�û�����ʾ������ַ���
	
	private User user;//���浱ǰ�û���Ϣ

	public UserSurveyManageAction() {
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
		HttpSession session = request.getSession();//�������session
		this.user = (User)session.getAttribute("user");//��õ�ǰ�û���Ϣ
		
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
			
			out.print(getSurveyCount());//��������ʾ�����
			
			break;
		case 3:
				
	        
			break;
		case 4:
			
			String surveyNum = request.getParameter("surveyId");
			
			int surveyId = Integer.parseInt(surveyNum);//�����ͨ����Ա���
			
	        surveyOp = new SurveyDaoImpl();//��ͨ����Ա������
	           
	        if(surveyOp.deleteSurvey(surveyId))//ɾ����������Ϣ
	        {
	           out.println("<script type='text/javascript'>alert('�˵����ʾ��ѳɹ�ɾ��!');getSurveyList();getSurveyCount();</script>");
	        }
	        else
	        {
	           out.println("<script type='text/javascript'>alert('�˵����ʾ������쳣û�ܳɹ�ɾ��!');</script>");
	         }
			
			break;
			
		case 5:
			String surveyNum5 = request.getParameter("surveyId");
			
			int surveyId5 = Integer.parseInt(surveyNum5);//�����ͨ����Ա���
			
	        surveyOp = new SurveyDaoImpl();//��ͨ����Ա������
	           
	        if(surveyOp.deleteSurvey(surveyId5))//ɾ����������Ϣ
	        {
	           out.println("<script type='text/javascript'>alert('�˵����ʾ��ѳɹ�ɾ��!');getSurveyListTen();</script>");
	        }
	        else
	        {
	           out.println("<script type='text/javascript'>alert('�˵����ʾ������쳣û�ܳɹ�ɾ��!');</script>");
	         }
			
			
			break;
			
		case 6:
			//���������ʾ��õ����ʾ���õ����ʾ���0�����������ʾ���1�������ʾ�����session�н��б��棬�����û�
	        
			if(session.getAttribute("surveyId") != null)
			{
				String surveyIdStr = (String)session.getAttribute("surveyId");
		        int surveyid = Integer.parseInt(surveyIdStr);//��õ����ʾ���
		        
		        SurveyDaoImpl surveyOp = new SurveyDaoImpl();//�����ʾ������
		        
		        ItemDaoImpl itemOp = new ItemDaoImpl();//�����������
		        
		        RadioItemDaoImpl radioOp = new RadioItemDaoImpl();//��ѡ�������
		        
		        CheckboxItemDaoImpl checkOp = new CheckboxItemDaoImpl();//��ѡ�������
		        
		        TextItemDaoImpl textOp = new TextItemDaoImpl();//�����������
		        
		        Survey survey = surveyOp.getSurvey(surveyid);//���ݵ����ʾ��Ż�ȡ�����ʾ�
		        
		        OperatorTools opTool = new OperatorTools();//��ù�����
		        
		        String surveyObject = "";//json����Ŀ�ʼ
		        
		        String surveyInfo = null;
		        String itemList = null;
		        String itemInfo = null;
		        String answerList = null;
		        String radioItem = null;
		        String checkboxItem = null;
		        String textItem = null;
		        
		        
		        surveyObject = "{";
		        
		        if(survey != null)
		        {
		        	surveyInfo = "\"surveyInfo\":{";
		        	
		        	surveyInfo += "\"surveyId\":" + survey.getSurveyID() + ",";
		        	
		        	surveyInfo += "\"surveyTitle\":\"" + opTool.getStringReplace(survey.getSurveyTitle()) + "\",";
		        	surveyInfo += "\"surveyOwnerID\":" + survey.getSurveyOwnerID() + ",";
		        	surveyInfo += "\"surveyLink\":\"" + opTool.getStringReplace(survey.getSurveyLink()) + "\",";
		        	surveyInfo += "\"surveyCreateDateTime\":\"" + survey.getSurveyCreateDate() + "\",";
		        	surveyInfo += "\"surveyExpirationDateTime\":\"" + survey.getSurveyExpirationDate() + "\"}";
		        	
		          surveyObject +=  surveyInfo;//��õ����ʾ���Ϣ
		          surveyObject += ",";
		          surveyObject += "\"sItemList\":";
		        	
		          List items = itemOp.getItemList(survey.getSurveyID());//���ݵ����ʾ��Ų�ѯ�������б�
		          
		          itemList = "[";
		          
		          for(int i = 0; i < items.size(); i++)
		          {
		              Item item = (Item)items.get(i);//��õ���������
		              
		              itemInfo = "\"itemInfo\":{";
		              itemInfo += "\"itemID\":" + item.getItemID() +",";
		              itemInfo += "\"itemCaption\":\"" + item.getItemCaption() + "\",";
		              itemInfo += "\"itemType\":" + item.getItemType() +",";
		              itemInfo += "\"itemAttribute\":" + item.getItemAttribute() +",";
		              itemInfo += "\"itemOwnerID\":" + item.getItemOwnerID() +",";
		              itemInfo += "\"itemOwnerSurveyID\":" + item.getItemOwnerSurveyID() +",";
		              itemInfo += "\"radioCheckboxCount\":" + item.getRadioCount() +"}";
		              
		              itemList += "{";
		              itemList += itemInfo;
		              itemList += ",\"answerList\":";
		              List answers = new ArrayList();
		              

		              switch(item.getItemType())//����������
		              {
		                
		                case 0:
		                   answers = radioOp.getRadioList(item.getItemID());//���ݵ������Ų�ѯ��ѡ����
		                   
		                   answerList = "[";
		                   
		                   for(int ri = 0; ri < answers.size(); ri++)
		                   {
		                     RadioItem rItem = (RadioItem)answers.get(ri);//��ô���
		             
	
		                     radioItem = "{";
		                     radioItem += "\"radioId\":" + rItem.getRadioID() +",";
		                     radioItem += "\"radioOwnerId\":" + rItem.getRadioOwnerID() +",";
		                     radioItem += "\"radioIndex\":" + rItem.getRadioIndex() +",";
		                     radioItem += "\"radioCaption\":\"" + rItem.getRadioCaption() + "\",";
		                     radioItem += "\"defaultSelected\":" + rItem.getDefaultSelected() +",";
		                     radioItem += "\"selectedCount\":" + rItem.getSelectCount() +"}";
		                     
				              answerList += radioItem;	
				              
				              if((ri + 1) != answers.size())
				              {
				            	  answerList += ",";
				              }
		                    
		                   }
		                   answerList += "]";
		                                     
		                   break;
		                   
		                case 1:
		                
		                   answers = checkOp.getCheckboxList(item.getItemID());//���ݵ������Ų�ѯ��ѡ����
		                   answerList = "[";		                   
		                   for(int ri = 0; ri < answers.size(); ri++)
		                   {
		                     CheckboxItem cItem = (CheckboxItem)answers.get(ri);//��ô���
		                     
		                     checkboxItem = "{";
		                     checkboxItem += "\"checkboxId\":" + cItem.getCheckboxID() +",";
		                     checkboxItem += "\"checkboxOwnerID\":" + cItem.getCheckboxOwnerID() +",";
		                     checkboxItem += "\"checkboxIndex\":" + cItem.getCheckboxIndex() +",";
		                     checkboxItem += "\"checkboxCaption\":\"" + cItem.getCheckboxCaption() + "\",";
		                     checkboxItem += "\"defaultSelected\":" + cItem.getDefaultSelected() +",";
		                     checkboxItem += "\"selectedCount\":" + cItem.getSelectCount() +"}";
		                     
				              answerList += checkboxItem;	
				              
				              if((ri + 1) != answers.size())
				              {
				            	  answerList += ",";
				              }
				              	
		                   }
		                   
			               answerList += "]";	
			               
		                   break;
		                   
		                case 2:
		                
	                        answerList = "null";
		                   
		                   break;
		                
		              }
		              
		              itemList += answerList;
		              itemList += "}";
		              
		              if((i + 1) != items.size())
		              {
		            	  itemList += ",";
		              }
		          }
		          itemList += "]";
		          surveyObject += itemList;
		          surveyObject += "}";
		          
		          out.print(surveyObject);
		        }
			}
			else
			{

                out.print("fail");//�����ʾ�������

			}
			
			break;
		case 7:
			//���������ʾ���session����
			String surveyNum7 = request.getParameter("surveyId");
			
			session.setAttribute("surveyId",surveyNum7);//���浱ǰ�����ʾ���
			
			out.print("success");//�����������
			
			break;
			
		case 8:
			//���������ʾ�
	        User user8 = null;
	        
	        if(session.getAttribute("user") != null)
	        {
	           user8 = (User)session.getAttribute("user");//��õ�ǰ�û�������
	         
	           Survey survey = new Survey();//���������ʾ����
	       
	           SurveyDaoImpl surveyOp = new SurveyDaoImpl();//�����ʾ������
	        
	           //�û�����Զ����ɣ��˳���˶δ���δ��
	           if(request.getParameter("surveytitle")!= null)
	           {
	        	   String surveytitle = request.getParameter("surveytitle");//��õ����ʾ����
	           
	        	   survey.setSurveyTitle(surveytitle);//���õ����ʾ����
	           }
	           
	           if(request.getParameter("surveylink")!=null)
	           {
	               String surveylink = request.getParameter("surveylink");//��õ����ʾ�����
	               
	              survey.setSurveyLink(surveylink);//���õ����ʾ�����
	           }
	           
	           if(surveyOp.getServeyByTitle(survey.getSurveyTitle()) == null){
	        	   
	               survey.setSurveyOwnerID(user8.getUserID());//����û����
	               
	               if(surveyOp.InsertSurvey(survey))
	               {
	  
	                    survey = surveyOp.getServeyByTitle(survey.getSurveyTitle());
	                    
	                    String surveyIdStr = String.valueOf(survey.getSurveyID());
	                    
	        			session.setAttribute("surveyId",surveyIdStr);//���浱ǰ�����ʾ���
	        			
	                    out.print("success");//���ص����ʾ��б�
	        			
	                }
	                else
	                {
	                  out.print("fail");
	                  
	                }
	        	   
	           }
	           else
	           {
	        	   out.print("surveyexist");
	           }
	        }
	        else
	        {
	        	out.print("usererror");
	        }
	        
			break;
			
		case 9:
			   //���ĵ����ʾ���Ϣ
	           Survey survey = new Survey();//���������ʾ����
		       
	           SurveyDaoImpl surveyOp = new SurveyDaoImpl();//�����ʾ������
	           
	           if(request.getParameter("surveyId") != null)
	           {
	             int surveyId9 = Integer.parseInt(request.getParameter("surveyId"));//��õ����ʾ���
	             
	             survey = surveyOp.getSurvey(surveyId9);//��õ����ʾ�
	           }
	           //�û�����Զ����ɣ��˳���˶δ���δ��
	           if(request.getParameter("surveytitle")!= null)
	           {
	        	   String surveytitle = request.getParameter("surveytitle");//��õ����ʾ����
	           
	        	   survey.setSurveyTitle(surveytitle);//���õ����ʾ����
	           }
	           
	           if(request.getParameter("surveylink")!=null)
	           {
	               String surveylink = request.getParameter("surveylink");//��õ����ʾ�����
	               
	              survey.setSurveyLink(surveylink);//���õ����ʾ�����
	           }
	           
	           if(surveyOp.getServeyByTitle(survey.getSurveyTitle()) == null){
	        	   
	               
	               if(surveyOp.updateSurvey(survey))
	               {
	  
	                    survey = surveyOp.getServeyByTitle(survey.getSurveyTitle());
	                    
	                    String surveyIdStr = String.valueOf(survey.getSurveyID());
	                    
	        			session.setAttribute("surveyId",surveyIdStr);//���浱ǰ�����ʾ���
	        			
	                    out.print("success");//���ص����ʾ��б�
	        			
	                }
	                else
	                {
	                  out.print("fail"); 
	                }
	        	   
	           }
	           else
	           {
	        	   out.print("surveyexist");
	           }
			break;
		case 10:
			 //ɾ��������
		     if(request.getParameter("itemId") != null)
		     {
		       
		       int itemid = Integer.parseInt(request.getParameter("itemId"));
		       
		       ItemDaoImpl itemOp = new ItemDaoImpl();//�����������
		       
		       if(itemOp.deleteItem(itemid))
		       {
		         out.print("success");
		       }
		       else
		       {
		         out.print("fail");
		       }
		      
		     }
		     else
		     {
		       out.print("error");//��������Ϊ��
		     }
			break;
			
		case 11:
			//ɾ������
			   RadioItemDaoImpl radioOp = new RadioItemDaoImpl();
			   CheckboxItemDaoImpl checkboxOp = new CheckboxItemDaoImpl();
			   
			   int type = Integer.parseInt(request.getParameter("type"));//��õ���������
			   
			   if(type == 0)
			   {
			   
				   if(request.getParameter("id") != null)
				   {
					   int radioid = Integer.parseInt(request.getParameter("id"));//��ѡ����
					   
					   if(radioOp.deleteRadioItem(radioid))
					   {
						   out.print("success");
					   }
					   else
					   {
						   out.print("fail");
					   }
				   }
			   }
			   else if(type == 1)
			   {
				   if(request.getParameter("id") != null)
				   {
					   int checkboxid = Integer.parseInt(request.getParameter("id"));//��ѡ����
					   
					   if(checkboxOp.deleteCheckboxItem(checkboxid))
					   {
						   out.print("success");
					   }
					   else
					   {
						   out.print("fail");
					   }
				   } 
			   }
			
			break;
			
		case 12:
			
			//���²��������
			int type12 = Integer.parseInt(request.getParameter("type"));//��õ���������
			
			String idStr = request.getParameter("idstr");//��ñ���ַ���
			
			String captionStr = request.getParameter("captionStr");
			
		    ItemDaoImpl itemOp12 = new ItemDaoImpl();//�����������
		    
		    RadioItemDaoImpl radioOp12 = new RadioItemDaoImpl();//��ѡ�������ʵ��
		    
		    CheckboxItemDaoImpl checkboxOp12 = new CheckboxItemDaoImpl();//��ѡ�������ʵ��
		    
			String surveyIdStr = (String)session.getAttribute("surveyId");
			
	        int surveyid12 = Integer.parseInt(surveyIdStr);//��õ����ʾ���
	        
	        User user12 = (User)session.getAttribute("user");//��õ�ǰ�û���Ϣ
			
			switch(type12)
			{
			case 0:
			
			    String []idR = idStr.split(",");//��õ������� 
			
			    String []captionR = captionStr.split(",");//��ñ����б�
			    
			    int itemId120 = Integer.parseInt(idR[0]);
			    
			    if(itemId120 == -1)
			    {
			    	Item item120 = new Item();//�½�������
			    	
			    	item120.setItemOwerID(user12.getUserID());
			    	
			    	item120.setItemCaption(captionR[0]);
			    	
			    	item120.setItemType(0);//�ı�������
			    	
			    	item120.setItemOwnerSurveyID(surveyid12);
			    	
			    	item120.setItemAttribute(2);//����������ӱ��
			    	
		             if(itemOp12.InsertItem(item120))
		              {
		            	 
                          List items = itemOp12.getItemList(item120);//��õ�����
                          
                          if(items.size() == 1)
                          {
                        	  
                        	  item120 = (Item)items.get(0);//��õ�һ��������
                        	  item120.setItemAttribute(1);//���������
                        	  itemOp12.updateItem(item120);//���µ�����
                        	  
                        	  
                        	  
                        	  for(int rI1 = 1; rI1 < idR.length;rI1 ++)
                        	  {
                        		  RadioItem rItem = new RadioItem();
                        		  
                        		  rItem.setRadioOwnerID(item120.getItemID());//������������
                        		  
                        		  rItem.setRadioIndex(0);//������������
                        		  
                        		  rItem.setRadioCaption(captionR[rI1]);//��ѡ�������
                        		  
                        		  radioOp12.InsertRadioItem(rItem);//���������
                        		  
                        	  }
                        	  
                        	  out.print("success");//����ɹ���־
                        	  
                          }
                          else
                          {
                        	  out.print("fail");
                          }
		                   //out.print("success");//���������ɹ�
		                   
		              }
		             else
		             {
		            	out.print("fail"); //���������ʧ��
		             }
			    	
			    }
			    else
			    {
			    	Item item1220 = itemOp12.getItemByItemId(itemId120);//���ԭ�е�������Ϣ
			    	
			    	item1220.setItemCaption(captionR[0]);//���õ��������
			    	
			    	if(itemOp12.updateItem(item1220))
			    	{
			    		
                  	  for(int rI1 = 1; rI1 < idR.length;rI1 ++)
                	  {
                  		  int rId1 = Integer.parseInt(idR[rI1]);//��ô�����
                  		  
                		  RadioItem rItem = new RadioItem();
                		  
                		  if(rId1 != -1)
                		  {
                			  rItem = radioOp12.getRadio(rId1);//��õ�����
                			  
                			  rItem.setRadioCaption(captionR[rI1]);//�������ñ���
                			  
                			  radioOp12.updateRadioItem(rItem);//���µ�����
                		  }
                		  else
                		  {
                		  
                			  rItem.setRadioOwnerID(item1220.getItemID());//������������
                		  
                			  rItem.setRadioIndex(0);//������������
                		  
                			  rItem.setRadioCaption(captionR[rI1]);//��ѡ�������
                		  
                			  radioOp12.InsertRadioItem(rItem);//���������
                		  }
                		  
                	  }
                  	  
			    	 out.print("success");
			    	}
			    	else
			    	{
			    		out.print("fail");
			    	}
			    		
			    }
			    
			    
			   break;
			   
			case 1:
				String []idC = idStr.split(",");
				
				String []captionC = captionStr.split(",");
				
			    int itemId121 = Integer.parseInt(idC[0]);
			    
			    if(itemId121 == -1)
			    {
			    	Item item121 = new Item();//�½�������
			    	
			    	item121.setItemOwerID(user12.getUserID());
			    	
			    	item121.setItemCaption(captionC[0]);
			    	
			    	item121.setItemType(1);//�ı�������
			    	
			    	item121.setItemOwnerSurveyID(surveyid12);
			    	
			    	item121.setItemAttribute(2);//����������ӱ��
			    	
		             if(itemOp12.InsertItem(item121))
		              {
          
                         List items = itemOp12.getItemList(item121);//��õ�����
                         
                         if(items.size() == 1)
                         {
                       	  
                       	  item121 = (Item)items.get(0);//��õ�һ��������
                       	  item121.setItemAttribute(1);//���������
                       	  itemOp12.updateItem(item121);//���µ�����
                       	  
                       	  
                       	  
                       	  for(int rI1 = 1; rI1 < idC.length;rI1 ++)
                       	  {
                       		  CheckboxItem rItem = new CheckboxItem();
                       		  
                       		  rItem.setCheckboxOwnerID(item121.getItemID());//������������
                       		  
                       		  rItem.setCheckboxIndex(0);//������������
                       		  
                       		  rItem.setCheckboxCaption(captionC[rI1]);//��ѡ�������
                       		  
                       		  checkboxOp12.InsertCheckboxItem(rItem);//���������
                       		  
                       	  }
                       	  
                       	  out.print("success");//����ɹ���־
                         }
                         else
                         {
                        	 
                       	  out.print("fail");
                       	  
                         }
		                   
		              }
		             else
		             {
		            	out.print("fail"); //���������ʧ��
		             }
			    	
			    }
			    else
			    {
			    	Item item1221 = itemOp12.getItemByItemId(itemId121);//���ԭ�е�������Ϣ
			    	
			    	item1221.setItemCaption(captionC[0]);//���õ��������
			    	
			    	if(itemOp12.updateItem(item1221))
			    	{
	                 	  for(int rI1 = 1; rI1 < idC.length;rI1 ++)
	                	  {
	                  		  int rId1 = Integer.parseInt(idC[rI1]);//��ô�����
	                  		  
	                  		  CheckboxItem rItem = new CheckboxItem();
	                		  
	                		  if(rId1 != -1)
	                		  {
	                			  rItem = checkboxOp12.getCheckbox(rId1);//��õ�����
	                			  
	                			  rItem.setCheckboxCaption(captionC[rI1]);//�������ñ���
	                			  
	                			  checkboxOp12.updateCheckboxItem(rItem);//���µ�����
	                		  }
	                		  else
	                		  {
	                		  
	                       		  rItem.setCheckboxOwnerID(item1221.getItemID());//������������
	                       		  
	                       		  rItem.setCheckboxIndex(0);//������������
	                       		  
	                       		  rItem.setCheckboxCaption(captionC[rI1]);//��ѡ�������
	                       		  
	                       		  checkboxOp12.InsertCheckboxItem(rItem);//���������
	                		  }
	                		  
	                	  }
	                  	  
				    	 out.print("success");
			    	}
			    	else
			    	{
			    		out.print("fail");
			    	}
			    		
			    }
				break;
				
			case 2:
				
			    int itemId12 = Integer.parseInt(idStr);
			    
			    if(itemId12 == -1)
			    {
			    	Item item122 = new Item();//�½�������
			    	
			    	item122.setItemOwerID(user12.getUserID());
			    	
			    	item122.setItemCaption(captionStr);
			    	
			    	item122.setItemType(2);//�ı�������
			    	
			    	item122.setItemOwnerSurveyID(surveyid12);
			    	
		             if(itemOp12.InsertItem(item122))
		              {
          
		                   out.print("success");//���������ɹ�
		                   
		              }
		             else
		             {
		            	out.print("fail"); //���������ʧ��
		             }
			    	
			    }
			    else
			    {
			    	Item item1221 = itemOp12.getItemByItemId(itemId12);//���ԭ�е�������Ϣ
			    	
			    	item1221.setItemCaption(captionStr);//���õ��������
			    	
			    	if(itemOp12.updateItem(item1221))
			    	{
			    	 out.print("success");
			    	}
			    	else
			    	{
			    		out.print("fail");
			    	}
			    		
			    }
			    
			  break;

			}
			
			
			break;			
			
		default:
			break;
		}
		
		out.flush();
		out.close();
	}

	//���ݵ�ǰҳ��Ͳ˵���ȷ��Ҫ��ʾ�Ĳ˵�
	private String getPageBottomByNum(int pageNum,int pageListNum)
	{
		
		
		String results = "<div style='height:27px;width:348px;display:inline-block;'>";//�����ʾ�
		
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
	
	//���ݵ�ǰҳ��Ͳ˵�����ȷ����ʾҪ��ʾ�ĵ����ʾ�
	private String getPageByNum(int pageNum,int pageListNum)
	{
		String results = "";//���÷��ʽ��
		
        initParameters(pageNum,pageListNum);//�����ʾ�ҳ�Ͳ˵�ҳ��Ϣ��ʼ��
		
		this.surveyOp = new SurveyDaoImpl();//ʵ���������ʾ������
		
		List surveyList = this.surveyOp.getServeyList(user.getUserID());//��õ����ʾ��б�
		
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
			listNum = 0;//��ʾ�б�����
		}
		
		//�����ʾ����
	     results +="<div style='margin-top:10px;margin-bottom:10px;'><table border='0' cellpadding='0' cellspacing='0' class='tableClass'>";
	     results +="<tr>";
	     results +="<td align='center' class='tdHeader'>�ʾ����</td>";
	     results +="<td align='center' class='tdHeader'>�ʾ�˵��</td>";
	     results +="<td align='center' class='tdHeader'>����ʱ��</td>";
	     results +="<td align='center' class='tdHeader'>������</td>";
	     results +="<td align='center' class='tdHeader'>����</td>";
	     results +="<td align='center' class='tdHeader'>����</td>";
	     results +="<td align='center' class='tdHeader'>����</td>";
	     results +="</tr>";
	     
		UserDaoImpl userOp = new UserDaoImpl();//����û�������
		
		for(int i = this.pageFirstIndex; i < this.pageFirstIndex + listNum; i++)
		{
			Survey survey = (Survey)surveyList.get(i);//��õ����ʾ�ʵ��
			
			   User user = userOp.getUser(survey.getSurveyOwnerID());//��õ�ǰ�����ʾ�ķ�����
			
		       results +="<tr class='trContent'>";
		       
		       String surveyTitle = survey.getSurveyTitle().length()<= titleSize?survey.getSurveyTitle():survey.getSurveyTitle().substring(0,titleSize);
		       
		       results +="<td class='tdContent' align='center'>" + surveyTitle + "</td>";
		       
		       String surveyLink = survey.getSurveyLink().length()<=linkSize?survey.getSurveyLink():survey.getSurveyLink().substring(0,linkSize);
		       results +="<td class='tdContent' align='center'>" + surveyLink + "</td>";
		       
		       Date date = survey.getSurveyCreateDate();//�����ʾ���ʱ��
		       
		       if(date != null)
		         results +="<td class='tdContent' align='center'>" + date.toString() + "</td>";
		       else
		         results +="<td class='tdContent' align='center'>" + "����ʱ���쳣" + "</td>";
		       
		       String userName = user.getUserName().length()<= nameSize?user.getUserName():user.getUserName().substring(0,nameSize);
		       
		       results += "<td class='tdContent' align='center'>" + userName + "</td>";
		         
		       results +="<td class='tdContent' width='30' align='center'>" + "<a href='javascript:updateSurveyDataById(" + survey.getSurveyID() + ")'>�޸�</a>" + "</td>";
		       results +="<td class='tdContent' width='30' align='center'>" + "<a href='javascript:deleteSurveyDataById(" + survey.getSurveyID() + ")'>ɾ��</a>" + "</td>";
		       results +="<td class='tdContent' width='30' align='center'>" + "<a href='../showsurvey.jsp?surveyId=" + survey.getSurveyID() + "'>Ԥ��</a>" + "</td>";
		       results +="</tr>";
		       
		}
		
	    results +="</table></div>";
		
		results += "<div>�����ʾ�������" + this.surveyCount + " �����ʾ���ҳ����" + this.pageCount + " �����ʾ��ܲ˵�������" + this.pageListCount + "</div>";
		
		return results;
	}
	//��õ����ʾ�����
	private int getSurveyCount()
	{
		this.surveyOp = new SurveyDaoImpl();
		
		this.surveyCount = this.surveyOp.getServeyList(user.getUserID()).size();//��õ����ʾ�����
		
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
	}

}
