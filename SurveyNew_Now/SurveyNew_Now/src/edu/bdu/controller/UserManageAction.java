package edu.bdu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.bdu.dao.SurveyDaoImpl;
import edu.bdu.dao.UserDaoImpl;
import edu.bdu.dao.VisitorDaoImpl;
import edu.bdu.entity.Survey;
import edu.bdu.entity.User;


public class UserManageAction extends HttpServlet {
	
   /**
     * ��ʼ��
     */
	private UserDaoImpl userOp;//�����ʾ������
	
	private int userCount = 0;//��õ����ʾ�����
	
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
	
	private int nameSize = 18;//�û�����ʾ������ַ���


	public UserManageAction() {
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
			
			out.print(getUserCount());//�����ͨ����Ա����
			
			break;
		case 3:
			
	        userOp = new UserDaoImpl();//��ͨ����Ա������
	        
			String userNum2 = request.getParameter("userId");
			
			int userId2 = Integer.parseInt(userNum2);//�����ͨ����Ա���
			
	        User user2 = userOp.getUser(userId2);
	        
	        String userInfo = "{\"userID\":\"" + user2.getUserID() + "\","   +
	        		"\"userName\":\"" + user2.getUserName() + "\"," +
	        		"\"userPass\":\"" + user2.getUserPassword() + "\"," +
	        	    "\"userType\":\"" + user2.getUserType() + "\"}";
	        
	        if(user2 != null)
	           out.print(userInfo);//����û�����
	        else
	           out.print("usererror");//�û���ݲ���ȷ
	        
			break;

		case 4:
			
			String userNum = request.getParameter("userId");
			
			int userId = Integer.parseInt(userNum);//�����ͨ����Ա���
			
	        userOp = new UserDaoImpl();//��ͨ����Ա������
	           
	        if(userOp.deleteUser(userId))//ɾ����������Ϣ
	        {
	           out.println("<script type='text/javascript'>alert('����Ա��Ϣ�ѳɹ�ɾ��!');getUserList();getUserCount();</script>");
	        }
	        else
	        {
	           out.println("<script type='text/javascript'>alert('����Ա��Ϣ�����쳣û�ܳɹ�ɾ��!');</script>");
	         }
			
			break;
			
		case 5:

	        User user1 = new User();//�����û�����
	        
	        if(request.getParameter("userid")!= null)
	        {
	           int userid = Integer.parseInt(request.getParameter("userid"));//����û����
	           
	           user1.setUserID(userid);//�û���Ÿ�ֵ
	        }
	        
	        if(request.getParameter("username")!=null)
	        {
	            String username = request.getParameter("username");
	            
	            user1.setUserName(username);//Ϊ�û����Ƹ�ֵ
	        }
	        
	        if(request.getParameter("userpassword")!=null)
	        {
	            String userpassword = request.getParameter("userpassword");
	            
	            user1.setUserPassword(userpassword);//Ϊ�û����븳ֵ 
	        }
	        

	           
	        user1.setUserType(0);//Ϊ�û����͸�ֵ

	        
	        this.userOp = new UserDaoImpl();//�û�������
	        
	        if(userOp.InsertUser(user1))
	        {
	          out.print("success");//����ɹ���־
	        }
	        else
	        {
	          out.println("fail");//���ʧ�ܱ�־
	        }
			
			break;	
			
		case 6:
			
	        User user3 = new User();//�����û�����
	        
	        if(request.getParameter("userid")!= null)
	        {
	           int userid = Integer.parseInt(request.getParameter("userid"));//����û����
	           
	           user3.setUserID(userid);//�û���Ÿ�ֵ
	        }
	        
	        if(request.getParameter("username")!=null)
	        {
	            String username = request.getParameter("username");
	            
	            user3.setUserName(username);//Ϊ�û����Ƹ�ֵ
	        }
	        
	        if(request.getParameter("userpassword")!=null)
	        {
	            String userpassword = request.getParameter("userpassword");
	            
	            user3.setUserPassword(userpassword);//Ϊ�û����븳ֵ 
	        }
	        
	        if(request.getParameter("usertype")!= null)
	        {
	           String usertype = request.getParameter("usertype");
	           
	           int usertypeId = Integer.parseInt(usertype);
	           
	           user3.setUserType(usertypeId);//Ϊ�û����͸�ֵ
	        }
	        
	        this.userOp = new UserDaoImpl();//�û�������
	        
	        if(userOp.updateUser(user3))
	        {
	          //response.sendRedirect("testuserdao.jsp");
	          out.print("success");//����ɹ���־
	        }
	        else
	        {
	          out.println("fail");//���ʧ�ܱ�־
	        }
			
			break;
			
		case 7:
			String userNum7 = request.getParameter("userId");
			
			int userId7 = Integer.parseInt(userNum7);//�����ͨ����Ա���
			
	        userOp = new UserDaoImpl();//��ͨ����Ա������
	           
	        if(userOp.deleteUser(userId7))//ɾ����������Ϣ
	        {
	           out.println("<script type='text/javascript'>alert('����Ա��Ϣ�ѳɹ�ɾ��!');getUserListTen();</script>");
	        }
	        else
	        {
	           out.println("<script type='text/javascript'>alert('����Ա��Ϣ�����쳣û�ܳɹ�ɾ��!');</script>");
	         }			
			break;
			
		case 8:
	        userOp = new UserDaoImpl();//��ͨ����Ա������
	        
			String userNum8 = request.getParameter("userId");
			
			int userId8 = Integer.parseInt(userNum8);//�����ͨ����Ա���
			
	        User user8 = userOp.getUser(userId8);
	        
	        SurveyDaoImpl surveyOp8 = new SurveyDaoImpl();//��õ����ʾ������
	        
	        List surveyList = surveyOp8.getServeyList(userId8);
	        
	        String userInfo8 = "<table width='100%' height='100%' style='color:#0099CC;font-size:12px;border-top:1px solid #0099CC;border-bottom:1px solid #0099CC;'>";
	        userInfo8 += "<tr><td>�û����</td><td>" + user8.getUserID() + "</td></tr>"; 
	        userInfo8 += "<tr><td>�û�����</td><td>" + user8.getUserName() + "</td></tr>";
	        userInfo8 += "<tr><td>�û�����</td><td>" + user8.getUserPassword() + "</td></tr>"; 
	        userInfo8 += "<tr><td>�û�����</td><td>��ͨ����Ա</td></tr>"; 
	        userInfo8 += "<tr><td>���������ʾ�����</td><td>" + surveyList.size() + "</td></tr>";	
	        if(surveyList.size() > 0)
	        {
	          Survey survey8 = (Survey)surveyList.get(0);//�������޸ĵĵ����ʾ�	
		      String surveyName = survey8.getSurveyTitle().length()<= nameSize?survey8.getSurveyTitle():survey8.getSurveyTitle().substring(0,nameSize);
		      userInfo8 += "<tr><td>����޸ĵĵ����ʾ�</td><td>" + surveyName + "</td></tr>";
	        }
            userInfo8 += "</table>";
	        
	        if(user8 != null)
	           out.print(userInfo8);//����û�����
	        else
	           out.print("usererror");//�û���ݲ���ȷ
			break;
		default:
			break;
		}

		out.flush();
		out.close();
	}

	//���ݵ�ǰҳ��Ͳ˵�����ȷ����ʾҪ��ʾ�Ĺ���Ա
	private String getPageByNum(int pageNum,int pageListNum)
	{
		String results = "";//���÷��ʽ��
		
        initParameters(pageNum,pageListNum);//����Ա�Ͳ˵�ҳ��Ϣ��ʼ��
		
		this.userOp = new UserDaoImpl();//ʵ��������Ա������
		
		List userList = this.userOp.getUsersNotAdmin();//��ù���Ա�б�
		
		int listNum = this.pageSize;//Ĭ����ʾ�б�����
		
		int lastNum = this.userCount % this.pageSize;//������һҳ����Ա����
		
		if(this.pageCount == this.currentPageNum)
		{
		   if(lastNum < listNum)
		   {
			   listNum = lastNum;//�����б�Ϊ��ǰҳ�Ĺ���Ա����
		   }
		}
		
		//����Ա�����պ���ÿҳ����Ա����������
		if(listNum == 0 && this.userCount != 0)
		{
			listNum = this.pageSize;//��ʾ�б�����
		}
		
		if(this.userCount == 0)
		{
		  listNum = 0;
		}
		
		
		//�����ʾ����
	     results +="<div style='margin-top:10px;margin-bottom:10px;'><table border='0' cellpadding='0' cellspacing='0' class='tableClass'>";
	     results +="<tr>";
	     results +="<td align='center' class='tdHeader'>�û����</td>";
	     results +="<td align='center' class='tdHeader'>�û�����</td>";
	     results +="<td align='center' class='tdHeader'>�û�����</td>";
	     results +="<td align='center' class='tdHeader'>�û�����</td>";
	     results +="<td align='center' class='tdHeader'>����</td>";
	     results +="<td align='center' class='tdHeader'>����</td>";
	     results +="<td align='center' class='tdHeader'>����</td>";
	     results +="</tr>";
	     
		
		for(int i = this.pageFirstIndex; i < this.pageFirstIndex + listNum; i++)
		{
		       User user = (User)userList.get(i);//����û���Ϣ
		       
		       results += "<tr class='trContent'>";
		       
		       results += "<td class='tdContent' align='center'>" + user.getUserID() + "</td>";
		       
		       String userName = user.getUserName().length()<= nameSize?user.getUserName():user.getUserName().substring(0,nameSize);
		       
		       results += "<td class='tdContent' align='center'>" + userName + "</td>";
		       
		       String userPass = user.getUserPassword().length()<= nameSize?user.getUserPassword():user.getUserPassword().substring(0,nameSize);
		       
		       results += "<td class='tdContent' align='center'>" + userPass + "</td>";
		       
		       String userType = user.getUserType()==1?"ϵͳ����Ա":"��ͨ����Ա";
		       
		       results += "<td class='tdContent' align='center'>" + userType + "</td>";
		       results += "<td class='tdContent' align='center'>" + "<a href='javascript:getUserDataById(" + user.getUserID() + ")'>�鿴</a>" + "</td>";
		       results += "<td class='tdContent' align='center'>" + "<a href='javascript:getUserDataById(" + user.getUserID() + ")'>�޸�</a>" + "</td>";
		       results += "<td class='tdContent' align='center'>" + "<a href='javascript:deleteUserDataById(" + user.getUserID() + ")'>ɾ��</a>" + "</td>";
		       
		       results += "</tr>";
		       
		}
		
	    results +="</table></div>";
		
		results += "<div>����Ա������" + this.userCount + " ��ͨ����Ա��ҳ����" + this.pageCount + " ��ͨ����Ա�ܲ˵�������" + this.pageListCount + "</div>";
		
		return results;
	}

   	//���ݵ�ǰҳ��Ͳ˵���ȷ��Ҫ��ʾ�Ĳ˵�
	private String getPageBottomByNum(int pageNum,int pageListNum)
	{
		
		
		String results = "<div style='height:27px;width:348px;display:inline-block;'>";//����Ա
		
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
	
    //��õ����ʾ�����
	private int getUserCount()
	{
		this.userOp = new UserDaoImpl();
		
		this.userCount = userOp.getUsersNotAdmin().size();//����û��б�
		
		return this.userCount;//���ع���Ա����
	}
	
    //��ù���Ա��ҳ��
	private int getPageCount()
	{
		getUserCount();//��õ�ǰ����Ա����
		
		this.pageCount = (int)Math.ceil(this.userCount / (double)this.pageSize);//��õ�ǰ��ҳ��
		
		return this.pageCount;//���ص�ǰ��ҳ��
	}
	
	//��ù���Ա�ܲ˵���
	private int getPageListCount()
	{
		
		getPageCount();//��õ�ǰ����Ա��ҳ��
		
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

	public void init() throws ServletException {
		
		initParameters(1,1);//��ʼ������Աҳ�Ͳ˵�ҳ

	}

}
